package com.example.mockito.jpa;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberDto implements Serializable {
    private String email;
    private String name;
}
