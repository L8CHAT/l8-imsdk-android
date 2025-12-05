package com.l8chat.imsdk.interfaces;

import com.l8chat.imsdk.entity.WKChannelMember;

import java.util.List;

public interface IGetChannelMemberListResult {
    public void onResult(List<WKChannelMember> list, boolean isRemote);
}
