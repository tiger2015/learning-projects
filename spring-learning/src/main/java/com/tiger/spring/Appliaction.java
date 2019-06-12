package com.tiger.spring;

import com.tiger.spring.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Appliaction {

    static {
//        System.out.println("加载log4j2.xml");
//        String path="file:///C:/Users/ZENGHU/Desktop/log4j2.xml";
//        try {
//            URL url = new URL(path);
//            ConfigurationSource source = new ConfigurationSource(url.openStream(), url);
//            LoggerContext context = Configurator.initialize(null, source);
//            XmlConfiguration xmlConfig = new XmlConfiguration(source);
//            context.start(xmlConfig);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        User bean = context.getBean(User.class);

        context.close(); // 执行@PreDestroy方法，只针对单例模式的bean

    }
}
