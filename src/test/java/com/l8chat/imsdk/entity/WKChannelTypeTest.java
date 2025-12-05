package com.l8chat.imsdk.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKChannelType 频道类型测试
 */
public class WKChannelTypeTest {

    @Test
    public void testPersonalChannelType() {
        assertEquals(1, WKChannelType.PERSONAL);
    }

    @Test
    public void testGroupChannelType() {
        assertEquals(2, WKChannelType.GROUP);
    }

    @Test
    public void testCustomerServiceChannelType() {
        assertEquals(3, WKChannelType.CUSTOMER_SERVICE);
    }

    @Test
    public void testCommunityChannelType() {
        assertEquals(4, WKChannelType.COMMUNITY);
    }

    @Test
    public void testCommunityTopicChannelType() {
        assertEquals(5, WKChannelType.COMMUNITY_TOPIC);
    }

    @Test
    public void testChannelTypeValues() {
        // 确保所有频道类型值不重复
        byte[] types = {
            WKChannelType.PERSONAL,
            WKChannelType.GROUP,
            WKChannelType.CUSTOMER_SERVICE,
            WKChannelType.COMMUNITY,
            WKChannelType.COMMUNITY_TOPIC
        };
        
        for (int i = 0; i < types.length; i++) {
            for (int j = i + 1; j < types.length; j++) {
                assertNotEquals("Channel types should be unique", types[i], types[j]);
            }
        }
    }
}

