package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKChannel;

/**
 * 2020-02-01 14:38
 * 刷新频道
 */
public interface IRefreshChannel {
    void onRefreshChannel(WKChannel channel, boolean isEnd);
}
