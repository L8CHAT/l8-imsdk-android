package com.l8chat.imsdk.msgmodel;

import com.l8chat.imsdk.message.type.WKMsgContentType;

public class WKFormatErrorContent extends WKMessageContent {
    public WKFormatErrorContent() {
        this.type = WKMsgContentType.WK_CONTENT_FORMAT_ERROR;
    }

    @Override
    public String getDisplayContent() {
        return "[消息格式错误]";
    }
}
