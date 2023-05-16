package controller.book;

import es.plexus.Application;
import es.plexus.config.security.UserCredentialsDto;
import es.plexus.dto.book.BookDto;
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
public class BookControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private static String jwt_token;

    private static UserDto userDto;
    private static BookDto bookDto;

    private String getServiceUrl() {
        return String.format("http://localhost:%d/api/", this.port);
    }
    @Test
    @Order(1)
    public void createUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "register";
        URI uri = new URI(url);
        UserPostDto userPostDto = new UserPostDto(
                "prueba4",
                "prueba4",
                "prueba4",
                "prueba4@gmail.com",
                "1234567",
                LocalDate.of(2020, 02, 02),
                "prueba2");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserPostDto> requestEntity = new HttpEntity<>(userPostDto, headers);

        ResponseEntity<UserDto> response = testRestTemplate.postForEntity(uri, requestEntity, UserDto.class);

        this.userDto = response.getBody();

        assertEquals("The response code must be 201", HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals("The name must be prueba1", response.getBody().getName(), userPostDto.getName());
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
    public void postBookForUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId() + "/books";
        URI uri = new URI(url);
        BookDto bookDto = new BookDto(
                0,
                "titulo1",
                "autor1",
                "editorial1",
                "9234567890127"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_token);

        HttpEntity<BookDto> requestEntity = new HttpEntity<>(bookDto, headers);

        ResponseEntity<BookDto> response = testRestTemplate.postForEntity(uri, requestEntity, BookDto.class);
        this.bookDto = response.getBody();

        assertEquals("The response code must be 201", HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals("The response isb must by" + bookDto.getIsbn(), response.getBody().getIsbn(), bookDto.getIsbn());
    }

    @Test
    @Order(4)
    public void getBooksForUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId() + "/books";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_token);
        HttpEntity<BookDto> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<BookDto[]> response = testRestTemplate.exchange(uri, HttpMethod.GET, requestEntity, BookDto[].class);

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("The response isb must by" + bookDto.getIsbn(), response.getBody()[0].getIsbn(), bookDto.getIsbn());
    }

    @Test
    @Order(5)
    public void getBookForUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId() + "/books/" + bookDto.getId();
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_token);
        HttpEntity<BookDto> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<BookDto> response = testRestTemplate.exchange(uri, HttpMethod.GET, requestEntity, BookDto.class);

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("The response isb must by" + bookDto.getIsbn(), response.getBody().getIsbn(), bookDto.getIsbn());
    }

    @Test
    @Order(6)
    public void getAllBooksTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "books";
        URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_token);
        HttpEntity<BookDto> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<BookDto[]> response = testRestTemplate.exchange(uri, HttpMethod.GET, requestEntity, BookDto[].class);

        assertEquals("The response code must be 200", HttpStatus.OK.value(), response.getStatusCodeValue());
        assertTrue("The response length must be " + response.getBody().length, response.getBody().length >=1);
    }

    @Test
    @Order(7)
    public void updateBookForUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId() + "/books/" + bookDto.getId();
        URI uri = new URI(url);
        BookDto updatedBookDto = new BookDto();
        updatedBookDto.setTitle("Nuevo título");
        updatedBookDto.setId(bookDto.getId());
        updatedBookDto.setIsbn(bookDto.getIsbn());
        updatedBookDto.setAuthor(bookDto.getAuthor());
        updatedBookDto.setEditorial(bookDto.getEditorial());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_token);
        HttpEntity<BookDto> requestEntity = new HttpEntity<>(updatedBookDto, headers);

        ResponseEntity<Void> response = testRestTemplate.exchange(uri, HttpMethod.PUT, requestEntity, Void.class);

        assertEquals("The response code must be 204", HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
    }

    /*@Test
    @Order(8)
    public void patchBookForUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId() + "/books/" + bookDto.getId();
        URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_token);


        HttpEntity<JsonPatch> requestEntity = new HttpEntity<>(patch, headers);

        ResponseEntity<Void> response = testRestTemplate.exchange (
                url,
                HttpMethod.PATCH,
                requestEntity,
                Void.class
        );

        assertEquals("The response code must be 204", HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
    }*/

    @Test
    @Order(9)
    public void deleteBookForUserTest() throws URISyntaxException {
        String url = this.getServiceUrl() + "users/" + userDto.getId() + "/books/" + bookDto.getId();
        URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt_token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = testRestTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, Void.class);


        assertEquals("The response code must be 204", HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
    }


}
