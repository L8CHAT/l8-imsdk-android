package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKSyncChannelMsg;

/**
 * 2020-10-10 15:17
 */
public interface ISyncChannelMsgBack {
    void onBack(WKSyncChannelMsg syncChannelMsg);
}
