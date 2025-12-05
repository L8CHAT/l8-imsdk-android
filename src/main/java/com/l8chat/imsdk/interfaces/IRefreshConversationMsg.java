package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKUIConversationMsg;

/**
 * 2020-02-21 11:11
 * 刷新最近会话
 */
public interface IRefreshConversationMsg {
    void onRefreshConversationMsg(WKUIConversationMsg wkuiConversationMsg, boolean isEnd);
}
