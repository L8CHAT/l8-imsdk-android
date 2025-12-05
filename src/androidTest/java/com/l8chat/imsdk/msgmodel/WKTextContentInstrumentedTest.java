package com.l8chat.imsdk.msgmodel;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.l8chat.imsdk.message.type.WKMsgContentType;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * WKTextContent 文本消息 Instrumented 测试
 * 测试需要 Android 环境的 encode/decode 功能
 */
@RunWith(AndroidJUnit4.class)
public class WKTextContentInstrumentedTest {

    @Test
    public void testEncodeMsg() throws Exception {
        WKTextContent textContent = new WKTextContent("测试消息");
        JSONObject json = textContent.encodeMsg();
        
        assertNotNull(json);
        assertTrue(json.has("content"));
        assertEquals("测试消息", json.getString("content"));
    }

    @Test
    public void testDecodeMsg() throws Exception {
        JSONObject json = new JSONObject();
        json.put("content", "解码测试");
        
        WKTextContent textContent = new WKTextContent();
        textContent.decodeMsg(json);
        
        assertEquals("解码测试", textContent.content);
    }

    @Test
    public void testEncodeDecodeRoundTrip() throws Exception {
        String originalMessage = "Round trip 测试消息 🎉";
        WKTextContent original = new WKTextContent(originalMessage);
        
        // 编码
        JSONObject encoded = original.encodeMsg();
        assertNotNull(encoded);
        
        // 解码
        WKTextContent decoded = new WKTextContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(originalMessage, decoded.content);
    }

    @Test
    public void testEncodeWithNullContent() throws Exception {
        WKTextContent textContent = new WKTextContent();
        textContent.content = null;
        
        JSONObject json = textContent.encodeMsg();
        assertNotNull(json);
    }

    @Test
    public void testDecodeWithEmptyJson() throws Exception {
        WKTextContent textContent = new WKTextContent();
        JSONObject emptyJson = new JSONObject();
        textContent.decodeMsg(emptyJson);
        
        assertNull(textContent.content);
    }

    @Test
    public void testEncodeDecodeWithSpecialCharacters() throws Exception {
        String specialMessage = "特殊字符: <>&\"'\\n\\t emoji: 👋🌍";
        WKTextContent original = new WKTextContent(specialMessage);
        
        JSONObject encoded = original.encodeMsg();
        WKTextContent decoded = new WKTextContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(specialMessage, decoded.content);
    }

    @Test
    public void testEncodeDecodeWithLongContent() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("Long message content. ");
        }
        String longMessage = sb.toString();
        
        WKTextContent original = new WKTextContent(longMessage);
        JSONObject encoded = original.encodeMsg();
        WKTextContent decoded = new WKTextContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(longMessage, decoded.content);
    }
}

