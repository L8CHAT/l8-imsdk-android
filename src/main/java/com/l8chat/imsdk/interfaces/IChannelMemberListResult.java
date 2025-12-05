package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKChannelMember;

import java.util.List;

public interface IChannelMemberListResult {
    void onResult(List<WKChannelMember> list);
}
