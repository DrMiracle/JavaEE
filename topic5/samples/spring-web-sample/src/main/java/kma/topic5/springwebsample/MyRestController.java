package kma.topic5.springwebsample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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

    @RequestMapping(value = "/book/number_of_books")
    public int numberOfBooks(){
        return bookService.allBooks().size();
    }

    @RequestMapping(value = "/book")
    public List<Book> paginateBooks(@RequestParam(name = "page") String page, @RequestParam(name = "size") String size){
        return bookService.paginatedBooks(Integer.parseInt(page), Integer.parseInt(size));
    }
}

