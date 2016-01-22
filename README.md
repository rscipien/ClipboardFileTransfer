# ClipboardFileTransfer
Tool used for remote desktop application (Citrix) to copy files beetwen local and remote computer when file sharing is blocked.
#Info
Application works a little bit like client-server. You must run program on local and remote computer. Which one will be sending file 
```sh
java -jar CFT.jar -f file_to_send
```

and other will wait for file part.

```sh
java -jar CFT.jar
```

If sender get information that first part was consumed it will send next part.
# Installation
In root folder there is base64.txt file which contains current JAR created from source converted to base64. Unfortunately there is no magic way to deploy this appliaction on remote computer. You must manually copy content of this file to remote computer and run certutil on windows to convert it to binary version.

Certutil is avaliable from Windows Server 2003
```sh
certutil -decode base64.txt CFT.jar
```
Linux
```sh
base64 -d FILE 
```

