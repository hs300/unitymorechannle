using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;
public static class TestShell
{
    
    [MenuItem("Help/TestShell01")]
    private static void TestIpconfig()
    {
        Debug.Log(Environment.CurrentDirectory);
        ShellHelper.ProcessCommandSync("git --help",Environment.CurrentDirectory);
        
    }
}
