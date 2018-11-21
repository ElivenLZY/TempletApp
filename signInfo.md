签名文件：
templetapp.jks
签名密码：
templetapp123
别名：
templetapp
别名密码：
templetapp123


adb uninstall com.lzy.templetapp

//查看签名信息
keytool -v -list -keystore templetapp.jks

//签名
jarsigner -digestalg SHA1 -sigalg MD5withRSA -verbose -keystore F:\AndroidStudioProjects\XCKJProjects\CNSTApp\cnstapp.jks -signedjar C:\Users\Administrator\Desktop\CNST乐固加固版.apk C:\Users\Administrator\Desktop\old_legu.apk cnstapp