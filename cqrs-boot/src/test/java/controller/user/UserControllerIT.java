package controller.user;

import es.plexus.Application;
import es.plexus.config.security.UserCredentialsDto;
import es.plexus.dto.user.UserDto;
import es.plexus.dto.user.UserPostDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.*;


@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIT {
    //Token válido por 1 semana
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private static String jwt_token;

    private static UserDto userDto;

    @Test
    @Order(1)
    public void createUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "register";
        URI uri = new URI(url);
        UserPostDto userPostDto = new UserPostDto(
                "prueba3",
                "prueba3",
                "prueba3",
                "prueba3@gmail.com",
                "1234567",
                LocalDate.of(2020, 02, 02),
                "prueba1");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserPostDto> requestEntity = new HttpEntity<>(userPostDto, headers);

        ResponseEntity<UserDto> response = testRestTemplate.postForEntity(uri, requestEntity, UserDto.class);

        this.userDto = response.getBody();
        System.out.println(userDto);

        assertEquals("The response code must be 201", HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals("The name must be prueba3", response.getBody().getName(), userPostDto.getName());
    }

    @Test
    @Order(2)
    public void loginUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "login";
        URI uri = new URI(url);
        UserCredentialsDto credentialsDto = new UserCredentialsDto(userDto.getUsername(), "1234567");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserCredentialsDto> requestEntity = new HttpEntity<>(credentialsDto, headers);

        ResponseEntity<Map> response = testRestTemplate.postForEntity(uri, requestEntity, Map.class);

        Map<String, String> responseBody = response.getBody();
        String authorizationHeader = responseBody.get("Authorization");
        this.jwt_token = authorizationHeader.replace("Bearer ", "");


        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertFalse("The response must not contain records", response.getBody()==null);
    }

    @Test
    @Order(3)
    public void getAllUsersTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(this.jwt_token);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserDto[]> response = testRestTemplate.exchange(uri, HttpMethod.GET, requestEntity,UserDto[].class);

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertTrue("The response length must be " + response.getBody().length, response.getBody().length >=1);
    }
    @Test
    @Order(3)
    public void getUserByIdTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId();
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(this.jwt_token);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserDto> response = testRestTemplate.exchange(uri, HttpMethod.GET, requestEntity,UserDto.class);

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("The email must be prueba1@gmail.com", response.getBody().getEmail(), userDto.getEmail());
    }
    @Test
    @Order(4)
    public void updateUserByIdTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId();
        URI uri = new URI(url);
        UserPostDto userPostDto = new UserPostDto(
                "update1",
                "update1",
                "update1",
                "update1@gmail.com",
                "1234567",
                LocalDate.of(2020, 02, 02),
                "update1");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.jwt_token);

        HttpEntity<UserPostDto> requestEntity = new HttpEntity<>(userPostDto, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.PUT, requestEntity,String.class);

        assertEquals("The response code must be 204", HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
    }

//    @Test
//    @Order(4)
//    public void patchUserByIdTest() throws URISyntaxException {
//        String url = this.getServiceUrl() + "users/" + userDto.getId();
//        URI uri = new URI(url);
//
//        String patchOperation =  """
//            [{
//                "op": "replace",
//                "path": "/name",
//                "value": "Nombre PATCH"
//            }]""";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        headers.set("Content-Type", "application/json-patch+json");
//        headers.setBearerAuth(this.jwt_token);
//
//        HttpEntity requestEntity = new HttpEntity<>(patchOperation, headers);
//
//        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.PATCH, requestEntity,String.class);
//
//        assertEquals("The response code must be 204", HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
//    }

    @Test
    @Order(5)
    public void deleteUserByIdTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId();
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.jwt_token);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.DELETE, requestEntity,String.class);

        assertEquals("The response code must be 204", HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
    }

    private String getServiceUrl() {
        return String.format("http://localhost:%d/api/", this.port);
    }
}
