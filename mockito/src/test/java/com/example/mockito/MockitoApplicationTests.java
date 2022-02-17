package com.example.mockito;

import com.example.mockito.controller.MemberController;
import com.example.mockito.jpa.MemberEntity;
import com.example.mockito.jpa.MemberRepository;
import com.example.mockito.service.MemberService;
import com.example.mockito.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MockitoApplicationTests {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("mockito 레파지토리 테스트")
    void mockMemberRepositoryTest() {
        //given
        MemberEntity member1 = MemberEntity.builder()
                .email("test@email.com")
                .name("mock유저1")
                .build();
        List<MemberEntity> members = new ArrayList<>();
        members.add(member1);

        given(memberRepository.findAll()).willReturn(members);

        //when
        List<MemberEntity> findMembers = memberRepository.findAll();
        System.out.println("findMembers = " + findMembers);

        //then
        Assertions.assertEquals(1, findMembers.size());
        Assertions.assertEquals(member1.getName(), findMembers.get(0).getName());
    }

}
