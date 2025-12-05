package com.l8chat.imsdk.message.type;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKConnectStatus 连接状态测试
 */
public class WKConnectStatusTest {

    @Test
    public void testFail() {
        assertEquals(0, WKConnectStatus.fail);
    }

    @Test
    public void testSuccess() {
        assertEquals(1, WKConnectStatus.success);
    }

    @Test
    public void testKicked() {
        assertEquals(2, WKConnectStatus.kicked);
    }

    @Test
    public void testSyncMsg() {
        assertEquals(3, WKConnectStatus.syncMsg);
    }

    @Test
    public void testConnecting() {
        assertEquals(4, WKConnectStatus.connecting);
    }

    @Test
    public void testNoNetwork() {
        assertEquals(5, WKConnectStatus.noNetwork);
    }

    @Test
    public void testSyncCompleted() {
        assertEquals(6, WKConnectStatus.syncCompleted);
    }

    @Test
    public void testAllStatusesUnique() {
        int[] statuses = {
            WKConnectStatus.fail,
            WKConnectStatus.success,
            WKConnectStatus.kicked,
            WKConnectStatus.syncMsg,
            WKConnectStatus.connecting,
            WKConnectStatus.noNetwork,
            WKConnectStatus.syncCompleted
        };
        
        // 确保所有状态值不重复
        for (int i = 0; i < statuses.length; i++) {
            for (int j = i + 1; j < statuses.length; j++) {
                assertNotEquals("Connect statuses should be unique", statuses[i], statuses[j]);
            }
        }
    }

    @Test
    public void testFailIsZero() {
        assertEquals(0, WKConnectStatus.fail);
    }

    @Test
    public void testSuccessIsPositive() {
        assertTrue(WKConnectStatus.success > 0);
    }

    @Test
    public void testStatusesAreNonNegative() {
        assertTrue(WKConnectStatus.fail >= 0);
        assertTrue(WKConnectStatus.success >= 0);
        assertTrue(WKConnectStatus.kicked >= 0);
        assertTrue(WKConnectStatus.syncMsg >= 0);
        assertTrue(WKConnectStatus.connecting >= 0);
        assertTrue(WKConnectStatus.noNetwork >= 0);
        assertTrue(WKConnectStatus.syncCompleted >= 0);
    }
}

