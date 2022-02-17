package com.example.mockito.jpa;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberDto implements Serializable {
    private Long id;
    private String email;
    private String name;
}
