package com.l8chat.imsdk.interfaces;

import com.l8chat.imsdk.entity.WKUIConversationMsg;

import java.util.List;

public interface IRefreshConversationMsgList {
    void onRefresh(List<WKUIConversationMsg> list);
}
