package com.l8chat.imsdk.interfaces;

import com.l8chat.imsdk.entity.WKUIConversationMsg;

import java.util.List;

public interface IAllConversations {
    void onResult(List<WKUIConversationMsg> list);
}
