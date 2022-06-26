package application.api;

import application.model.dto.UserDtoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class RestTemplateForUserTest {

    @Test
    void get() throws JsonProcessingException {
        RestTemplateForUser restTemplateForUser = new RestTemplateForUser();

        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response = restTemplateForUser.get();

        JsonNode root = mapper.readTree(response.getBody());
        System.out.println(root.asText());
        //restTemplateForUser.setSetCookie(response.getHeaders().get("set-cookie").get(0));
        //restTemplateForUser.setHeaders();

        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setId(3L);
        userDtoResponse.setName("James");
        userDtoResponse.setLastName("Brown");
        userDtoResponse.setAge(new Byte((byte)33));

        UserDtoResponse responsePost = (UserDtoResponse) restTemplateForUser.post(userDtoResponse,
                response.getHeaders().get("set-cookie"));
        System.out.println(responsePost);
    }
}