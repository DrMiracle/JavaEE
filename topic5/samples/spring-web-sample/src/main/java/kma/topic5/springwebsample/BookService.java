package kma.topic5.springwebsample;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> findBooks(String field){
        return bookRepository.findBookByTitleContainsOrIsbnContainsOrAuthorContains(field, field, field);
    }

    @Transactional
    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book findBookById(long id){
        return bookRepository.findById(id).get();
    }

    @Transactional
    public List<Book> paginatedBooks(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return bookRepository.findAll(pageable).getContent();
    }
}
