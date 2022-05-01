package kma.topic5.springwebsample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByTitleContainsOrIsbnContainsOrAuthorContains(String search1, String search2, String search3);

}
