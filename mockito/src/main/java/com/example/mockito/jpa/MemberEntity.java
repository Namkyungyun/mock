package com.example.mockito.jpa;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "members")
@Entity
public class MemberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 30)
    private String name;


}
