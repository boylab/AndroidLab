/* ====================================================================
 * Copyright (c) 2017-2017 The CAYSN Project.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted.
 *
 * THIS SOFTWARE IS PROVIDED BY THE CAYSN PROJECT ``AS IS'' AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE CAYSN PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 * Company: CAYSN
 * Author:  PENGDASHUAI
 *
 */

package com.boylab.projectstruct.utilre;

import java.util.List;
import java.util.Locale;
import java.util.Random;

class ByteUtils {
	public static byte[] getRandomByteArray(int nlength) {
		byte[] data = new byte[nlength];
		Random rmByte = new Random(System.currentTimeMillis());
		for (int i = 0; i < nlength; i++) {
			// 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n
			data[i] = (byte) rmByte.nextInt(256);
		}
		return data;
	}

	public static boolean bytesEquals(byte[] d1, int offset1, byte[] d2,
			int offset2, int length) {
		if (d1 == null || d2 == null)
			return false;

		if ((offset1 + length > d1.length) || (offset2 + length > d2.length))
			return false;

		for (int i = 0; i < length; i++)
			if (d1[i + offset1] != d2[i + offset2])
				return false;

		return true;
	}

	public static byte[] byteArraysToBytes(byte[][] data) {

		int length = 0;
		for (int i = 0; i < data.length; i++)
			length += data[i].length;
		byte[] send = new byte[length];
		int k = 0;
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[i].length; j++)
				send[k++] = data[i][j];
		return send;
	}

	public static byte[] ByteArrayListToBytes(List<byte[]> data) {
		
		int length = 0;
		for (int i = 0; i < data.size(); ++i)
			length += data.get(i).length;
		
		byte[] bytes = new byte[length];
		int k = 0;
		for (int i = 0; i < data.size(); i++)
			for (int j = 0; j < data.get(i).length; j++)
				bytes[k++] = data.get(i)[j];
		return bytes;
	}

	public static String bytesToStr(byte[] rcs) {
		if (null == rcs)
			return "";
		StringBuilder stringBuilder = new StringBuilder();
		String tmp;
		for (int i = 0; i < rcs.length; i++) {
			tmp = Integer.toHexString(rcs[i] & 0xff);
			tmp = tmp.toUpperCase(Locale.getDefault());
			if (tmp.length() == 1) {
				stringBuilder.append("0" + tmp);
			} else {
				stringBuilder.append("" + tmp);
			}

			if ((i % 16) != 15) {
				stringBuilder.append(" ");
			} else {
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}
	
	public static byte[] macStringToBytes(String macString) {
		byte[] mac = new byte[6];
		
		for (int i = 0; i < mac.length; ++i) {
			try {
				String hexString = macString.substring(i*3, i*3+2);
				mac[i] = (byte) Integer.parseInt(hexString, 16);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return mac;
	}
}
