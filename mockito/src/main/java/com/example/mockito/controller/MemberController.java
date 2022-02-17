package com.example.mockito.controller;

import com.example.mockito.jpa.MemberDto;
import com.example.mockito.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MemberController {

    private MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> getMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getMembers());
    }

}
