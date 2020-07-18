package com.it.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Copyright (C), 2018-2020, xxx有限公司
 *
 * @FileName: ExceptionUtil
 * @Author: dawsontong
 * @Date: 2020/7/16 12:46
 * @Description:
 */
public class ExceptionUtil {
    private static final Logger log = LoggerFactory.getLogger(ExceptionUtil.class);
    public static String getStackTrace(Throwable throwable)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            try {
                sw.close();
            } catch ( IOException e ) {
                log.error(ExceptionUtil.getStackTrace(e));
            }
            pw.close();
        }
    }

}
