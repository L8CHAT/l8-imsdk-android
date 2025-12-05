package com.l8chat.imsdk.message.type;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKMsgContentType 消息内容类型测试
 */
public class WKMsgContentTypeTest {

    @Test
    public void testTextType() {
        assertEquals(1, WKMsgContentType.WK_TEXT);
    }

    @Test
    public void testImageType() {
        assertEquals(2, WKMsgContentType.WK_IMAGE);
    }

    @Test
    public void testGifType() {
        assertEquals(3, WKMsgContentType.WK_GIF);
    }

    @Test
    public void testVoiceType() {
        assertEquals(4, WKMsgContentType.WK_VOICE);
    }

    @Test
    public void testVideoType() {
        assertEquals(5, WKMsgContentType.WK_VIDEO);
    }

    @Test
    public void testLocationType() {
        assertEquals(6, WKMsgContentType.WK_LOCATION);
    }

    @Test
    public void testCardType() {
        assertEquals(7, WKMsgContentType.WK_CARD);
    }

    @Test
    public void testFileType() {
        assertEquals(8, WKMsgContentType.WK_FILE);
    }

    @Test
    public void testMultipleForwardType() {
        assertEquals(11, WKMsgContentType.WK_MULTIPLE_FORWARD);
    }

    @Test
    public void testVectorStickerType() {
        assertEquals(12, WKMsgContentType.WK_VECTOR_STICKER);
    }

    @Test
    public void testEmojiStickerType() {
        assertEquals(13, WKMsgContentType.WK_EMOJI_STICKER);
    }

    @Test
    public void testContentFormatErrorType() {
        assertEquals(97, WKMsgContentType.WK_CONTENT_FORMAT_ERROR);
    }

    @Test
    public void testSignalDecryptErrorType() {
        assertEquals(98, WKMsgContentType.WK_SIGNAL_DECRYPT_ERROR);
    }

    @Test
    public void testInsideMsgType() {
        assertEquals(99, WKMsgContentType.WK_INSIDE_MSG);
    }

    @Test
    public void testAllTypesUnique() {
        int[] types = {
            WKMsgContentType.WK_TEXT,
            WKMsgContentType.WK_IMAGE,
            WKMsgContentType.WK_GIF,
            WKMsgContentType.WK_VOICE,
            WKMsgContentType.WK_VIDEO,
            WKMsgContentType.WK_LOCATION,
            WKMsgContentType.WK_CARD,
            WKMsgContentType.WK_FILE,
            WKMsgContentType.WK_MULTIPLE_FORWARD,
            WKMsgContentType.WK_VECTOR_STICKER,
            WKMsgContentType.WK_EMOJI_STICKER,
            WKMsgContentType.WK_CONTENT_FORMAT_ERROR,
            WKMsgContentType.WK_SIGNAL_DECRYPT_ERROR,
            WKMsgContentType.WK_INSIDE_MSG
        };
        
        // 确保所有类型值不重复
        for (int i = 0; i < types.length; i++) {
            for (int j = i + 1; j < types.length; j++) {
                assertNotEquals("Message content types should be unique", types[i], types[j]);
            }
        }
    }

    @Test
    public void testMediaTypes() {
        // 媒体类型应该在 2-5 范围内
        assertTrue(WKMsgContentType.WK_IMAGE >= 2 && WKMsgContentType.WK_IMAGE <= 5);
        assertTrue(WKMsgContentType.WK_GIF >= 2 && WKMsgContentType.WK_GIF <= 5);
        assertTrue(WKMsgContentType.WK_VOICE >= 2 && WKMsgContentType.WK_VOICE <= 5);
        assertTrue(WKMsgContentType.WK_VIDEO >= 2 && WKMsgContentType.WK_VIDEO <= 5);
    }

    @Test
    public void testErrorTypes() {
        // 错误类型应该在 90+ 范围内
        assertTrue(WKMsgContentType.WK_CONTENT_FORMAT_ERROR >= 90);
        assertTrue(WKMsgContentType.WK_SIGNAL_DECRYPT_ERROR >= 90);
        assertTrue(WKMsgContentType.WK_INSIDE_MSG >= 90);
    }
}

