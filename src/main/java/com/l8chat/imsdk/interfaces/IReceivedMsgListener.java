package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.protocol.WKConnectAckMsg;
import com.l8chat.imsdk.protocol.WKDisconnectMsg;
import com.l8chat.imsdk.protocol.WKPongMsg;
import com.l8chat.imsdk.protocol.WKSendAckMsg;

/**
 * 2019-11-10 17:03
 * 接受通讯协议消息
 */
public interface IReceivedMsgListener {
    /**
     * 登录状态消息
     *
     * @param connectAckMsg 状态
     */
    void loginStatusMsg(WKConnectAckMsg connectAckMsg);

    /**
     * 心跳消息
     */
    void pongMsg(WKPongMsg pongMsg);

    /**
     * 被踢消息
     */
    void kickMsg(WKDisconnectMsg disconnectMsg);

    /**
     * 发送消息状态消息
     *
     * @param sendAckMsg ack
     */
    void sendAckMsg(WKSendAckMsg sendAckMsg);

    /**
     * 重连
     */
    void reconnect();
}
