package com.lms.demo.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "borrow_details")
public class BorrowDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "checkout_date",
            nullable = false
    )
    private Date checkoutDate;

    @Column(
            name = "due_date",
            nullable = false
    )
    private Date dueDate;

    @Column(name = "return_date")
    private Date returnDate;

    // set default values for fine and paid
    @Column(name = "fine")
    private Integer fine;

    @Column(name = "paid")
    private Boolean paid;

    @ManyToOne
    @JoinColumn(
            name = "fk_library_id",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "fk_barcode",
            referencedColumnName = "barcode",
            nullable = false
    )
    private BookItem bookItem;

    @Version
    private Integer version;
}
