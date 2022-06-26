package application.api;

import application.model.dto.UserDtoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class RestTemplateForUser {

    private static final String URL = "http://94.198.50.185:7081/api/users";
    private RestTemplate restTemplate = new RestTemplate();
    private String setCookie = "default";

    public String getSetCookie() {
        return setCookie;
    }

    public void setSetCookie(String setCookie) {
        this.setCookie = setCookie;
    }

    public ResponseEntity<String> get() {
        ResponseEntity<String> response =
        restTemplate.getForEntity(URL, String.class);
        return response;
    }

    public ResponseEntity post(UserDtoResponse userDtoResponse, List<String> cookies) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookies.stream().collect(Collectors.joining(";")));
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("id", userDtoResponse.getId());
        personJsonObject.put("name", userDtoResponse.getName());
        personJsonObject.put("lastName", userDtoResponse.getLastName());
        personJsonObject.put("age", userDtoResponse.getAge());

        ObjectMapper objectMapper = new ObjectMapper();

        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

//        UserDtoResponse personResultAsJsonStr =
//                restTemplate.postForObject(URL, request, UserDtoResponse.class);

        ResponseEntity responseEntityStr = restTemplate.
                postForEntity(URL, request, UserDtoResponse.class);
        return responseEntityStr;
    }

    public ResponseEntity<UserDtoResponse> put() {
        return null;
    }

    public ResponseEntity<UserDtoResponse> delete() {
        return null;
    }

    public void setHeaders() {
        restTemplate.headForHeaders(URL).set("Set-Cookie", setCookie);
        restTemplate.headForHeaders(URL).set("Content-Type", "application/json");
    }
}
