package kma.topic5.springwebsample;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MyRestController {

    List<Book> bookList = new ArrayList<>();

    @RequestMapping(value = "/addBookWithREST", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> addBookWithREST(@RequestBody Book book) {
        bookList.add(book);
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }

    @GetMapping("/getBooksREST")
    public List<Book> getAllBooks(){
        return bookList;
    }

    @GetMapping("/clear")
    public void clearBook(){
        bookList.clear();
    }

    @GetMapping("/foundBooks")
    public List<Book> findBooks(
            @RequestParam(name = "searchInput",required = false) String field){
        if (field == null || field.isEmpty()) {
            return bookList;
        }
        return bookList.stream().filter(book -> book.getTitle().contains(field)
                || book.getIsbn().contains(field)).collect(Collectors.toList());
    }
}

