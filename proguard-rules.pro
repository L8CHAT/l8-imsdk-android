# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-dontwarn org.xsocket.**
-keep class org.xsocket.** { *; }
-keep class javax.ws.rs.** { *; }
-keep class com.l8chat.imsdk.WKIM {*;}
-keep class com.l8chat.imsdk.message.type.WKMsgContentType { *; }
-keep class com.l8chat.imsdk.message.type.WKSendMsgResult { *; }
-keep class com.l8chat.imsdk.message.type.WKConnectStatus { *; }
-keep class com.l8chat.imsdk.message.type.WKConnectReason { *; }

-keep class com.l8chat.imsdk.entity.* { *; }
-keep class com.l8chat.imsdk.interfaces.** { *; }
-keep class com.l8chat.imsdk.msgmodel.** { *; }
-keep class com.l8chat.imsdk.manager.** { *; }
-keepclassmembers class com.l8chat.imsdk.db.WKDBHelper$DatabaseHelper {
   public *;
}

#--------- 混淆dh curve25519-------
-keep class org.whispersystems.curve25519.**{*;}
-keep class org.whispersystems.** { *; }
-keep class org.thoughtcrime.securesms.** { *; }

# SQLCipher 数据库加密相关混淆规则
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.** { *; }
-keep interface net.sqlcipher.** { *; }
-keepclassmembers class net.sqlcipher.** { *; }
-keepclassmembers class net.sqlcipher.database.** { *; }
-dontwarn net.sqlcipher.database.**
-dontwarn net.sqlcipher.**

# 保留本地数据库相关类
-keep class com.l8chat.imsdk.db.** { *; }
-keepclassmembers class com.l8chat.imsdk.db.** { *; }

# SQLCipher 原生库
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses

-flattenpackagehierarchy 'wkim'