# 快速入门指南

本指南帮助你快速集成 L8 IM Android SDK。

## 环境要求

- Android SDK 21+
- Java 21 / Kotlin 2.0+
- Gradle 9.0+

## 添加依赖

### 1. 配置 GitHub Packages 仓库

在项目根目录的 `settings.gradle` 中添加：

```groovy
dependencyResolutionManagement {
    repositories {
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

### 2. 添加 SDK 依赖

在 `app/build.gradle` 中添加：

```groovy
dependencies {
    implementation 'com.l8chat:l8-imsdk-android:1.0.0'
}
```

## 初始化 SDK

```java
// 在 Application 或登录成功后初始化
WKIM.getInstance().init(context, uid, token);

// 可选：开启调试模式
WKIM.getInstance().setDebug(true);

// 可选：设置文件缓存目录
WKIM.getInstance().setFileCacheDir("/path/to/cache");
```

## 配置连接

### 1. 设置服务器地址获取监听

```java
WKIM.getInstance().getConnectionManager().addOnGetIpAndPortListener((callback) -> {
    // 从你的服务器获取 IM 服务器地址
    String ip = "your-im-server.com";
    int port = 5100;
    callback.onResult(ip, port);
});
```

### 2. 监听连接状态

```java
WKIM.getInstance().getConnectionManager().addOnConnectionStatusListener("key", (status, reason) -> {
    switch (status) {
        case WKConnectStatus.success:
            // 连接成功
            break;
        case WKConnectStatus.connecting:
            // 连接中
            break;
        case WKConnectStatus.fail:
            // 连接失败
            break;
        case WKConnectStatus.kicked:
            // 被踢下线（其他设备登录）
            break;
        case WKConnectStatus.noNetwork:
            // 无网络
            break;
        case WKConnectStatus.syncMsg:
            // 同步消息中
            break;
        case WKConnectStatus.syncCompleted:
            // 同步完成
            break;
    }
});
```

### 3. 发起连接

```java
WKIM.getInstance().getConnectionManager().connection();
```

## 发送消息

### 发送文本消息

```java
WKTextContent textContent = new WKTextContent("Hello World!");
WKIM.getInstance().getMsgManager().send(textContent, channelId, channelType);
```

### 发送图片消息

```java
WKImageContent imageContent = new WKImageContent("/path/to/image.jpg");
imageContent.width = 1080;
imageContent.height = 720;
WKIM.getInstance().getMsgManager().send(imageContent, channelId, channelType);
```

## 接收消息

```java
WKIM.getInstance().getMsgManager().addOnNewMsgListener("key", (msgs) -> {
    for (WKMsg msg : msgs) {
        // 处理新消息
        Log.d("IM", "收到消息: " + msg.baseContentMsgModel.getDisplayContent());
    }
});
```

## 获取会话列表

```java
List<WKUIConversationMsg> conversations = WKIM.getInstance().getConversationManager().getAll();
for (WKUIConversationMsg conv : conversations) {
    Log.d("IM", "会话: " + conv.getWkChannel().channelName);
}
```

## 断开连接

```java
// 断开连接但保留登录状态
WKIM.getInstance().getConnectionManager().disconnect(false);

// 退出登录并断开连接
WKIM.getInstance().getConnectionManager().disconnect(true);
```

## 下一步

- [API 参考文档](API_REFERENCE.md) - 完整的 API 文档
- [消息类型](MESSAGE_TYPES.md) - 支持的消息类型
- [自定义消息](CUSTOM_MESSAGE.md) - 如何创建自定义消息类型
- [架构概览](ARCHITECTURE.md) - SDK 架构设计

