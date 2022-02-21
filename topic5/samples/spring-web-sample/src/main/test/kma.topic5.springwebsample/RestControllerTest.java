package kma.topic5.springwebsample;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyRestController.class)
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void clearBookList() throws Exception {
        mockMvc.perform(get("/clear"));
    }
    @Test
    public void addBookWithRest() throws Exception {
        Book book = new Book();
        book.setAuthor("test");
        book.setIsbn("1111");
        book.setTitle("test1");
        List<Book> books = new ArrayList<>();
        books.add(book);
        final String jsonRequest = objectMapper.writeValueAsString(book);
        mockMvc.perform(post("/addBookWithREST")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
    }

    @Test
    void getBooks() throws Exception {
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
        mockMvc.perform(post("/addBookWithREST")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk());
        jsonRequest = objectMapper.writeValueAsString(book1);
        mockMvc.perform(post("/addBookWithREST")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk());
        mockMvc.perform(get("/getBooksREST"))
                .andExpect(content().json(objectMapper.writeValueAsString(books)));

    }

    @Test
    void findBooks() throws Exception {
        Book book = new Book();
        book.setAuthor("author");
        book.setIsbn("1111");
        book.setTitle("Title");
        List<Book> books = new ArrayList<>();
        books.add(book);
        String jsonRequest = objectMapper.writeValueAsString(book);
        mockMvc.perform(post("/addBookWithREST")
                .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk());
        mockMvc.perform(get("/foundBooks")
                .param("searchField","test"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
    }
}