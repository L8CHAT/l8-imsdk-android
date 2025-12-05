package com.l8chat.imsdk.msgmodel;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * WKVoiceContent 语音消息 Instrumented 测试
 * 测试需要 Android 环境的 encode/decode 功能
 */
@RunWith(AndroidJUnit4.class)
public class WKVoiceContentInstrumentedTest {

    @Test
    public void testEncodeMsg() throws Exception {
        WKVoiceContent voiceContent = new WKVoiceContent("/local/voice.amr", 45);
        voiceContent.url = "https://example.com/voice.amr";
        voiceContent.waveform = "base64_waveform_data";
        
        JSONObject json = voiceContent.encodeMsg();
        
        assertNotNull(json);
        assertEquals("/local/voice.amr", json.getString("localPath"));
        assertEquals(45, json.getInt("timeTrad"));
        assertEquals("https://example.com/voice.amr", json.getString("url"));
        assertEquals("base64_waveform_data", json.getString("waveform"));
    }

    @Test
    public void testDecodeMsg() throws Exception {
        JSONObject json = new JSONObject();
        json.put("localPath", "/decoded/voice.amr");
        json.put("timeTrad", 60);
        json.put("url", "https://cdn.example.com/voice.amr");
        json.put("waveform", "decoded_waveform");
        
        WKVoiceContent voiceContent = new WKVoiceContent();
        voiceContent.decodeMsg(json);
        
        assertEquals("/decoded/voice.amr", voiceContent.localPath);
        assertEquals(60, voiceContent.timeTrad);
        assertEquals("https://cdn.example.com/voice.amr", voiceContent.url);
        assertEquals("decoded_waveform", voiceContent.waveform);
    }

    @Test
    public void testEncodeDecodeRoundTrip() throws Exception {
        WKVoiceContent original = new WKVoiceContent("/original/voice.mp3", 120);
        original.url = "https://voice.example.com/test.mp3";
        original.waveform = "original_waveform_data";
        
        // 编码
        JSONObject encoded = original.encodeMsg();
        
        // 解码
        WKVoiceContent decoded = new WKVoiceContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(original.localPath, decoded.localPath);
        assertEquals(original.timeTrad, decoded.timeTrad);
        assertEquals(original.url, decoded.url);
        assertEquals(original.waveform, decoded.waveform);
    }

    @Test
    public void testEncodeWithoutWaveform() throws Exception {
        WKVoiceContent voiceContent = new WKVoiceContent("/local/voice.amr", 30);
        voiceContent.url = "https://example.com/voice.amr";
        voiceContent.waveform = null;
        
        JSONObject json = voiceContent.encodeMsg();
        
        assertNotNull(json);
        assertFalse(json.has("waveform"));
    }

    @Test
    public void testDecodeWithMissingFields() throws Exception {
        JSONObject json = new JSONObject();
        json.put("url", "https://example.com/voice.amr");
        
        WKVoiceContent voiceContent = new WKVoiceContent();
        voiceContent.decodeMsg(json);
        
        assertEquals("https://example.com/voice.amr", voiceContent.url);
        assertEquals(0, voiceContent.timeTrad);
        assertNull(voiceContent.localPath);
    }

    @Test
    public void testShortVoice() throws Exception {
        WKVoiceContent original = new WKVoiceContent("/short.amr", 5);
        original.url = "https://example.com/short.amr";
        
        JSONObject encoded = original.encodeMsg();
        WKVoiceContent decoded = new WKVoiceContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(5, decoded.timeTrad);
    }

    @Test
    public void testLongVoice() throws Exception {
        WKVoiceContent original = new WKVoiceContent("/long.amr", 300);
        original.url = "https://example.com/long.amr";
        
        JSONObject encoded = original.encodeMsg();
        WKVoiceContent decoded = new WKVoiceContent();
        decoded.decodeMsg(encoded);
        
        assertEquals(300, decoded.timeTrad);
    }
}

