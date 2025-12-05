package com.l8chat.imsdk.entity;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * WKMsgSetting 消息设置测试
 */
public class WKMsgSettingTest {

    private WKMsgSetting setting;

    @Before
    public void setUp() {
        setting = new WKMsgSetting();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(setting);
    }

    @Test
    public void testDefaultValues() {
        assertEquals(0, setting.receipt);
        assertEquals(0, setting.topic);
        assertEquals(0, setting.stream);
    }

    @Test
    public void testReceiptSetting() {
        setting.receipt = 1;
        assertEquals(1, setting.receipt);
        
        setting.receipt = 0;
        assertEquals(0, setting.receipt);
    }

    @Test
    public void testTopicSetting() {
        setting.topic = 1;
        assertEquals(1, setting.topic);
        
        setting.topic = 0;
        assertEquals(0, setting.topic);
    }

    @Test
    public void testStreamSetting() {
        setting.stream = 1;
        assertEquals(1, setting.stream);
        
        setting.stream = 0;
        assertEquals(0, setting.stream);
    }

    @Test
    public void testAllSettingsEnabled() {
        setting.receipt = 1;
        setting.topic = 1;
        setting.stream = 1;
        
        assertEquals(1, setting.receipt);
        assertEquals(1, setting.topic);
        assertEquals(1, setting.stream);
    }

    @Test
    public void testDescribeContents() {
        assertEquals(0, setting.describeContents());
    }
}

