package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKChannelMember;

/**
 * 2019-12-01 15:54
 * 频道成员
 */
public interface IChannelMemberInfoListener {
    void onResult(WKChannelMember channelMember);
}
