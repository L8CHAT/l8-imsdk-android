package com.l8chat.imsdk.interfaces;


import com.l8chat.imsdk.entity.WKCMD;

/**
 * 2/3/21 2:23 PM
 * cmd监听
 */
public interface ICMDListener {
    void onMsg(WKCMD wkcmd);
}
