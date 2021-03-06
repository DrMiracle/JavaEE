package kma.topic5.springwebsample;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MyRestController {

    @Autowired
    BookService bookService;

    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/addBookWithREST", method = RequestMethod.POST)
    public List<Book> addBookWithREST(@RequestBody Book book) {
        bookService.createBook(book);
        return getAllBooks();
    }

    @ResponseBody
    @GetMapping("/getBooksREST")
    public List<Book> getAllBooks(){
        List<Book> allBooks = bookService.allBooks();
        allBooks.forEach(book -> book.setUsers(null));
        return allBooks;
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


    @RequestMapping(value = "/toWishlist/{id}", method = RequestMethod.GET)
    public String addToWishlist(Model model, @PathVariable(name="id") long id, Authentication authentication){
        User user = userRepository.findByLogin(authentication.getName()).get();
        List<Book> wishlist = user.getBooks();
        if (wishlist.stream().anyMatch(book -> book.getId()==id)) {
            wishlist = wishlist.stream().dropWhile(book -> book.getId()==id).collect(Collectors.toList());
        } else {
            wishlist.add(bookService.findBookById(id));
        }
        user.setBooks(wishlist);
        userRepository.save(user);
        return "Book changed!";
    }
}
