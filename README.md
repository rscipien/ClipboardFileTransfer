# ClipboardFileTransfer
Tool used for remote desktop application (Citrix) to copy files beetwen local and remote computer when file sharing is blocked.
# Installation
In root folder there is base64.txt file which contains current JAR created from source converted to base64. Unfortunately there is no magic way to deploy this appliaction on remote computer. You must manually copy content of this file to remote computer and run certutil on windows to convert it to binary version.

```sh
certutil -decode base64.txt CTF.jar
```

