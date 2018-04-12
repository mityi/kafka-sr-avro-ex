## SR

##### Собрать проект и сгенерить класс 
./mvnw clean install


##### Общая информация

- org.apache.avro - плагин для генирации классов
- user.avsc - описывает класс SR_User
- application.yml - описывает настройки работы с кафкой
- sr.Mocks.MockKafkaAvroSerializer(Deserializer) - мок когда нет подключенной sr
 - AppTest тест на моках
- docker-compose.yml - пример как мы поднимаем кафку на dev'тачках при необходимости
  - тот-же тест AppTestOnRealKafka тест но на реальной кафке и sr


##### Ссылки

```$xslt
https://avro.apache.org/docs/1.8.2/gettingstartedjava.html
https://docs.confluent.io/current/schema-registry/docs/index.html
```