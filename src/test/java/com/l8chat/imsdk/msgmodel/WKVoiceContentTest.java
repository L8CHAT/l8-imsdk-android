package com.l8chat.imsdk.msgmodel;

import com.l8chat.imsdk.message.type.WKMsgContentType;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKVoiceContent 语音消息测试
 * 注意：由于 Android 的 JSONObject 在单元测试中是 mock 的，
 * encode/decode 相关测试需要在 instrumented test 中进行
 */
public class WKVoiceContentTest {

    @Test
    public void testDefaultConstructor() {
        WKVoiceContent voiceContent = new WKVoiceContent();
        assertNotNull(voiceContent);
        assertEquals(WKMsgContentType.WK_VOICE, voiceContent.type);
    }

    @Test
    public void testConstructorWithParams() {
        String localPath = "/sdcard/voice.amr";
        int duration = 30;

        WKVoiceContent voiceContent = new WKVoiceContent(localPath, duration);

        assertNotNull(voiceContent);
        assertEquals(localPath, voiceContent.localPath);
        assertEquals(duration, voiceContent.timeTrad);
        assertEquals(WKMsgContentType.WK_VOICE, voiceContent.type);
    }

    @Test
    public void testGetDisplayContent() {
        WKVoiceContent voiceContent = new WKVoiceContent();
        assertEquals("[语音]", voiceContent.getDisplayContent());
    }

    @Test
    public void testGetSearchableWord() {
        WKVoiceContent voiceContent = new WKVoiceContent();
        assertEquals("[语音]", voiceContent.getSearchableWord());
    }

    @Test
    public void testContentType() {
        WKVoiceContent voiceContent = new WKVoiceContent();
        assertEquals(WKMsgContentType.WK_VOICE, voiceContent.type);
        assertEquals(4, voiceContent.type);
    }

    @Test
    public void testVoiceDuration() {
        // 测试短语音
        WKVoiceContent shortVoice = new WKVoiceContent("/path/short.amr", 5);
        assertEquals(5, shortVoice.timeTrad);

        // 测试长语音
        WKVoiceContent longVoice = new WKVoiceContent("/path/long.amr", 300);
        assertEquals(300, longVoice.timeTrad);
    }

    @Test
    public void testUrlProperty() {
        WKVoiceContent voiceContent = new WKVoiceContent();
        voiceContent.url = "https://example.com/voice.amr";

        assertEquals("https://example.com/voice.amr", voiceContent.url);
    }

    @Test
    public void testLocalPathProperty() {
        WKVoiceContent voiceContent = new WKVoiceContent("/local/voice.amr", 30);
        assertEquals("/local/voice.amr", voiceContent.localPath);
    }

    @Test
    public void testWaveformProperty() {
        WKVoiceContent voiceContent = new WKVoiceContent();
        voiceContent.waveform = "base64_waveform_data";

        assertEquals("base64_waveform_data", voiceContent.waveform);
    }

    @Test
    public void testDefaultValues() {
        WKVoiceContent voiceContent = new WKVoiceContent();

        assertEquals(0, voiceContent.timeTrad);
        assertNull(voiceContent.url);
        assertNull(voiceContent.localPath);
        assertNull(voiceContent.waveform);
    }

    @Test
    public void testTimeTradRange() {
        // 测试边界值
        WKVoiceContent voiceContent = new WKVoiceContent("/path/voice.amr", 0);
        assertEquals(0, voiceContent.timeTrad);

        voiceContent = new WKVoiceContent("/path/voice.amr", Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, voiceContent.timeTrad);
    }
}

