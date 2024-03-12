package cat.tecnocampus.consensus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConsensusApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsensusApplication.class, args);
    }
}
