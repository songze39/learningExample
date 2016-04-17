package com.yile.learning.rest.test;

import com.rabbitframework.commons.codec.Md5Hash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class MD5Test {
    private Logger logger = LogManager.getLogger(MD5Test.class);
    @Test
    public void generatorMD5() {
        String md5String = new Md5Hash("test").toString();
        System.out.println(md5String);
        logger.debug(md5String);
    }
}
