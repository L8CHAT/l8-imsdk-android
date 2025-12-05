package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKChannelMember;

import java.util.List;

/**
 * 2020-02-01 16:43
 * 移除频道成员
 */
public interface IRemoveChannelMember {
    void onRemoveMembers(List<WKChannelMember> list);
}
