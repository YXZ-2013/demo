package com.yin.myproject.demo.redis;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Eason on 2017/4/27.
 */
public class demo01 {
    public static void main(String[] args) {
        String message = "待比较图片的有效数量为{1},小于参数中规定的有效数量{2}";
        String s = StringUtils.replaceEach(message, new String[]{"{1}","{2}"}, new String[]{"1", "2"});
        System.out.println(s);
    }
}
