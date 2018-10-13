package com.boomboo.demo.java;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.ZipOutputStream;

public class TestZip {

    public static void main(String[] args) throws Exception {

        String filename = "/Users/**/Desktop/123.jpg";


        byte[] file = toByteArray2(filename);


        String result = Base64Utils.encode(file);

        byte[] decodeBytes = Base64Utils.decode(result);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ZipOutputStream fos = new ZipOutputStream(baos);
        fos.write(decodeBytes);
        fos.close();

        System.err.println(result);

//
//        String filename1 = "/Users/**/Desktop/123.jpg";
//
//        BufferedImage bi = ImageIO.read(new File(filename1));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bi, "jpg", baos);
//        byte[] picStream = baos.toByteArray();
//
//        String result = Base64Utils.encode(picStream);
//        String result1 = Base64Utils.encode(picStream);
//
//        byte[] decodeBytes = Base64Utils.decode(result);
//        byte[] decodeBytes1 = Base64Utils.decode(result1);
//
//        FileOutputStream fos = new FileOutputStream(new File("/Users/**/Desktop/123.zip"));
//        fos.write(decodeBytes);
////        fos.write(ArrayUtils.addAll(decodeBytes1));
//        fos.close();
//        System.err.println(result);
    }

    /**
     * NIO way
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {

            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing  
                // System.out.println("reading");  
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
