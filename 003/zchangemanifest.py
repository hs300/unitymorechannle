print("xxxxxxxxxxxxxxxx")


def spite_intent():
    start_str = "<intent-filter>"
    end_str = "</intent-filter>"
    with open('AndroidManifest.xml', 'r+') as file:
        # 读取文件内容
        content = file.read()
        # 修改内容
        start_index = content.index(start_str)
        end_index = content.index(end_str) + len(end_str)

        result = content[start_index:end_index]
        modified_content = content.replace(result, '')
        # 把新内容写回文件
        print("xxx")
        file.seek(0)  # 将文件指针移动到开头
        file.write(modified_content)  # 写入新内容
        file.truncate()


spite_intent()