package com.l8chat.imsdk.msgmodel;

import com.l8chat.imsdk.message.type.WKMsgContentType;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKTextContent 文本消息测试
 * 注意：由于 Android 的 JSONObject 在单元测试中是 mock 的，
 * encode/decode 相关测试需要在 instrumented test 中进行
 */
public class WKTextContentTest {

    @Test
    public void testDefaultConstructor() {
        WKTextContent textContent = new WKTextContent();
        assertNotNull(textContent);
        assertEquals(WKMsgContentType.WK_TEXT, textContent.type);
    }

    @Test
    public void testConstructorWithContent() {
        String message = "Hello, World!";
        WKTextContent textContent = new WKTextContent(message);

        assertNotNull(textContent);
        assertEquals(message, textContent.content);
        assertEquals(WKMsgContentType.WK_TEXT, textContent.type);
    }

    @Test
    public void testGetSearchableWord() {
        WKTextContent textContent = new WKTextContent("搜索关键字");
        assertEquals("搜索关键字", textContent.getSearchableWord());
    }

    @Test
    public void testGetDisplayContent() {
        WKTextContent textContent = new WKTextContent("显示内容");
        assertEquals("显示内容", textContent.getDisplayContent());
    }

    @Test
    public void testEmptyContent() {
        WKTextContent textContent = new WKTextContent("");
        assertEquals("", textContent.content);
    }

    @Test
    public void testChineseContent() {
        String chineseMessage = "你好，世界！这是一条中文消息。";
        WKTextContent textContent = new WKTextContent(chineseMessage);

        assertEquals(chineseMessage, textContent.content);
        assertEquals(chineseMessage, textContent.getDisplayContent());
    }

    @Test
    public void testEmojiContent() {
        String emojiMessage = "Hello 👋 World 🌍";
        WKTextContent textContent = new WKTextContent(emojiMessage);

        assertEquals(emojiMessage, textContent.content);
    }

    @Test
    public void testSpecialCharacters() {
        String specialChars = "Special chars: <>&\"'\\n\\t";
        WKTextContent textContent = new WKTextContent(specialChars);

        assertEquals(specialChars, textContent.content);
    }

    @Test
    public void testLongContent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("Long message content. ");
        }
        String longMessage = sb.toString();

        WKTextContent textContent = new WKTextContent(longMessage);
        assertEquals(longMessage, textContent.content);
    }

    @Test
    public void testContentType() {
        WKTextContent textContent = new WKTextContent("test");
        assertEquals(WKMsgContentType.WK_TEXT, textContent.type);
        assertEquals(1, textContent.type);
    }

    @Test
    public void testNullContentHandling() {
        WKTextContent textContent = new WKTextContent();
        assertNull(textContent.content);

        // getSearchableWord 和 getDisplayContent 应该返回 null
        assertNull(textContent.getSearchableWord());
        assertNull(textContent.getDisplayContent());
    }

    @Test
    public void testSetContent() {
        WKTextContent textContent = new WKTextContent();
        textContent.content = "设置的内容";

        assertEquals("设置的内容", textContent.content);
        assertEquals("设置的内容", textContent.getDisplayContent());
    }
}

