package app;

import controllers.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import services.FireBaseService;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
public class App {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class,args);
        FireBaseService.FireBaseService();
    }
}
