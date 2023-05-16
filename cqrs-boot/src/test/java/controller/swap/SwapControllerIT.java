package controller.swap;

import es.plexus.Application;
import es.plexus.config.security.UserCredentialsDto;
import es.plexus.dto.book.BookDto;
import es.plexus.dto.swap.SwapDto;
import es.plexus.dto.swap.SwapPostDto;
import es.plexus.dto.user.UserDto;
import es.plexus.dto.user.UserPostDto;
import es.plexus.repository.book.BookJpaRepository;
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
public class SwapControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private static String jwt_tokenOrigin;
    private static String jwt_tokenTarget;

    private static UserDto userDtoOrigin;
    private static UserDto userDtoTarget;

    private static BookDto bookDtoOrigin;
    private static BookDto bookDtoTarget;
    private static SwapDto swapDto;
    @Autowired
    private BookJpaRepository bookJpaRepository;


    private String getServiceUrl() {
        return String.format("http://localhost:%d/api/", this.port);
    }

    @Test
    @Order(1)
    public void createUsersTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "register";
        URI uri = new URI(url);
        UserPostDto userPostDtoOrigin = new UserPostDto(
                "prueba5",
                "prueba5",
                "prueba5",
                "prueba5@gmail.com",
                "1234567",
                LocalDate.of(2020, 02, 02),
                "prueba5");
        UserPostDto userPostDtoTarget = new UserPostDto(
                "prueba6",
                "prueba6",
                "prueba6",
                "prueba6@gmail.com",
                "1234567",
                LocalDate.of(2020, 02, 02),
                "prueba6");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserPostDto> requestEntityOrigin = new HttpEntity<>(userPostDtoOrigin, headers);
        HttpEntity<UserPostDto> requestEntityTarget = new HttpEntity<>(userPostDtoTarget, headers);

        ResponseEntity<UserDto> responseOrigin = testRestTemplate.postForEntity(uri, requestEntityOrigin, UserDto.class);
        ResponseEntity<UserDto> responseTarget = testRestTemplate.postForEntity(uri, requestEntityTarget, UserDto.class);

        this.userDtoOrigin = responseOrigin.getBody();
        this.userDtoTarget = responseTarget.getBody();

        assertEquals("The response code must be 201", HttpStatus.CREATED.value(), responseOrigin.getStatusCodeValue());
        assertEquals("The name must be prueba1", responseOrigin.getBody().getName(), userPostDtoOrigin.getName());

        assertEquals("The response code must be 201", HttpStatus.CREATED.value(), responseTarget.getStatusCodeValue());
        assertEquals("The name must be prueba1", responseTarget.getBody().getName(), userDtoTarget.getName());
    }

    @Test
    @Order(2)
    public void loginUserOriginTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "login";
        URI uri = new URI(url);
        UserCredentialsDto credentialsDto = new UserCredentialsDto(userDtoOrigin.getUsername(), "1234567");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserCredentialsDto> requestEntity = new HttpEntity<>(credentialsDto, headers);

        ResponseEntity<Map> response = testRestTemplate.postForEntity(uri, requestEntity, Map.class);

        Map<String, String> responseBody = response.getBody();
        String authorizationHeader = responseBody.get("Authorization");
        this.jwt_tokenOrigin = authorizationHeader.replace("Bearer ", "");

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertFalse("The response must not contain records", response.getBody()==null);
    }
    @Test
    @Order(2)
    public void loginUserTargetTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "login";
        URI uri = new URI(url);
        UserCredentialsDto credentialsDto = new UserCredentialsDto(userDtoTarget.getUsername(), "1234567");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserCredentialsDto> requestEntity = new HttpEntity<>(credentialsDto, headers);

        ResponseEntity<Map> response = testRestTemplate.postForEntity(uri, requestEntity, Map.class);

        Map<String, String> responseBody = response.getBody();
        String authorizationHeader = responseBody.get("Authorization");
        this.jwt_tokenTarget = authorizationHeader.replace("Bearer ", "");

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertFalse("The response must not contain records", response.getBody()==null);
    }

    @Test
    @Order(3)
    public void postBookForUserTest() throws URISyntaxException {
        String urlOrigin = this.getServiceUrl() + "users/" + userDtoOrigin.getId() + "/books";
        URI uriOrigin = new URI(urlOrigin);
        String urlTarget = this.getServiceUrl() + "users/" + userDtoTarget.getId() + "/books";
        URI uriTarget = new URI(urlTarget);
        BookDto bookDtoTarget = new BookDto(
                0,
                "titulo2",
                "autor2",
                "editorial2",
                "1934567890122"
        );
        BookDto bookDtoOrigin = new BookDto(
                1,
                "titulo3",
                "autor3",
                "editorial3",
                "1934567890123"
        );

        HttpHeaders headersOrigin = new HttpHeaders();
        headersOrigin.setContentType(MediaType.APPLICATION_JSON);
        headersOrigin.setBearerAuth(jwt_tokenOrigin);
        HttpHeaders headersTarget = new HttpHeaders();
        headersTarget.setContentType(MediaType.APPLICATION_JSON);
        headersTarget.setBearerAuth(jwt_tokenTarget);

        HttpEntity<BookDto> requestEntityOrigin = new HttpEntity<>(bookDtoOrigin, headersOrigin);
        HttpEntity<BookDto> requestEntityTarget = new HttpEntity<>(bookDtoTarget, headersTarget);

        ResponseEntity<BookDto> responseOrigin = testRestTemplate.postForEntity(uriOrigin, requestEntityOrigin, BookDto.class);
        ResponseEntity<BookDto> responseTarget = testRestTemplate.postForEntity(uriTarget, requestEntityTarget, BookDto.class);

        this.bookDtoOrigin = responseOrigin.getBody();
        this.bookDtoTarget = responseTarget.getBody();

        assertEquals("The response code must be 201", HttpStatus.CREATED.value(), responseOrigin.getStatusCodeValue());
        assertEquals("The response isb must by" + bookDtoOrigin.getIsbn(), responseOrigin.getBody().getIsbn(), bookDtoOrigin.getIsbn());
        assertEquals("The response code must be 201", HttpStatus.CREATED.value(), responseTarget.getStatusCodeValue());
        assertEquals("The response isb must by" + bookDtoTarget.getIsbn(), responseTarget.getBody().getIsbn(), bookDtoTarget.getIsbn());
    }

    @Test
    @Order(4)
    public void postSwapForUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDtoOrigin.getId() + "/swaps";
        URI uri = new URI(url);

        SwapPostDto swapPostDto = new SwapPostDto(
                userDtoTarget.getId(),
                List.of(bookDtoOrigin.getId()),
                List.of(bookDtoTarget.getId()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_tokenOrigin);

        HttpEntity<SwapPostDto> requestEntity = new HttpEntity<>(swapPostDto, headers);

        ResponseEntity<SwapDto> response = testRestTemplate.postForEntity(uri, requestEntity, SwapDto.class);

        this.swapDto = response.getBody();

        assertEquals("The response code must be 200", HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals("The user origin id must be " + userDtoOrigin, response.getBody().getUserOrigin().getId(), userDtoOrigin.getId());
    }

    @Test
    @Order(5)
    public void getSwapForUserByIdTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDtoTarget.getId() + "/swaps/" + swapDto.getId();
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(this.jwt_tokenTarget);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<SwapDto> response = testRestTemplate.exchange(uri, HttpMethod.GET, requestEntity,SwapDto.class);

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("The user origin id must be " + userDtoOrigin, response.getBody().getUserOrigin().getId(), userDtoOrigin.getId());
    }

    @Test
    @Order(5)
    public void getAllSwapsForUserByIdTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDtoTarget.getId() + "/swaps";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(this.jwt_tokenTarget);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<SwapDto[]> response = testRestTemplate.exchange(uri, HttpMethod.GET, requestEntity,SwapDto[].class);

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("The user origin id must be " + userDtoOrigin, response.getBody().length, 1);
    }

}
