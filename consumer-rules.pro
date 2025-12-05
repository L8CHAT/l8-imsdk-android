# L8 IM SDK 混淆规则
-dontwarn com.l8chat.imsdk.**
-keep class com.l8chat.imsdk.**{*;}

# 数据库加密
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

# DH curve25519
-keep class org.whispersystems.curve25519.**{*;}
-keep class org.whispersystems.** { *; }
-keep class org.thoughtcrime.securesms.** { *; }
