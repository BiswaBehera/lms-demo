package com.lms.demo.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book_item")
public class BookItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barcode")
    private Long barcode;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne
    @JoinColumn(
            name = "fk_isbn_code",
            referencedColumnName = "isbn_code"
    )
    private Book book;

    @Version
    private Integer version;
}
