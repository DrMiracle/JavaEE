package kma.topic5.springwebsample;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    void setPort(int port) {
        RestAssured.port = port;
    }

    @BeforeEach
    public void clear(){
        RestAssured.when().get("/clear");
    }


    @Test
    public void shouldAddBook() throws Exception {
        Book book = new Book();
        book.setAuthor("author");
        book.setIsbn("1111");
        book.setTitle("Title");
        List<Book> books = new ArrayList<>();
        books.add(book);
        final String jsonRequest = objectMapper.writeValueAsString(book);
        List<Book> bookList = RestAssured
                .given().contentType("application/json")
                    .body(jsonRequest)
                .when()
                    .post("/addBookWithREST")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath()
                    .getList("$", Book.class);
        assertEquals(bookList, books);
    }

    @Test
    public void shouldFindBook() throws Exception {
        Book book = new Book();
        book.setAuthor("author");
        book.setIsbn("1111");
        book.setTitle("Title");
        Book book1 = new Book();
        book1.setAuthor("test");
        book1.setIsbn("test");
        book1.setTitle("test");
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        String jsonRequest = objectMapper.writeValueAsString(book);
        RestAssured
                .given().contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("/addBookWithREST");
        jsonRequest = objectMapper.writeValueAsString(book1);
        RestAssured
                .given().contentType("application/json")
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .post("/addBookWithREST");
        List<Book> bookList = RestAssured.given()
                .param("searchField","test")
                .when().get("/foundBooks")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("$", Book.class);
        assertEquals(books, bookList);
    }
}