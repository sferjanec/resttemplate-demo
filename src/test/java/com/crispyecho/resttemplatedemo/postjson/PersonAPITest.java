package com.crispyecho.resttemplatedemo.postjson;

import com.crispyecho.resttemplatedemo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class PersonAPITest {

    private static RestTemplate restTemplate;
    private static HttpHeaders headers;
    private static JSONObject personJsonObject;

    private static String createPersonUrl;
    private static String updatePersonUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeClass
    public static void runBeforeAllTestMethods() throws JSONException {
        createPersonUrl = "http://localhost:8082/spring-rest/createPerson";
        updatePersonUrl = "http://localhost:8082/spring-rest/updatePerson";

        restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        personJsonObject.put("id", 1);
        personJsonObject.put("name", "John");
    }

    @Test
    public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
        throws IOException {
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        String personResultAsJsonStr = restTemplate.postForObject(createPersonUrl, request, String.class);
        JsonNode root = objectMapper.readTree(personResultAsJsonStr);

        Person person = restTemplate.postForObject(createPersonUrl, request, Person.class);

        assertNotNull(personResultAsJsonStr);
        assertNotNull(root);
        assertNotNull(root.path("name").asText());

        assertNotNull(person);
        assertNotNull(person.getName());
    }

    @Test
    public void givenDataIsJson_whenDataIsPostedByPostForEntity_thenResponseBodyIsNotNull()
        throws IOException {
        HttpEntity<String> request = new HttpEntity<String> (personJsonObject.toString(), headers);

        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(createPersonUrl, request, String.class);
        JsonNode root = objectMapper.readTree(responseEntityStr.getBody());

        assertNotNull(responseEntityStr.getBody());
        assertNotNull(root.path("name").asText());

        ResponseEntity<Person> responseEntityPerson = restTemplate.
                postForEntity(createPersonUrl, request, Person.class);

        assertNotNull(responseEntityPerson.getBody());
        assertNotNull(responseEntityPerson.getBody().getName());
    }
}
