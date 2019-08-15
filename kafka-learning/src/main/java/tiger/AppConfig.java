package tiger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Configuration
@EnableKafka
@EnableScheduling
@ComponentScan(basePackages = {"tiger"})
@PropertySource(value = {"classpath:application.properties", "./config/application.properties"}, ignoreResourceNotFound = true)
@Slf4j
public class AppConfig {
    static {
        File file = new File("./config/log4j2.xml");
        if (file.exists()) {
            System.setProperty("log4j.configuration", "./config/log4j2.xml");
        }
    }
}
