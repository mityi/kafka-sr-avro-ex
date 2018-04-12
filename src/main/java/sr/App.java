package sr;

import example.avro.SR_User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableKafka
public class App {

    static final String topic = "sr-t";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Component
    public static class L {

        @KafkaListener(topics = topic)
        public void processMessage(SR_User user) {
            System.out.println();
            System.out.println("================================== " + user.getName() + " ==================================");
            System.out.println();
        }
    }
}
