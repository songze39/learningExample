package com.yile.learning.rest.test;

import com.rabbitframework.commons.codec.Md5Hash;
import org.junit.Test;

public class MD5Test {
    @Test
    public void generatorMD5() {
        String md5String = new Md5Hash("test").toString();
        System.out.println(md5String);
    }
}
