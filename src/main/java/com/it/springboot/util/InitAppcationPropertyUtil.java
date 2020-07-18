package com.it.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: InitAppcationPropertyUtil
 * @Author: dawsontong
 * @Date: 2020/6/21 21:16
 * @Description:
 */
public class InitAppcationPropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(InitAppcationPropertyUtil.class);
    private static Properties configProperty =null;
    private static String rootPath = StringUtil.isEmpty(System.getProperty("root.path")) ? "../":System.getProperty("root.path");
    private static String binPath;
    private static String configPath;
    private static final String customPropertyName = "application_custom.properties";

    static {
        binPath = rootPath + "/bin/";
        configPath = rootPath + "/config/";
        configProperty = new Properties();

        File file = new File(configPath,customPropertyName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            configProperty.load(fileInputStream);
        } catch ( IOException e ) {
            logger.error(e.getMessage());
        }


    }

    public static String[] readOuter(String[] args){
        Iterator< Map.Entry< Object, Object > > iterator = configProperty.entrySet().iterator();
        Set<String> argSet = new HashSet<>();
        if(args !=null && args.length != 0){
            for (String arg :args) {
                argSet.add(arg);
            }
        }
        StringBuffer sb = new StringBuffer("--");
        String str="";
        boolean isCustomEmpty = true;
        while ( iterator.hasNext() ){
            Map.Entry< Object, Object > next = iterator.next();
            str = "--"+next.getKey().toString()+"="+next.getValue().toString();
            argSet.add(str);
            logger.info("adding customed config:{}",str);
            if(isCustomEmpty){
               isCustomEmpty = false;
            }
        }
        if(!isCustomEmpty){
            argSet.add("--root.path="+rootPath);
        }
        String[] res = null;
        if(argSet !=null && !argSet.isEmpty()){
            res = new String[argSet.size()];
            Iterator< String > stringIterator = argSet.iterator();
            int index = 0;
            while ( stringIterator.hasNext() ){
                res[index] = stringIterator.next();
                index ++;
            }
        }else {
            res = new String[0];
        }
        return res;
    }



}
