package com.example.hibernate_wo_boot;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addWebapp("", new File("src/main/").getAbsolutePath());
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}
