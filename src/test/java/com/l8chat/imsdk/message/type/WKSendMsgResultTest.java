package com.l8chat.imsdk.message.type;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKSendMsgResult 发送消息结果测试
 */
public class WKSendMsgResultTest {

    @Test
    public void testSendLoading() {
        assertEquals(0, WKSendMsgResult.send_loading);
    }

    @Test
    public void testSendSuccess() {
        assertEquals(1, WKSendMsgResult.send_success);
    }

    @Test
    public void testSendFail() {
        assertEquals(2, WKSendMsgResult.send_fail);
    }

    @Test
    public void testNoRelation() {
        assertEquals(3, WKSendMsgResult.no_relation);
    }

    @Test
    public void testBlackList() {
        assertEquals(4, WKSendMsgResult.black_list);
    }

    @Test
    public void testNotOnWhiteList() {
        assertEquals(13, WKSendMsgResult.not_on_white_list);
    }

    @Test
    public void testAllResultsUnique() {
        int[] results = {
            WKSendMsgResult.send_loading,
            WKSendMsgResult.send_success,
            WKSendMsgResult.send_fail,
            WKSendMsgResult.no_relation,
            WKSendMsgResult.black_list,
            WKSendMsgResult.not_on_white_list
        };
        
        // 确保所有结果值不重复
        for (int i = 0; i < results.length; i++) {
            for (int j = i + 1; j < results.length; j++) {
                assertNotEquals("Send message results should be unique", results[i], results[j]);
            }
        }
    }

    @Test
    public void testSuccessIsPositive() {
        assertTrue(WKSendMsgResult.send_success > 0);
    }

    @Test
    public void testLoadingIsZero() {
        assertEquals(0, WKSendMsgResult.send_loading);
    }

    @Test
    public void testErrorCodesArePositive() {
        assertTrue(WKSendMsgResult.send_fail > 0);
        assertTrue(WKSendMsgResult.no_relation > 0);
        assertTrue(WKSendMsgResult.black_list > 0);
        assertTrue(WKSendMsgResult.not_on_white_list > 0);
    }
}

