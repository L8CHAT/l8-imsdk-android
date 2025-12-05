package com.l8chat.imsdk.msgmodel;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * WKVideoContent 视频消息 Instrumented 测试
 * 测试需要 Android 环境的 encode/decode 功能
 */
@RunWith(AndroidJUnit4.class)
public class WKVideoContentInstrumentedTest {

    @Test
    public void testEncodeMsg() throws Exception {
        WKVideoContent videoContent = new WKVideoContent();
        videoContent.url = "https://example.com/video.mp4";
        videoContent.localPath = "/local/video.mp4";
        videoContent.cover = "https://example.com/cover.jpg";
        videoContent.coverLocalPath = "/local/cover.jpg";
        videoContent.width = 1920;
        videoContent.height = 1080;
        videoContent.size = 10485760L; // 10MB
        videoContent.second = 120L; // 2分钟
        
        JSONObject json = videoContent.encodeMsg();
        
        assertNotNull(json);
        assertEquals("https://example.com/video.mp4", json.getString("url"));
        assertEquals("/local/video.mp4", json.getString("localPath"));
        assertEquals("https://example.com/cover.jpg", json.getString("cover"));
        assertEquals("/local/cover.jpg", json.getString("coverLocalPath"));
        assertEquals(1920, json.getInt("width"));
        assertEquals(1080, json.getInt("height"));
    }

    @Test
    public void testDecodeMsg() throws Exception {
        JSONObject json = new JSONObject();
        json.put("url", "https://cdn.example.com/video.mp4");
        json.put("localPath", "/decoded/video.mp4");
        json.put("cover", "https://cdn.example.com/cover.jpg");
        json.put("coverLocalPath", "/decoded/cover.jpg");
        json.put("width", 3840);
        json.put("height", 2160);
        json.put("size", 52428800); // 50MB
        json.put("second", 300); // 5分钟
        
        WKVideoContent videoContent = new WKVideoContent();
        videoContent.decodeMsg(json);
        
        assertEquals("https://cdn.example.com/video.mp4", videoContent.url);
        assertEquals("/decoded/video.mp4", videoContent.localPath);
        assertEquals("https://cdn.example.com/cover.jpg", videoContent.cover);
        assertEquals("/decoded/cover.jpg", videoContent.coverLocalPath);
        assertEquals(3840, videoContent.width);
        assertEquals(2160, videoContent.height);
        assertEquals(52428800L, videoContent.size);
        assertEquals(300L, videoContent.second);
    }

    @Test
    public void testEncodeDecodeRoundTrip() throws Exception {
        WKVideoContent original = new WKVideoContent();
        original.url = "https://video.example.com/test.mp4";
        original.localPath = "/local/test.mp4";
        original.cover = "https://video.example.com/test_cover.jpg";
        original.coverLocalPath = "/local/test_cover.jpg";
        original.width = 1280;
        original.height = 720;
        original.second = 60L;
        original.size = 5242880L;
        
        // 编码
        JSONObject encoded = original.encodeMsg();
        
        // 解码
        WKVideoContent decoded = new WKVideoContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(original.url, decoded.url);
        assertEquals(original.localPath, decoded.localPath);
        assertEquals(original.cover, decoded.cover);
        assertEquals(original.coverLocalPath, decoded.coverLocalPath);
        assertEquals(original.width, decoded.width);
        assertEquals(original.height, decoded.height);
        assertEquals(original.second, decoded.second);
        assertEquals(original.size, decoded.size);
    }

    @Test
    public void testPartialDecode() throws Exception {
        JSONObject json = new JSONObject();
        json.put("url", "https://example.com/video.mp4");
        json.put("width", 1920);
        json.put("height", 1080);
        
        WKVideoContent videoContent = new WKVideoContent();
        videoContent.decodeMsg(json);
        
        assertEquals("https://example.com/video.mp4", videoContent.url);
        assertEquals(1920, videoContent.width);
        assertEquals(1080, videoContent.height);
        assertNull(videoContent.cover);
        assertEquals(0L, videoContent.size);
    }
}

