package com.l8chat.imsdk.interfaces;

import com.l8chat.imsdk.entity.WKMsg;

/**
 * 5/12/21 2:02 PM
 * 发送消息ack监听
 */
public interface ISendACK {
    void msgACK(WKMsg msg);
}
