package com.lms.demo.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(
        name = "book"
//        uniqueConstraints = @UniqueConstraint(
//                name = "UniqueTitle&Author",
//                columnNames = {"title", "fk_author_id"}
//        )
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn_code")
    private Long isbnCode;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(name = "genre")
    private String genre;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "fk_author_id",
            referencedColumnName = "id"
    )
    private Author author;
}
