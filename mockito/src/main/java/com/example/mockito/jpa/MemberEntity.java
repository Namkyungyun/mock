package com.example.mockito.jpa;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "MEMBER")
@Entity
@Builder
public class MemberEntity implements Serializable {

    @Id
    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 30)
    private String name;


}
