package kma.topic5.springwebsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MyRestController {

    @Autowired
    BookService bookService;

    @Transactional
    @RequestMapping(value = "/addBookWithREST", method = RequestMethod.POST)
    public List<Book> addBookWithREST(@RequestBody Book book) {
        bookService.createBook(book);
        return getAllBooks();
    }

    @GetMapping("/getBooksREST")
    public List<Book> getAllBooks(){
        return bookService.allBooks();
    }

//    @GetMapping("/clear")
//    public void clearBook(){
//        bookList.clear();
//    }

    @GetMapping("/foundBooks")
    public List<Book> findBooks(@RequestParam(name = "searchInput",required = false) String field){
        if (field == null || field.isEmpty()) {
            return getAllBooks();
        }
        return bookService.findBooks(field);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String bookInfo(Model model, @PathVariable(name="id") long id){
        Book book = bookService.findBookById(id);
        if (book != null) {
            return book.toString();
        }
        return "No book with such id.";
    }
}

