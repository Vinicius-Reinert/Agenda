package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@SpringBootApplication
@EntityScan(basePackages = "domain")
@ComponentScan(basePackages = {"service", "dao", "springboot"})
public class Application {


    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
