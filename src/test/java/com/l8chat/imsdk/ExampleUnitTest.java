package com.l8chat.imsdk;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * WKIM 单例测试
 */
public class ExampleUnitTest {

    @Test
    public void testWKIMSingleton() {
        // 测试单例模式
        WKIM instance1 = WKIM.getInstance();
        WKIM instance2 = WKIM.getInstance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    public void testVersion() {
        String version = WKIM.getInstance().getVersion();
        assertNotNull(version);
        assertEquals("V1.0.0", version);
    }

    @Test
    public void testDebugMode() {
        WKIM wkim = WKIM.getInstance();

        // 默认应该是 false
        assertFalse(wkim.isDebug());

        // 设置为 true
        wkim.setDebug(true);
        assertTrue(wkim.isDebug());

        // 恢复为 false
        wkim.setDebug(false);
        assertFalse(wkim.isDebug());
    }

    @Test
    public void testWriteLog() {
        WKIM wkim = WKIM.getInstance();

        // 默认应该是 false
        assertFalse(wkim.isWriteLog());

        // 设置为 true
        wkim.setWriteLog(true);
        assertTrue(wkim.isWriteLog());

        // 恢复为 false
        wkim.setWriteLog(false);
        assertFalse(wkim.isWriteLog());
    }

    @Test
    public void testDeviceId() {
        WKIM wkim = WKIM.getInstance();

        // 设置 deviceId
        String testDeviceId = "test-device-123";
        wkim.setDeviceId(testDeviceId);
        assertEquals(testDeviceId, wkim.getDeviceID());
    }
}