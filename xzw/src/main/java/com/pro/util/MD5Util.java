package com.pro.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Yuhua
 * @since 21.8.5 11:20
 */
public class MD5Util {
    /**
     *
     * @param source 准备加密梳理的
     * @param salt
     * @return
     */
    public static String md5Digest(String source,Integer salt){
        char[] ca = source.toCharArray();
        /*for (int i = 0; i < ca.length; i++) {
            ca[i] = (char) (ca[i]+salt);
        }*/
        //字符数组-->字符串
        String target = new String(ca);
        String md5 = DigestUtils.md5Hex(target);
        //abcd-->adfadsfasdfadfsa
        return md5;
    }
}