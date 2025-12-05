# API 参考文档

## WKIM 主入口

`WKIM` 是 SDK 的主入口类，采用单例模式。

### 初始化

```java
// 获取实例
WKIM wkim = WKIM.getInstance();

// 初始化 SDK
wkim.init(Context context, String uid, String token);

// 设置调试模式
wkim.setDebug(boolean isDebug);

// 设置日志写入
wkim.setWriteLog(boolean isWriteLog);

// 设置文件缓存目录
wkim.setFileCacheDir(String fileDir);

// 设置设备ID
wkim.setDeviceId(String deviceID);

// 获取版本号
String version = wkim.getVersion();
```

### 管理器获取

```java
// 消息管理器
MsgManager msgManager = wkim.getMsgManager();

// 连接管理器
ConnectionManager connectionManager = wkim.getConnectionManager();

// 频道管理器
ChannelManager channelManager = wkim.getChannelManager();

// 会话管理器
ConversationManager conversationManager = wkim.getConversationManager();

// 频道成员管理器
ChannelMembersManager membersManager = wkim.getChannelMembersManager();

// 提醒管理器
ReminderManager reminderManager = wkim.getReminderManager();

// CMD 管理器
CMDManager cmdManager = wkim.getCMDManager();

// 机器人管理器
RobotManager robotManager = wkim.getRobotManager();
```

---

## ConnectionManager 连接管理

### 连接控制

```java
// 发起连接
connectionManager.connection();

// 断开连接
// isLogout: true-退出登录, false-仅断开连接
connectionManager.disconnect(boolean isLogout);
```

### 事件监听

```java
// 监听连接状态
connectionManager.addOnConnectionStatusListener(String key, IConnectionStatus listener);
connectionManager.removeOnConnectionStatusListener(String key);

// 监听获取服务器地址
connectionManager.addOnGetIpAndPortListener(IGetIpAndPort listener);
```

### 连接状态常量 (WKConnectStatus)

| 常量 | 值 | 说明 |
|------|---|------|
| `fail` | 0 | 连接失败 |
| `success` | 1 | 连接成功 |
| `kicked` | 2 | 被踢下线 |
| `syncMsg` | 3 | 同步消息中 |
| `connecting` | 4 | 连接中 |
| `noNetwork` | 5 | 无网络 |
| `syncCompleted` | 6 | 同步完成 |

---

## MsgManager 消息管理

### 发送消息

```java
// 发送消息
msgManager.send(WKMessageContent content, String channelId, byte channelType);

// 带选项发送
msgManager.send(WKMessageContent content, String channelId, byte channelType, WKSendOptions options);

// 创建客户端消息ID
String clientMsgNo = msgManager.createClientMsgNO();
```

### 消息查询

```java
// 获取历史消息
msgManager.getOrSyncHistoryMessages(
    String channelId,
    byte channelType,
    long oldestOrderSeq,
    boolean contain,
    int pullMode,      // 0:向下拉取 1:向上拉取
    int limit,
    long aroundMsgOrderSeq,
    IGetOrSyncHistoryMsgBack callback
);

// 通过消息ID查询
WKMsg msg = msgManager.getWithMessageID(String messageID);

// 通过客户端消息ID查询
WKMsg msg = msgManager.getWithClientMsgNO(String clientMsgNo);

// 搜索消息
List<WKMsg> msgs = msgManager.searchWithChannel(String searchKey, String channelID, byte channelType);
List<WKMessageSearchResult> results = msgManager.search(String searchKey);
```

### 消息操作

```java
// 保存消息
msgManager.saveMsg(WKMsg msg);

// 保存并更新会话
msgManager.saveAndUpdateConversationMsg(WKMsg msg, boolean addRedDots);

// 删除消息
msgManager.deleteWithClientMsgNO(String clientMsgNo);
msgManager.deleteWithClientMsgNos(List<String> clientMsgNos);
msgManager.deleteWithMessageID(String messageID);

// 清空频道消息
msgManager.clearWithChannel(String channelId, byte channelType);

// 更新消息内容
msgManager.updateContentAndRefresh(String clientMsgNo, WKMessageContent model, boolean isRefreshUI);
```

### 事件监听

```java
// 新消息监听
msgManager.addOnNewMsgListener(String key, INewMsgListener listener);
msgManager.removeNewMsgListener(String key);

// 消息刷新监听
msgManager.addOnRefreshMsgListener(String key, IRefreshMsg listener);
msgManager.removeRefreshMsgListener(String key);

// 消息删除监听
msgManager.addOnDeleteMsgListener(String key, IDeleteMsgListener listener);
msgManager.removeDeleteMsgListener(String key);

// 发送回调监听
msgManager.addOnSendMsgCallback(String key, ISendMsgCallBackListener listener);
msgManager.removeSendMsgCallBack(String key);

// 发送ACK监听
msgManager.addOnSendMsgAckListener(String key, ISendACK listener);
msgManager.removeSendMsgAckListener(String key);

// 附件上传监听
msgManager.addOnUploadAttachListener(IUploadAttachmentListener listener);

// 离线消息同步监听
msgManager.addOnSyncOfflineMsgListener(ISyncOfflineMsgListener listener);

// 频道消息同步监听
msgManager.addOnSyncChannelMsgListener(ISyncChannelMsgListener listener);
```

### 自定义消息注册

```java
// 注册自定义消息类型
msgManager.registerContentMsg(Class<? extends WKMessageContent> contentMsg);
```

---

## ConversationManager 会话管理

