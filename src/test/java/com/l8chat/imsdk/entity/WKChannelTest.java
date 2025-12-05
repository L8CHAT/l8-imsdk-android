package com.l8chat.imsdk.entity;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * WKChannel 频道实体测试
 */
public class WKChannelTest {

    private WKChannel channel;

    @Before
    public void setUp() {
        channel = new WKChannel("test_channel_id", WKChannelType.GROUP);
    }

    @Test
    public void testChannelCreation() {
        assertNotNull(channel);
        assertEquals("test_channel_id", channel.channelID);
        assertEquals(WKChannelType.GROUP, channel.channelType);
    }

    @Test
    public void testDefaultConstructor() {
        WKChannel emptyChannel = new WKChannel();
        assertNotNull(emptyChannel);
    }

    @Test
    public void testChannelProperties() {
        channel.channelName = "测试群组";
        channel.channelRemark = "这是一个测试群";
        channel.avatar = "https://example.com/avatar.png";
        channel.top = 1;
        channel.mute = 0;
        channel.save = 1;
        channel.status = 1;
        channel.follow = 1;
        channel.online = 1;
        channel.robot = 0;
        channel.category = "group";

        assertEquals("测试群组", channel.channelName);
        assertEquals("这是一个测试群", channel.channelRemark);
        assertEquals("https://example.com/avatar.png", channel.avatar);
        assertEquals(1, channel.top);
        assertEquals(0, channel.mute);
        assertEquals(1, channel.save);
        assertEquals(1, channel.status);
        assertEquals(1, channel.follow);
        assertEquals(1, channel.online);
        assertEquals(0, channel.robot);
        assertEquals("group", channel.category);
    }

    @Test
    public void testPersonalChannel() {
        WKChannel personalChannel = new WKChannel("user_123", WKChannelType.PERSONAL);
        assertEquals("user_123", personalChannel.channelID);
        assertEquals(WKChannelType.PERSONAL, personalChannel.channelType);
    }

    @Test
    public void testGroupChannel() {
        WKChannel groupChannel = new WKChannel("group_456", WKChannelType.GROUP);
        assertEquals("group_456", groupChannel.channelID);
        assertEquals(WKChannelType.GROUP, groupChannel.channelType);
    }

    @Test
    public void testCustomerServiceChannel() {
        WKChannel csChannel = new WKChannel("cs_789", WKChannelType.CUSTOMER_SERVICE);
        assertEquals("cs_789", csChannel.channelID);
        assertEquals(WKChannelType.CUSTOMER_SERVICE, csChannel.channelType);
    }

    @Test
    public void testCommunityChannel() {
        WKChannel communityChannel = new WKChannel("community_001", WKChannelType.COMMUNITY);
        assertEquals("community_001", communityChannel.channelID);
        assertEquals(WKChannelType.COMMUNITY, communityChannel.channelType);
    }

    @Test
    public void testChannelTimestamps() {
        // 确保创建时间和更新时间已设置
        assertNotNull(channel.createdAt);
        assertNotNull(channel.updatedAt);
        assertFalse(channel.createdAt.isEmpty());
        assertFalse(channel.updatedAt.isEmpty());
    }

    @Test
    public void testChannelVersion() {
        channel.version = 123456L;
        assertEquals(123456L, channel.version);
    }

    @Test
    public void testFlameSettings() {
        channel.flame = 1;
        channel.flameSecond = 30;
        
        assertEquals(1, channel.flame);
        assertEquals(30, channel.flameSecond);
    }

    @Test
    public void testParentChannel() {
        channel.parentChannelID = "parent_community";
        channel.parentChannelType = WKChannelType.COMMUNITY;
        
        assertEquals("parent_community", channel.parentChannelID);
        assertEquals(WKChannelType.COMMUNITY, channel.parentChannelType);
    }
}

