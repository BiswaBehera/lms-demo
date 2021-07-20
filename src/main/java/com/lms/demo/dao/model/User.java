package com.lms.demo.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(
        name = "user"
//        uniqueConstraints = @UniqueConstraint(
//                name = "UniqueName&ContactNo",
//                columnNames = {"name", "contact_no"}
//        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "password"
//            nullable = false
    )
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "is_admin")
    private Boolean isAdmin;
}