### 会话查询

```java
// 获取所有会话
List<WKUIConversationMsg> list = conversationManager.getAll();

// 异步获取所有会话
conversationManager.getAll(IAllConversations callback);

// 获取指定频道会话
WKConversationMsg msg = conversationManager.getWithChannel(String channelID, byte channelType);

// 获取UI会话对象
WKUIConversationMsg uiMsg = conversationManager.getUIConversationMsg(String channelID, byte channelType);
```

### 会话操作

```java
// 删除会话
conversationManager.deleteWitchChannel(String channelId, byte channelType);

// 清空所有会话
conversationManager.clearAll();

// 更新红点
conversationManager.updateRedDot(String channelID, byte channelType, int redDot);
```

### 事件监听

```java
// 会话刷新监听
conversationManager.addOnRefreshMsgListener(String key, IRefreshConversationMsg listener);
conversationManager.removeOnRefreshMsgListener(String key);

// 会话列表刷新监听
conversationManager.addOnRefreshMsgListListener(String key, IRefreshConversationMsgList listener);
conversationManager.removeOnRefreshMsgListListener(String key);

// 会话删除监听
conversationManager.addOnDeleteMsgListener(String key, IDeleteConversationMsg listener);
conversationManager.removeOnDeleteMsgListener(String key);

// 会话同步监听
conversationManager.addOnSyncConversationListener(ISyncConversationChat listener);
```

---

## ChannelManager 频道管理

### 频道查询

```java
// 获取频道信息
WKChannel channel = channelManager.getChannel(String channelID, byte channelType);

// 从网络获取频道信息
channelManager.fetchChannelInfo(String channelID, byte channelType);

// 搜索频道
List<WKChannelSearchResult> results = channelManager.search(String keyword);
List<WKChannel> channels = channelManager.searchWithChannelType(String keyword, byte channelType);
```

### 频道操作

```java
// 保存或更新频道
channelManager.saveOrUpdateChannel(WKChannel channel);
channelManager.saveOrUpdateChannels(List<WKChannel> list);

// 更新频道属性
channelManager.updateName(String channelID, byte channelType, String name);
channelManager.updateMute(String channelID, byte channelType, int isMute);
channelManager.updateTop(String channelID, byte channelType, int top);
channelManager.updateRemark(String channelID, byte channelType, String remark);
channelManager.updateFollow(String channelID, byte channelType, int follow);
channelManager.updateStatus(String channelID, byte channelType, int status);
```

### 事件监听

```java
// 频道刷新监听
channelManager.addOnRefreshChannelInfo(String key, IRefreshChannel listener);
channelManager.removeRefreshChannelInfo(String key);

// 获取频道信息监听
channelManager.addOnGetChannelInfoListener(IGetChannelInfo listener);
```

---

## ChannelMembersManager 频道成员管理

### 成员查询

```java
// 获取成员
WKChannelMember member = membersManager.getMember(String channelID, byte channelType, String uid);

// 获取所有成员
List<WKChannelMember> members = membersManager.getMembers(String channelID, byte channelType);

// 分页获取成员
membersManager.getWithPageOrSearch(String channelID, byte channelType, String searchKey, int page, int limit, IGetChannelMemberListResult callback);

// 获取成员数量
int count = membersManager.getMemberCount(String channelID, byte channelType);

// 按角色获取成员
List<WKChannelMember> members = membersManager.getWithRole(String channelID, byte channelType, int role);
```

### 成员操作

```java
// 保存成员
membersManager.save(WKChannelMember member);
membersManager.save(List<WKChannelMember> list);

// 删除成员
membersManager.delete(List<WKChannelMember> list);

// 更新成员属性
membersManager.updateRemarkName(String channelID, byte channelType, String uid, String remarkName);
membersManager.updateMemberName(String channelID, byte channelType, String uid, String name);
membersManager.updateMemberStatus(String channelId, byte channelType, String uid, int status);
```

### 事件监听

```java
// 成员刷新监听
membersManager.addOnRefreshChannelMemberInfo(String key, IRefreshChannelMember listener);
membersManager.removeRefreshChannelMemberInfo(String key);

// 成员添加监听
membersManager.addOnAddChannelMemberListener(String key, IAddChannelMemberListener listener);
membersManager.removeAddChannelMemberListener(String key);

// 成员移除监听
membersManager.addOnRemoveChannelMemberListener(String key, IRemoveChannelMember listener);
membersManager.removeRemoveChannelMemberListener(String key);
```

---

## 频道类型常量 (WKChannelType)

| 常量 | 值 | 说明 |
|------|---|------|
| `PERSONAL` | 1 | 个人/单聊 |
| `GROUP` | 2 | 群组 |
| `CUSTOMER_SERVICE` | 3 | 客服 |
| `COMMUNITY` | 4 | 社区 |
| `COMMUNITY_TOPIC` | 5 | 社区话题 |

---

## 消息发送状态 (WKSendMsgResult)

| 常量 | 说明 |
|------|------|
| `send_loading` | 发送中 |
| `send_success` | 发送成功 |
| `send_fail` | 发送失败 |
| `send_no_relation` | 非好友关系 |
| `send_blacklist` | 在黑名单中 |

---

## 下一步

- [快速入门](QUICK_START.md) - 快速集成指南
- [消息类型](MESSAGE_TYPES.md) - 支持的消息类型
- [自定义消息](CUSTOM_MESSAGE.md) - 创建自定义消息
- [架构概览](ARCHITECTURE.md) - SDK 架构设计

