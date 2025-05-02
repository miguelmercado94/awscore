package com.awscore.eventslambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;
import java.util.function.Function;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LambdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LambdaApplication.class, args);
    }

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder().build();
    }

    @Bean
    public Function<String, String> handleSqsMessages(DynamoDbClient dynamoDbClient) {
        return input -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(input);
                JsonNode records = root.path("Records");

                if (records.isArray()) {
                    for (JsonNode record : records) {
                        String bodyStr = record.path("body").asText();

                        String uuid = UUID.randomUUID().toString();
                        Map<String, AttributeValue> item = new HashMap<>();
                        item.put("id", AttributeValue.builder().s(uuid).build());
                        item.put("event", AttributeValue.builder().s(bodyStr).build());

                        PutItemRequest request = PutItemRequest.builder()
                                .tableName("events")
                                .item(item)
                                .build();

                        dynamoDbClient.putItem(request);
                        System.out.println("Guardado en DynamoDB: " + bodyStr);
                    }
                }

                return "Eventos guardados";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error procesando evento: " + e.getMessage();
            }
        };
    }

}
