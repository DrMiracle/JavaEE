package kma.topic5.springwebsample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final EntityManager entityManager;

    @Transactional
    public Book createBook(Book book) {
        return entityManager.merge(book);
    }

    @Transactional
    public List<Book> findBooks(String field){
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.title LIKE :query OR b.isbn LIKE :query OR b.author LIKE :query", Book.class)
                .setParameter("query", '%' + field + '%')
                .getResultList();
    }


    @Transactional
    public List<Book> allBooks(){
        List<Book> allBooks = entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        return allBooks;
    }

    @Transactional
    public Book findBookById(long id){
        return entityManager.find(Book.class, id);
    }
}
