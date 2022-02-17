package com.example.mockito.service;

import com.example.mockito.jpa.MemberDto;


import java.util.List;

public interface MemberService {

    List<MemberDto> getMembers();

}
