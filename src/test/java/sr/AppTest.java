package sr;

import example.avro.SR_User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
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

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "spring.kafka.producer.value-serializer=sr.Mocks.MockKafkaAvroSerializer",
                "spring.kafka.consumer.value-deserializer=sr.Mocks.MockKafkaAvroDeserializer"
        }
)
public class AppTest {

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, App.topic);

    @Autowired
    private KafkaTemplate<String, SR_User> kafkaTemplate;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
    }

    @Before
    public void setUp() throws Exception {
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafka.getPartitionsPerTopic());
        }
    }

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