package com.example.mockito;

import com.example.mockito.jpa.MemberDto;
import com.example.mockito.jpa.MemberEntity;
import com.example.mockito.jpa.MemberRepository;
import com.example.mockito.service.MemberServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@SpringBootTest
@Profile("test")
@ExtendWith(MockitoExtension.class)
class MockitoApplicationTests {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("mockito 레파지토리 테스트-findAll")
    void testFindAll() {
        //given
        MemberEntity member1 = new MemberEntity();
        member1.setId(1l);
        member1.setEmail("test@email.com");
        member1.setName("mock유저1");

        List<MemberEntity> members = new ArrayList<>();
        members.add(member1);

        given(memberRepository.findAll()).willReturn(members);

        //when
        List<MemberEntity> findMembers = memberRepository.findAll();
        System.out.println("findMembers = " + findMembers);

        //then
        Assert.assertEquals(1, findMembers.size());
        Assert.assertEquals(member1.getName(), findMembers.get(0).getName());

        verify(memberRepository).findAll();
    }

    @Test
    @DisplayName("mockito 레파지토리 테스트-findByName")
    void testFindByName() {
        MemberEntity member1 = new MemberEntity();
        member1.setId(1l);
        member1.setEmail("test@email.com");
        member1.setName("mock유저1");

        when(memberRepository.findById(1l)).thenReturn(Optional.of(member1));

        Optional<MemberEntity> result = memberRepository.findById(1l);

        assertThat(result).isNotNull();

        verify(memberRepository).findById(1l);

    }

    @Test
    @DisplayName("mockito 레파지토리 테스트-save")
    void save() {
        MemberDto member1 = new MemberDto();
        member1.setId(1l);
        member1.setEmail("test@email.com");
        member1.setName("mock유저1");

        ModelMapper mapper = new ModelMapper();
        MemberEntity member = mapper.map(member1, MemberEntity.class);

        when(memberRepository.save(any(MemberEntity.class))).thenReturn(member);

        MemberDto result = memberService.saveMember(new MemberDto());
        assertThat(result).isNotNull();

        verify(memberRepository).save(any(MemberEntity.class));
    }

    @Test
    void deleteById() {
        memberRepository.deleteById(1l);

        verify(memberRepository).deleteById(anyLong());
    }

    @Test
    void delete() {
        MemberEntity member = new MemberEntity();
        member.setId(1l);
        member.setName("nace");
        member.setEmail("email");

        memberRepository.delete(member);

        verify(memberRepository).delete(any(MemberEntity.class));
    }



}
