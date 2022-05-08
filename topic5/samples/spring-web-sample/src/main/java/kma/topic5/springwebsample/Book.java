package kma.topic5.springwebsample;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @NotEmpty(message = "Book title can't be empty")
    @Column(name = "title")
    String title;

    @NotEmpty(message = "ISBN can't be empty")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN has bad format")
    @Column(name = "isbn")
    String isbn;

    @NotEmpty(message = "Author name can't be empty")
    @Column(name = "author")
    String author;

    @ManyToMany(mappedBy = "books")
    List<User> users;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
