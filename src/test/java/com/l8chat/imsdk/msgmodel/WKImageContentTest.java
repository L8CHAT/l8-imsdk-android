package com.l8chat.imsdk.msgmodel;

import com.l8chat.imsdk.message.type.WKMsgContentType;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * WKImageContent 图片消息测试
 * 注意：由于 Android 的 JSONObject 在单元测试中是 mock 的，
 * encode/decode 相关测试需要在 instrumented test 中进行
 */
public class WKImageContentTest {

    @Test
    public void testDefaultConstructor() {
        WKImageContent imageContent = new WKImageContent();
        assertNotNull(imageContent);
        assertEquals(WKMsgContentType.WK_IMAGE, imageContent.type);
    }

    @Test
    public void testConstructorWithLocalPath() {
        String localPath = "/sdcard/image.jpg";
        WKImageContent imageContent = new WKImageContent(localPath);

        assertNotNull(imageContent);
        assertEquals(localPath, imageContent.localPath);
        assertEquals(WKMsgContentType.WK_IMAGE, imageContent.type);
    }

    @Test
    public void testGetDisplayContent() {
        WKImageContent imageContent = new WKImageContent();
        assertEquals("[图片]", imageContent.getDisplayContent());
    }

    @Test
    public void testGetSearchableWord() {
        WKImageContent imageContent = new WKImageContent();
        assertEquals("[图片]", imageContent.getSearchableWord());
    }

    @Test
    public void testImageDimensions() {
        WKImageContent imageContent = new WKImageContent();
        imageContent.width = 4096;
        imageContent.height = 2160;

        assertEquals(4096, imageContent.width);
        assertEquals(2160, imageContent.height);
    }

    @Test
    public void testContentType() {
        WKImageContent imageContent = new WKImageContent();
        assertEquals(WKMsgContentType.WK_IMAGE, imageContent.type);
        assertEquals(2, imageContent.type);
    }

    @Test
    public void testUrlProperty() {
        WKImageContent imageContent = new WKImageContent();
        imageContent.url = "https://example.com/image.jpg";

        assertEquals("https://example.com/image.jpg", imageContent.url);
    }

    @Test
    public void testLocalPathProperty() {
        WKImageContent imageContent = new WKImageContent("/local/path.jpg");
        assertEquals("/local/path.jpg", imageContent.localPath);
    }

    @Test
    public void testImageSizes() {
        WKImageContent imageContent = new WKImageContent();

        // 测试常见分辨率
        imageContent.width = 1920;
        imageContent.height = 1080;
        assertEquals(1920, imageContent.width);
        assertEquals(1080, imageContent.height);

        // 测试 4K 分辨率
        imageContent.width = 3840;
        imageContent.height = 2160;
        assertEquals(3840, imageContent.width);
        assertEquals(2160, imageContent.height);
    }

    @Test
    public void testDefaultDimensions() {
        WKImageContent imageContent = new WKImageContent();
        assertEquals(0, imageContent.width);
        assertEquals(0, imageContent.height);
    }
}

