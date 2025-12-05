package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKMsg;

import java.util.List;

/**
 * 2019-11-18 11:44
 * 新消息监听
 */
public interface INewMsgListener {
    void newMsg(List<WKMsg> msgs);
}
