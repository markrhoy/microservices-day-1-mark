package ph.apper.account;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public

    @RestController
    @RequestMapping("account")
    public static class AccountController{

        @PostMapping
        public ResponseEntity<?> create(@RequestBody Request request){
            System.out.println(request);

            return ResponseEntity.ok().build();
        }
    }

    @Data
    public static class Request{
        private String firstName;
        private String lastName;
        private String email;
        private String password;
    }
}
