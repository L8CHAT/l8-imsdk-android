# 消息类型

L8 IM SDK 支持多种内置消息类型，同时支持自定义消息类型扩展。

## 内置消息类型

### 消息类型常量 (WKMsgContentType)

| 常量 | 值 | 说明 |
|------|---|------|
| `WK_TEXT` | 1 | 文本消息 |
| `WK_IMAGE` | 2 | 图片消息 |
| `WK_GIF` | 3 | GIF 动图 |
| `WK_VOICE` | 4 | 语音消息 |
| `WK_VIDEO` | 5 | 视频消息 |
| `WK_LOCATION` | 6 | 位置消息 |
| `WK_CARD` | 7 | 名片消息 |
| `WK_FILE` | 8 | 文件消息 |
| `WK_MULTIPLE_FORWARD` | 11 | 合并转发消息 |
| `WK_VECTOR_STICKER` | 12 | 矢量贴图 |
| `WK_EMOJI_STICKER` | 13 | Emoji 贴图 |

### 特殊类型

| 常量 | 值 | 说明 |
|------|---|------|
| `WK_CONTENT_FORMAT_ERROR` | 97 | 内容格式错误 |
| `WK_SIGNAL_DECRYPT_ERROR` | 98 | Signal 解密失败 |
| `WK_INSIDE_MSG` | 99 | 内部消息（不存储） |

---

## 文本消息 (WKTextContent)

最基础的消息类型，用于发送纯文本内容。

### 属性

| 属性 | 类型 | 说明 |
|------|------|------|
| `content` | String | 文本内容 |

### 使用示例

```java
// 创建文本消息
WKTextContent textContent = new WKTextContent("Hello World!");

// 发送消息
WKIM.getInstance().getMsgManager().send(textContent, channelId, channelType);
```

---

## 图片消息 (WKImageContent)

用于发送图片，继承自 `WKMediaMessageContent`。

### 属性

| 属性 | 类型 | 说明 |
|------|------|------|
| `localPath` | String | 本地文件路径 |
| `url` | String | 远程 URL |
| `width` | int | 图片宽度 |
| `height` | int | 图片高度 |

### 使用示例

```java
// 创建图片消息
WKImageContent imageContent = new WKImageContent("/sdcard/photo.jpg");
imageContent.width = 1080;
imageContent.height = 720;

// 发送消息
WKIM.getInstance().getMsgManager().send(imageContent, channelId, channelType);
```

---

## 视频消息 (WKVideoContent)

用于发送视频，继承自 `WKMediaMessageContent`。

### 属性

| 属性 | 类型 | 说明 |
|------|------|------|
| `localPath` | String | 本地文件路径 |
| `url` | String | 远程 URL |
| `cover` | String | 封面图 URL |
| `coverLocalPath` | String | 封面本地路径 |
| `width` | int | 视频宽度 |
| `height` | int | 视频高度 |
| `second` | long | 视频时长（秒） |
| `size` | long | 文件大小 |

### 使用示例

```java
// 创建视频消息
WKVideoContent videoContent = new WKVideoContent();
videoContent.localPath = "/sdcard/video.mp4";
videoContent.coverLocalPath = "/sdcard/cover.jpg";
videoContent.width = 1920;
videoContent.height = 1080;
videoContent.second = 60;

// 发送消息
WKIM.getInstance().getMsgManager().send(videoContent, channelId, channelType);
```

---

## 语音消息 (WKVoiceContent)

用于发送语音消息，继承自 `WKMediaMessageContent`。

### 属性

| 属性 | 类型 | 说明 |
|------|------|------|
| `localPath` | String | 本地文件路径 |
| `url` | String | 远程 URL |
| `timeTrad` | int | 语音时长（秒） |
| `waveform` | String | 波形数据 |

### 使用示例

```java
// 创建语音消息
WKVoiceContent voiceContent = new WKVoiceContent();
voiceContent.localPath = "/sdcard/voice.amr";
voiceContent.timeTrad = 10;

// 发送消息
WKIM.getInstance().getMsgManager().send(voiceContent, channelId, channelType);
```

---

## 消息基类 (WKMessageContent)

所有消息类型的基类，包含通用属性。

### 通用属性

| 属性 | 类型 | 说明 |
|------|------|------|
| `type` | int | 消息类型 |
| `content` | String | 内容 |
| `mentionAll` | int | 是否@所有人 |
| `mentionInfo` | WKMentionInfo | @成员信息 |
| `reply` | WKReply | 回复消息 |
| `entities` | List<WKMsgEntity> | 实体列表 |

### 核心方法

```java
// 编码消息为 JSON
JSONObject encodeMsg();

// 从 JSON 解码消息
WKMessageContent decodeMsg(JSONObject jsonObject);

// 获取搜索关键字
String getSearchableWord();

// 获取显示内容（用于会话列表）
String getDisplayContent();
```

---

## 下一步

- [自定义消息](CUSTOM_MESSAGE.md) - 创建自定义消息类型
- [API 参考](API_REFERENCE.md) - 完整 API 文档

