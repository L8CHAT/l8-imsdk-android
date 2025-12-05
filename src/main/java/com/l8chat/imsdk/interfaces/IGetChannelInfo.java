package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKChannel;

/**
 * 2019-12-01 15:40
 * 获取频道信息
 */
public interface IGetChannelInfo {
    WKChannel onGetChannelInfo(String channelId, byte channelType, IChannelInfoListener iChannelInfoListener);
}
