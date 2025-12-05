package com.l8chat.imsdk.msgmodel;

import com.l8chat.imsdk.message.type.WKMsgContentType;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKVideoContent 视频消息测试
 * 注意：由于 Android 的 JSONObject 在单元测试中是 mock 的，
 * encode/decode 相关测试需要在 instrumented test 中进行
 */
public class WKVideoContentTest {

    @Test
    public void testDefaultConstructor() {
        WKVideoContent videoContent = new WKVideoContent();
        assertNotNull(videoContent);
        assertEquals(WKMsgContentType.WK_VIDEO, videoContent.type);
    }

    @Test
    public void testGetDisplayContent() {
        WKVideoContent videoContent = new WKVideoContent();
        assertEquals("[视频]", videoContent.getDisplayContent());
    }

    @Test
    public void testGetSearchableWord() {
        WKVideoContent videoContent = new WKVideoContent();
        assertEquals("[视频]", videoContent.getSearchableWord());
    }

    @Test
    public void testContentType() {
        WKVideoContent videoContent = new WKVideoContent();
        assertEquals(WKMsgContentType.WK_VIDEO, videoContent.type);
        assertEquals(5, videoContent.type);
    }

    @Test
    public void testVideoDuration() {
        WKVideoContent videoContent = new WKVideoContent();

        // 测试短视频
        videoContent.second = 15L;
        assertEquals(15L, videoContent.second);

        // 测试长视频
        videoContent.second = 7200L; // 2小时
        assertEquals(7200L, videoContent.second);
    }

    @Test
    public void testVideoSize() {
        WKVideoContent videoContent = new WKVideoContent();

        // 测试大文件
        videoContent.size = 1073741824L; // 1GB
        assertEquals(1073741824L, videoContent.size);
    }

    @Test
    public void testVideoDimensions() {
        WKVideoContent videoContent = new WKVideoContent();

        // 测试 1080p
        videoContent.width = 1920;
        videoContent.height = 1080;
        assertEquals(1920, videoContent.width);
        assertEquals(1080, videoContent.height);

        // 测试 4K
        videoContent.width = 3840;
        videoContent.height = 2160;
        assertEquals(3840, videoContent.width);
        assertEquals(2160, videoContent.height);
    }

    @Test
    public void testCoverProperties() {
        WKVideoContent videoContent = new WKVideoContent();

        videoContent.cover = "https://example.com/cover.jpg";
        videoContent.coverLocalPath = "/local/cover.jpg";

        assertEquals("https://example.com/cover.jpg", videoContent.cover);
        assertEquals("/local/cover.jpg", videoContent.coverLocalPath);
    }

    @Test
    public void testUrlAndLocalPath() {
        WKVideoContent videoContent = new WKVideoContent();

        videoContent.url = "https://example.com/video.mp4";
        videoContent.localPath = "/local/video.mp4";

        assertEquals("https://example.com/video.mp4", videoContent.url);
        assertEquals("/local/video.mp4", videoContent.localPath);
    }

    @Test
    public void testDefaultValues() {
        WKVideoContent videoContent = new WKVideoContent();

        assertEquals(0, videoContent.width);
        assertEquals(0, videoContent.height);
        assertEquals(0L, videoContent.size);
        assertEquals(0L, videoContent.second);
        assertNull(videoContent.url);
        assertNull(videoContent.localPath);
        assertNull(videoContent.cover);
        assertNull(videoContent.coverLocalPath);
    }
}

