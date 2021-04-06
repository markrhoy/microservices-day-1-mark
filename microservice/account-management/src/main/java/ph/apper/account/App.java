package ph.apper.account;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RestController
    @RequestMapping("account")
    public static class AccountController{

        @Autowired
        private Environment env;

        private final RestTemplate restTemplate;

        public AccountController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @PostMapping
        public ResponseEntity<?> register(@RequestBody CreateAccountRequest request){
            System.out.println(request);

            Activity activity = new Activity();
            activity.setAction("Registration");
            activity.setIdentifier("email"+request.getEmail());

            ResponseEntity<Object> response = restTemplate.postForEntity(env.getProperty("host"), activity, Object.class);

            if(response.getStatusCode().is2xxSuccessful()){
                System.out.println("Success");
            }else {
                System.out.println("Err:" + response.getStatusCode());
            }

            return ResponseEntity.ok().build();
        }
    }

    @Data
    public static class Activity{
        private String action;
        private String identifier;
    }

    @Data
    public static class CreateAccountRequest{
        private String firstName;
        private String lastName;
        private String email;
        private String password;
    }
}
