package sr;

import example.avro.SR_User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTestOnRealKafka {

    /**
     * поднимаем docker-compose.yml и запускаем тест
     */

    @Autowired
    private KafkaTemplate<String, SR_User> kafkaTemplate;

    @Test
    public void testReceive() throws Exception {
        String[] test = {"t", "e", "s", "t"};
        for (String s : test) {
            TimeUnit.SECONDS.sleep(1);
            SR_User user = SR_User.newBuilder()
                    .setName(s)
                    .build();
            kafkaTemplate.send(App.topic, user);
        }
    }
}