package sr;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.HashMap;

public class Mocks {
    private static MockSchemaRegistryClient client = new MockSchemaRegistryClient();
    private static HashMap conf = new HashMap<String, String>() {{
        put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "bogus");
        put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
    }};


    public static class MockKafkaAvroSerializer extends KafkaAvroSerializer {
        public MockKafkaAvroSerializer() {
            super(client, conf);
        }
    }

    public static class MockKafkaAvroDeserializer extends io.confluent.kafka.serializers.KafkaAvroDeserializer {
        public MockKafkaAvroDeserializer() {
            super(client, conf);
        }
    }
}
