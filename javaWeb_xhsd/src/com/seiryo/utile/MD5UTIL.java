package com.seiryo.utile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5UTIL {
    /**
     * MD5����ϢժҪ�㷨��Message-Digest Algorithm5����һ�ֱ��㷺ʹ�õ�����ɢ�к��������Բ�����һ���̶����ȵ�ɢ��ֵ������ȷ����Ϣ��������һ�¡�
     * <p>
     * ���룺�����ܵ��ַ���
     * �����128λ��16�ֽڣ���32��16�����ַ������ã�
     * Ӧ�ã������������ǩ�����ļ�������У��
     * ��ȫ�ԣ�������
     *
     * @param plainString ����
     * @return cipherString ����
     */
    public static String md5(String plainString) {
        String cipherString = null;
        try {
            // ��ȡʵ��
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // ����ժҪ
            byte[] cipherBytes = messageDigest.digest(plainString.getBytes(StandardCharsets.UTF_8));
            // ���Ϊ16�����ַ���
            StringBuilder sb = new StringBuilder();
            for (byte b : cipherBytes) {
                sb.append(String.format("%02x", b));
            }
            cipherString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherString;
    }
}