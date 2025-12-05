package com.l8chat.imsdk.msgmodel;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * WKImageContent 图片消息 Instrumented 测试
 * 测试需要 Android 环境的 encode/decode 功能
 */
@RunWith(AndroidJUnit4.class)
public class WKImageContentInstrumentedTest {

    @Test
    public void testEncodeMsg() throws Exception {
        WKImageContent imageContent = new WKImageContent("/local/path.jpg");
        imageContent.url = "https://example.com/image.jpg";
        imageContent.width = 1920;
        imageContent.height = 1080;
        
        JSONObject json = imageContent.encodeMsg();
        
        assertNotNull(json);
        assertEquals("https://example.com/image.jpg", json.getString("url"));
        assertEquals("/local/path.jpg", json.getString("localPath"));
        assertEquals(1920, json.getInt("width"));
        assertEquals(1080, json.getInt("height"));
    }

    @Test
    public void testDecodeMsg() throws Exception {
        JSONObject json = new JSONObject();
        json.put("url", "https://example.com/decoded.jpg");
        json.put("localPath", "/decoded/path.jpg");
        json.put("width", 800);
        json.put("height", 600);
        
        WKImageContent imageContent = new WKImageContent();
        imageContent.decodeMsg(json);
        
        assertEquals("https://example.com/decoded.jpg", imageContent.url);
        assertEquals("/decoded/path.jpg", imageContent.localPath);
        assertEquals(800, imageContent.width);
        assertEquals(600, imageContent.height);
    }

    @Test
    public void testEncodeDecodeRoundTrip() throws Exception {
        WKImageContent original = new WKImageContent("/original/path.png");
        original.url = "https://cdn.example.com/image.png";
        original.width = 1024;
        original.height = 768;
        
        // 编码
        JSONObject encoded = original.encodeMsg();
        
        // 解码
        WKImageContent decoded = new WKImageContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(original.url, decoded.url);
        assertEquals(original.localPath, decoded.localPath);
        assertEquals(original.width, decoded.width);
        assertEquals(original.height, decoded.height);
    }

    @Test
    public void testPartialDecode() throws Exception {
        // 只有部分字段的 JSON
        JSONObject json = new JSONObject();
        json.put("url", "https://example.com/partial.jpg");
        
        WKImageContent imageContent = new WKImageContent();
        imageContent.decodeMsg(json);
        
        assertEquals("https://example.com/partial.jpg", imageContent.url);
        assertEquals(0, imageContent.width);
        assertEquals(0, imageContent.height);
    }

    @Test
    public void testEncodeDecode4KImage() throws Exception {
        WKImageContent original = new WKImageContent("/4k/image.jpg");
        original.url = "https://cdn.example.com/4k.jpg";
        original.width = 3840;
        original.height = 2160;
        
        JSONObject encoded = original.encodeMsg();
        WKImageContent decoded = new WKImageContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(3840, decoded.width);
        assertEquals(2160, decoded.height);
    }
}

