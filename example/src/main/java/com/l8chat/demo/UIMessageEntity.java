package com.l8chat.demo;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.l8chat.imsdk.entity.WKMsg;

class UIMessageEntity implements MultiItemEntity {
    public WKMsg msg;
    public int itemType;

    UIMessageEntity(WKMsg msg, int itemType) {
        this.itemType = itemType;
        this.msg = msg;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
