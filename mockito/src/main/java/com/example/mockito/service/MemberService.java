package com.example.mockito.service;

import com.example.mockito.jpa.MemberDto;
import java.util.List;

public interface MemberService {

    List<MemberDto> getMembers();

    MemberDto getMember(String name);

    MemberDto saveMember(MemberDto memberDto);

    String deleteMember(String email);
}
