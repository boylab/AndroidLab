package com.boylab.projectstruct.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static String md5(byte[] input) throws NoSuchAlgorithmException {
        byte[] bytes = MessageDigest.getInstance("MD5").digest(input);
        return printHexBinary(bytes);
    }

    public static String printHexBinary(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static boolean checkFileMd5(String filename, String filemd5) {
        boolean result = false;
        try {
            byte[] filedata = FileUtils.ReadBytesFromFile(filename);
            String currentmd5 = md5(filedata);
            result = (currentmd5.compareToIgnoreCase(filemd5) == 0);
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        return result;
    }

}
