package app;

import controllers.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
