package com.boomboo.demo.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boomboo.demo.vo.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Slf4j
public class MsgUtil {
    static final int BUFFER = 128;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDeserializer() {
        PersonVO personVO = new PersonVO();
        personVO.setAge(111);
        personVO.setName("aaa");
        try {
            String str = objectMapper.writeValueAsString(personVO);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(str);
            jsonObject.put("aa", "bn");
            PersonVO personVO1 = JSON.parseObject(jsonObject.toJSONString(), PersonVO.class);
            log.info("person:{}", objectMapper.writeValueAsString(personVO1));
        } catch (Exception e) {
            log.error("e", e);
        }


    }

    public static String getBytes(File f) throws Exception {
        FileInputStream in = new FileInputStream(f);
        byte[] b = new byte[4096];

        byte[] tmpResult = (byte[]) null;
        String base64Result = null;
        int n;
        while ((n = in.read(b)) != -1) {
            if (tmpResult == null)
                tmpResult = getSumByte(null, 0, b, n);
            else {
                tmpResult = getSumByte(tmpResult, tmpResult.length, b, n);
            }
        }

        base64Result = deflateEncode(tmpResult);
        in.close();
        return base64Result;
    }

    public static byte[] getSumByte(byte[] baseValue, int orLength,
                                    byte[] streamByte, int length) {
        byte[] result = new byte[orLength + length];
        for (int i = 0; i < orLength; i++) {
            result[i] = baseValue[i];
        }
        for (int i = 0; i < length; i++) {
            result[(orLength + i)] = streamByte[i];
        }
        return result;
    }

    public static String deflateEncode(byte[] inputByte) throws Exception {
        if ((inputByte == null) || (inputByte.length == 0)) {
            System.out.println("压缩编码异常:输入不能为空指针!");
            throw new IOException("压缩编码异常:输入不能为空指针!");
        }
        byte[] tmpByte = deflater(inputByte);
        return encode(tmpByte);
    }

    public static byte[] decodeInflate(String input) throws Exception {
        if ((input == null) || (input.length() == 0)) {
            throw new IOException("解码解压缩异常:输入不能为空!");
        }
        byte[] tmpByte = decode(input);
        return inflater(tmpByte);

    }

    public static byte[] deflater(byte[] inputByte) throws IOException {
        int compressedDataLength = 0;
        Deflater compresser = new Deflater();
        compresser.setInput(inputByte);
        compresser.finish();
        ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
        byte[] result = new byte[1024];
        try {
            while (!compresser.finished()) {
                compressedDataLength = compresser.deflate(result);
                o.write(result, 0, compressedDataLength);
            }
        } finally {
            o.close();
        }
        return o.toByteArray();
    }

    public static byte[] inflater(byte[] inputByte) throws Exception {
        int compressedDataLength = 0;
        Inflater compresser = new Inflater(false);
        compresser.setInput(inputByte, 0, inputByte.length);
        ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
        byte[] result = new byte[1024];
        try {
            while (!compresser.finished()) {
                compressedDataLength = compresser.inflate(result);
                if (compressedDataLength == 0) {
                    break;
                }
                o.write(result, 0, compressedDataLength);
            }
        } finally {
            o.close();
        }
        return o.toByteArray();
    }

    public static byte[] decode(String inputByte) throws Exception {
        return Base64Utils.decode(inputByte);
    }

    public static String encode(byte[] inputByte) throws Exception {
        return Base64Utils.encode(inputByte);
    }
}