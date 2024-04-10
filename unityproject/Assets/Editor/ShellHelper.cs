using UnityEngine;
using System.Collections;
using System.Diagnostics;
using UnityEditor;
using System.Collections.Generic;

public class ShellHelper  {

	public class ShellRequest{
		public event System.Action<int,string> onLog;
		public event System.Action onError;
		public event System.Action onDone;

		public void Log(int type,string log){
			if(onLog != null){
				onLog(type,log);
			}
			if (type == 1) {
				UnityEngine.Debug.LogError (log);
			} else {
				UnityEngine.Debug.Log (log);
			}
		}

		public void NotifyDone(){
			if(onDone != null){
				onDone();
			}
		}

		public void Error(){
			if(onError != null){
				onError();
			}
		}
	}

	
	private static string shellApp{
		get{
			#if UNITY_EDITOR_WIN
			string app = "cmd.exe";
			#elif UNITY_EDITOR_OSX
			string app = "zsh";
			#endif
			return app;
		}
	}


	private static List<System.Action> _queue = new List<System.Action>();


	static ShellHelper(){
		_queue = new List<System.Action>();
		EditorApplication.update += OnUpdate;
	}
	private static void OnUpdate(){
		for(int i = 0;i<_queue.Count;i++){
			try{
				var action = _queue[i];
				if(action != null){
					action();
				}
			}catch(System.Exception e){
				UnityEngine.Debug.LogException(e);
			}
		}
		_queue.Clear();
	}


	public static void ProcessCommandSync(string cmd, string workDirectory, Dictionary<string, string> envVars = null) {
		ProcessStartInfo start = new ProcessStartInfo(shellApp);

		#if UNITY_EDITOR_OSX
		start.Arguments = "-c";
		#elif UNITY_EDITOR_WIN
		start.Arguments = "/c";
		#endif

		if (envVars != null) {
			foreach (var kv in envVars) {
				if (start.EnvironmentVariables.ContainsKey(kv.Key)) {
					start.EnvironmentVariables[kv.Key] = kv.Value;
				} else {
					start.EnvironmentVariables.Add(kv.Key, kv.Value);
				}
			}
		}
		start.Arguments += (" \"" + cmd + " \"");
		start.CreateNoWindow = true;
		start.ErrorDialog = true;
		start.UseShellExecute = false;
		start.WorkingDirectory = workDirectory;
		start.RedirectStandardOutput = false;
		start.RedirectStandardError = false;
		start.RedirectStandardInput = false;
		Process p = Process.Start(start);
		p.WaitForExit();
		UnityEngine.Debug.LogFormat("Finish running {0}", cmd);
	}

    public static ShellRequest ProcessCommand(string cmd, string workDirectory, Dictionary<string, string> envVars = null, bool isSync = false){
		ShellRequest req = new ShellRequest();
		System.Threading.ThreadPool.QueueUserWorkItem(delegate(object state) {
			Process p = null;
			try{
				ProcessStartInfo start = new ProcessStartInfo(shellApp);

				#if UNITY_EDITOR_OSX
				start.Arguments = "-c";
				#elif UNITY_EDITOR_WIN
				start.Arguments = "/c";
				#endif
				
                if (envVars != null) {
                    foreach (var kv in envVars) {
						if (start.EnvironmentVariables.ContainsKey(kv.Key)) {
							start.EnvironmentVariables[kv.Key] = kv.Value;
						} else {
							start.EnvironmentVariables.Add(kv.Key, kv.Value);
						}
                    }
                }

				start.Arguments += (" \"" + cmd + " \"");
				start.CreateNoWindow = true;
				start.ErrorDialog = true;
				start.UseShellExecute = false;
				start.WorkingDirectory = workDirectory;

				if(start.UseShellExecute){ 
					start.RedirectStandardOutput = false;
					start.RedirectStandardError = false;
					start.RedirectStandardInput = false;
				} else{
					start.RedirectStandardOutput = true;
					start.RedirectStandardError = true;
					start.RedirectStandardInput = true;
					start.StandardOutputEncoding = System.Text.UTF8Encoding.UTF8;
					start.StandardErrorEncoding = System.Text.UTF8Encoding.UTF8;
				}

				p = Process.Start(start);
				p.ErrorDataReceived += delegate(object sender, DataReceivedEventArgs e) {
					UnityEngine.Debug.LogError(e.Data);
				};
				p.OutputDataReceived += delegate(object sender, DataReceivedEventArgs e) {
					UnityEngine.Debug.LogError(e.Data);
				};
				p.Exited += delegate(object sender, System.EventArgs e) {
					UnityEngine.Debug.LogError(e.ToString());
				};

				bool hasError = false;
				do{
					string line = p.StandardOutput.ReadLine();
					if(line == null){
						break;
					}
					line = line.Replace("\\","/");
						
					_queue.Add(delegate() {
						req.Log(0,line);
					});

				}while(true);

				while(true){
					string error = p.StandardError.ReadLine();
					if(string.IsNullOrEmpty(error)){
						break;
					}
					hasError = true;
					_queue.Add(delegate() {
						req.Log(1,error);
					});
				}
				p.Close();
				if(hasError){
					_queue.Add(delegate() {
						req.Error();
					});
				}
				else {
					_queue.Add(delegate() {
						req.NotifyDone();
					});
				} 

				
			}catch(System.Exception e){
				UnityEngine.Debug.LogException(e);
				if(p != null){
					p.Close();
				}
			}
		});
		return req;
	}

	
    private Dictionary<string, string> _enviroumentVars = new Dictionary<string, string>();
	
    public void AddEnvironmentVars(string key, string value){
        if (key == null || value == null)
            return;
        
        if (string.IsNullOrEmpty(key.Trim()))
            return;

        if (_enviroumentVars.ContainsKey(key))
            _enviroumentVars[key] = value;
        
        _enviroumentVars.Add(key, value);
	}

	public ShellRequest ProcessCMD(string cmd,string workDir){
		return ShellHelper.ProcessCommand(cmd,workDir,_enviroumentVars);
	}
}
