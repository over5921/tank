package com.xbin.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropertiesMan
 * @Descrption
 * @Author xiebin
 * @Date 2020/9/12 22:37
 * @Version 1.0
 **/
public class PropertiesMan {

    private static PropertiesMan propertiesMan=new PropertiesMan();

    public static PropertiesMan getInstance(){
        return propertiesMan;
    }

    private PropertiesMan(){

    }

    static Properties properties=new Properties();

    static {
        try {
            properties.load(PropertiesMan.class.getClassLoader().getResourceAsStream("config.conf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get(String key){
        return (String) properties.get(key);
    }

}
