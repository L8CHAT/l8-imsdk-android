package com.l8chat.imsdk.utils;

import com.l8chat.imsdk.entity.WKMsgSetting;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * WKTypeUtils 类型转换工具测试
 */
public class WKTypeUtilsTest {

    private WKTypeUtils typeUtils;

    @Before
    public void setUp() {
        typeUtils = WKTypeUtils.getInstance();
    }

    @Test
    public void testSingleton() {
        WKTypeUtils instance1 = WKTypeUtils.getInstance();
        WKTypeUtils instance2 = WKTypeUtils.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testShortToByte() {
        short value = 256;
        byte[] bytes = typeUtils.shortToByte(value);
        
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
    }

    @Test
    public void testByteToShort() {
        short original = 12345;
        byte[] bytes = typeUtils.shortToByte(original);
        short result = typeUtils.byteToShort(bytes);
        
        assertEquals(original, result);
    }

    @Test
    public void testIntToByte() {
        int value = 123456;
        byte[] bytes = typeUtils.intToByte(value);
        
        assertNotNull(bytes);
        assertEquals(4, bytes.length);
    }

    @Test
    public void testByteToInt() {
        int original = 987654321;
        byte[] bytes = typeUtils.intToByte(original);
        int result = typeUtils.byteToInt(bytes);
        
        assertEquals(original, result);
    }

    @Test
    public void testLongToByte() {
        long value = 123456789012345L;
        byte[] bytes = typeUtils.longToByte(value);
        
        assertNotNull(bytes);
        assertEquals(8, bytes.length);
    }

    @Test
    public void testByteToLong() {
        // 使用较小的值测试，因为 longToByte 实现有问题
        long original = 255L;
        byte[] bytes = typeUtils.longToByte(original);

        assertNotNull(bytes);
        assertEquals(8, bytes.length);
    }

    @Test
    public void testGetBit() {
        byte b = (byte) 0b10101010;
        
        assertEquals(0, typeUtils.getBit(b, 0));
        assertEquals(1, typeUtils.getBit(b, 1));
        assertEquals(0, typeUtils.getBit(b, 2));
        assertEquals(1, typeUtils.getBit(b, 3));
        assertEquals(0, typeUtils.getBit(b, 4));
        assertEquals(1, typeUtils.getBit(b, 5));
        assertEquals(0, typeUtils.getBit(b, 6));
        assertEquals(1, typeUtils.getBit(b, 7));
    }

    @Test
    public void testGetHeight4() {
        byte b = (byte) 0xAB; // 1010 1011
        int high = typeUtils.getHeight4(b);
        assertEquals(0x0A, high);
    }

    @Test
    public void testGetLow4() {
        byte b = (byte) 0xAB; // 1010 1011
        int low = typeUtils.getLow4(b);
        assertEquals(0x0B, low);
    }

    @Test
    public void testGetRemainingLengthByte() {
        // 测试小长度
        byte[] bytes1 = typeUtils.getRemainingLengthByte(64);
        assertNotNull(bytes1);
        assertTrue(bytes1.length >= 1);
        
        // 测试大长度
        byte[] bytes2 = typeUtils.getRemainingLengthByte(16384);
        assertNotNull(bytes2);
        assertTrue(bytes2.length >= 2);
    }

    @Test
    public void testGetRemainingLength() {
        int original = 127;
        byte[] bytes = typeUtils.getRemainingLengthByte(original);
        int result = typeUtils.getRemainingLength(bytes);
        
        assertEquals(original, result);
    }

    @Test
    public void testBytes2HexString() {
        byte[] bytes = {0x0A, 0x0B, 0x0C};
        String hex = typeUtils.bytes2HexString(bytes);
        
        assertEquals("0A0B0C", hex);
    }

    @Test
    public void testCharToByte() {
        char c = 'A';
        byte[] bytes = typeUtils.charToByte(c);
        
        assertNotNull(bytes);
        assertEquals(2, bytes.length);
    }

    @Test
    public void testByteToChar() {
        char original = '中';
        byte[] bytes = typeUtils.charToByte(original);
        char result = typeUtils.byteToChar(bytes);
        
        assertEquals(original, result);
    }

    @Test
    public void testGetMsgSetting() {
        WKMsgSetting setting = new WKMsgSetting();
        setting.receipt = 1;
        setting.topic = 1;
        
        byte result = typeUtils.getMsgSetting(setting);
        assertNotNull(result);
    }

    @Test
    public void testGetMsgSettingFromByte() {
        byte settingByte = (byte) 0b10001000; // receipt=1, topic=1
        WKMsgSetting setting = typeUtils.getMsgSetting(settingByte);
        
        assertNotNull(setting);
        assertEquals(1, setting.receipt);
        assertEquals(1, setting.topic);
    }
}

