package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKChannelMember;

/**
 * 2020-02-01 15:19
 * 刷新频道成员信息
 */
public interface IRefreshChannelMember {
    void onRefresh(WKChannelMember channelMember, boolean isEnd);
}
