package kma.topic5.springwebsample;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column(name = "title")
    String title;

    @Column(name = "isbn")
    String isbn;

    @Column(name = "author")
    String author;

    @ManyToMany(mappedBy = "books")
    List<User> users;
}
