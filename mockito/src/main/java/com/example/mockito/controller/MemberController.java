package com.example.mockito.controller;

import com.example.mockito.jpa.MemberDto;
import com.example.mockito.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<MemberDto>> getMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getMembers());
    }

    @GetMapping("/member/{name}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getMember(name));
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDto> saveMember(@RequestBody MemberDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveMember(dto));
    }

    @PostMapping("/member/{email}")
    public ResponseEntity<String> deleteMember(@PathVariable("email") String email) {

        return ResponseEntity.status(HttpStatus.OK).body(service.deleteMember(email));
    }

}
