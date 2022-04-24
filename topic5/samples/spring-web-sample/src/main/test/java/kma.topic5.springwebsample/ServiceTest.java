package kma.topic5.springwebsample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    @Sql(value = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createBook() {
        bookService.createBook(new Book());
        assertThat(bookService.allBooks()).hasSize(1);
    }

    @Test
    @Sql(value = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql("/test.sql")
    void findBooks() {
        assertThat(bookService.findBooks("test")).hasSize(2);
    }

    @Test
    @Sql(value = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql("/test.sql")
    void findAllBooks() {
        assertThat(bookService.allBooks()).hasSize(4);
    }

    @Test
    @Sql(value = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql("/test.sql")
    void findBookById() {
        assertThat(bookService.findBookById(1)).returns(1L, Book::getId).returns("t", Book::getTitle);
    }

}
