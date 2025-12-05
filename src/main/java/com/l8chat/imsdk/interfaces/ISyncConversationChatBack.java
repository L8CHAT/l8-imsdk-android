package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKSyncChat;

/**
 * 2020-10-09 14:43
 * 同步消息返回
 */
public interface ISyncConversationChatBack {
    void onBack(WKSyncChat syncChat);
}
