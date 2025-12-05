# 自定义消息类型

L8 IM SDK 支持创建自定义消息类型，满足业务特定需求。

## 创建自定义消息

### 步骤 1: 继承 WKMessageContent

```java
public class WKLocationContent extends WKMessageContent {
    
    // 定义消息类型（建议使用 1000 以上的值避免与内置类型冲突）
    public static final int MSG_TYPE = 1001;
    
    // 自定义属性
    public double latitude;
    public double longitude;
    public String address;
    public String title;
    
    // 必须提供无参构造方法
    public WKLocationContent() {
        this.type = MSG_TYPE;
    }
    
    // 带参数的构造方法
    public WKLocationContent(double latitude, double longitude, String address) {
        this.type = MSG_TYPE;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
    
    // 编码消息为 JSON（发送时调用）
    @Override
    public JSONObject encodeMsg() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            jsonObject.put("address", address);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    // 从 JSON 解码消息（接收时调用）
    @Override
    public WKMessageContent decodeMsg(JSONObject jsonObject) {
        if (jsonObject != null) {
            this.latitude = jsonObject.optDouble("latitude");
            this.longitude = jsonObject.optDouble("longitude");
            this.address = jsonObject.optString("address");
            this.title = jsonObject.optString("title");
        }
        return this;
    }
    
    // 搜索关键字
    @Override
    public String getSearchableWord() {
        return address;
    }
    
    // 会话列表显示内容
    @Override
    public String getDisplayContent() {
        return "[位置] " + (title != null ? title : address);
    }
}
```

### 步骤 2: 注册自定义消息

在 SDK 初始化后注册：

```java
// 初始化 SDK
WKIM.getInstance().init(context, uid, token);

// 注册自定义消息类型
WKIM.getInstance().getMsgManager().registerContentMsg(WKLocationContent.class);
```

### 步骤 3: 发送自定义消息

```java
WKLocationContent locationContent = new WKLocationContent(
    39.9042,    // 纬度
    116.4074,   // 经度
    "北京市东城区天安门广场"
);
locationContent.title = "天安门";

WKIM.getInstance().getMsgManager().send(locationContent, channelId, channelType);
```

---

## 媒体类型消息

如果自定义消息包含需要上传的文件，继承 `WKMediaMessageContent`：

```java
public class WKFileContent extends WKMediaMessageContent {
    
    public static final int MSG_TYPE = 1002;
    
    public String fileName;
    public long fileSize;
    
    public WKFileContent() {
        this.type = MSG_TYPE;
    }
    
    public WKFileContent(String localPath) {
        this.type = MSG_TYPE;
        this.localPath = localPath;  // 继承自 WKMediaMessageContent
    }
    
    @Override
    public JSONObject encodeMsg() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", url);           // 上传后的 URL
            jsonObject.put("localPath", localPath);
            jsonObject.put("fileName", fileName);
            jsonObject.put("fileSize", fileSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    @Override
    public WKMessageContent decodeMsg(JSONObject jsonObject) {
        if (jsonObject != null) {
            this.url = jsonObject.optString("url");
            this.localPath = jsonObject.optString("localPath");
            this.fileName = jsonObject.optString("fileName");
            this.fileSize = jsonObject.optLong("fileSize");
        }
        return this;
    }
    
    @Override
    public String getDisplayContent() {
        return "[文件] " + fileName;
    }
}
```

---

## 处理附件上传

对于媒体消息，需要设置附件上传监听：

```java
WKIM.getInstance().getMsgManager().addOnUploadAttachListener((msg, resultListener) -> {
    // msg.baseContentMsgModel 是消息内容对象
    if (msg.baseContentMsgModel instanceof WKMediaMessageContent) {
        WKMediaMessageContent mediaContent = (WKMediaMessageContent) msg.baseContentMsgModel;
        String localPath = mediaContent.localPath;
        
        // 上传文件到你的服务器
        uploadFile(localPath, (success, url) -> {
            if (success) {
                mediaContent.url = url;
                resultListener.onResult(true, msg);
            } else {
                resultListener.onResult(false, msg);
            }
        });
    }
});
```

---

## 下一步

- [消息类型](MESSAGE_TYPES.md) - 内置消息类型
- [API 参考](API_REFERENCE.md) - 完整 API 文档

