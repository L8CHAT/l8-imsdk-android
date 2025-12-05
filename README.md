# L8 IM Android SDK

![](https://img.shields.io/badge/platform-android-blue.svg) ![](https://img.shields.io/badge/compileSdkVersion-34-blue.svg) ![](https://img.shields.io/badge/minSdkVersion-21-blue.svg) ![](https://img.shields.io/hexpm/l/plug.svg)

基于悟空IM的完全自定义协议即时通讯 Android SDK。

## 📚 文档

| 文档 | 说明 |
|------|------|
| [快速入门](docs/QUICK_START.md) | 快速集成指南 |
| [API 参考](docs/API_REFERENCE.md) | 完整 API 文档 |
| [消息类型](docs/MESSAGE_TYPES.md) | 支持的消息类型 |
| [自定义消息](docs/CUSTOM_MESSAGE.md) | 创建自定义消息 |
| [架构概览](docs/ARCHITECTURE.md) | SDK 架构设计 |

## 快速入门

### 1. 配置 GitHub Packages 仓库

在项目的 `settings.gradle` 或根 `build.gradle` 中添加：

```groovy
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/L8CHAT/l8-imsdk-android")
            credentials {
                username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
```

### 2. 添加依赖

```groovy
dependencies {
    implementation 'com.l8chat:l8-imsdk-android:1.0.0'
}
```

### 3. 配置 GitHub Token

在 `~/.gradle/gradle.properties` 中添加：

```properties
gpr.user=YOUR_GITHUB_USERNAME
gpr.token=YOUR_GITHUB_TOKEN
```

> **注意：** GitHub Token 需要 `read:packages` 权限。[创建 Token](https://github.com/settings/tokens)

**混淆**
```
-dontwarn com.l8chat.imsdk.**
-keep class com.l8chat.imsdk.**{*;}

#数据库加密
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

#--------- 混淆dh curve25519-------
-keep class org.whispersystems.curve25519.**{*;}
-keep class org.whispersystems.** { *; }
-keep class org.thoughtcrime.securesms.** { *; }
```

**初始化sdk**
```
WKIM.getInstance().init(context, uid, token);
```
**初始化IP**
```
 WKIM.getInstance().getConnectionManager().addOnGetIpAndPortListener(new IGetIpAndPort() {
            @Override
            public void getIP(IGetSocketIpAndPortListener iGetSocketIpAndPortListener) {
                // 可请求接口后返回到sdk
                iGetSocketIpAndPortListener.onGetSocketIpAndPort("xxx.xxx.xxx",6666);
            }
        });
```
**连接服务端**
```
WKIM.getInstance().getConnectionManager().connection();
```

**发消息**
```
WKIM.getInstance().getConnectionManager().sendMessage(new WKTextContent("我是文本消息"), channelID, channelType);
```

## 监听
**连接状态监听**
```
WKIM.getInstance().getConnectionManager().addOnConnectionStatusListener("listener_key",new IConnectionStatus() {
            @Override
            public void onStatus(int status) {
                // 0 失败 【WKConnectStatus.fail】
                // 1 成功 【WKConnectStatus.success】
                // 2 被踢 【WKConnectStatus.kicked】
                // 3 同步消息中【WKConnectStatus.syncMsg】
                // 4 连接中 【WKConnectStatus.connecting】
                // 5 无网络连接 【WKConnectStatus.noNetwork】
            }
        });
```
**发送消息结果监听**
```
WKIM.getInstance().getMsgManager().addSendMsgAckListener("listener_key", new ISendACK() {
            @Override
            public void msgACK(long clientSeq, String messageID, long messageSeq, byte reasonCode) {
                // clientSeq 客户端序列号
                // messageID 服务器消息ID
                // messageSeq 服务器序列号
                // reasonCode 消息状态码【0:发送中1:成功2:发送失败3:不是好友或不在群内4:黑名单】
            }
        })
 ```
**监听新消息**
```
 WKIM.getInstance().getMsgManager().addOnNewMsgListener("listener_key", new INewMsgListener() {
            @Override
            public void newMsg(List<WKMsg> list) {
                // todo 
            }
        });
```
**命令消息(cmd)监听**
```
WKIM.getInstance().getCMDManager().addCmdListener("listener_key", new ICMDListener() {
            @Override
            public void onMsg(WKCMD cmd) {
                // todo
            }
        });
```
## 许可证

L8 IM SDK 使用 Apache 2.0 许可证。有关详情，请参阅 LICENSE 文件。