package com.it.springboot.util;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: StringUtil
 * @Author: dawsontong
 * @Date: 2020/6/21 21:44
 * @Description:
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        return str ==null || str.trim().length()==0;
    }

}
