package com.lizl.train.generator.server;


import com.lizl.train.generator.util.FreemarkerUtil;
import java.io.File;
import java.util.*;

public class TestGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\lizl\\train\\generator\\test\\";

    static {
        new File(toPath).mkdirs();
    }

    public static void main(String[] args) throws Exception {
        FreemarkerUtil.initConfig("test.ftl");
        Map<String, Object> params = new HashMap<>();
        params.put("domain", "Test");
        FreemarkerUtil.generator(toPath + "Test.java", params);
    }

}
