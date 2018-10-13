package com.boomboo.demo.java;

public class TestZip2 {

	public static void main(String[] args)throws Exception{
		String content = "eJwzNDI2JIS4AIkzBxM=";
		byte[] msgBytes = MsgUtil.decodeInflate(content);
		System.err.println(new String(msgBytes));
	}
}
