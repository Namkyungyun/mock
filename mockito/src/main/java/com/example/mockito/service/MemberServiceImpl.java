package com.example.mockito.service;

import com.example.mockito.jpa.MemberDto;
import com.example.mockito.jpa.MemberEntity;
import com.example.mockito.jpa.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository repository;

    @Autowired
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MemberDto> getMembers() {
        List<MemberEntity> list = repository.findAll();
        List<MemberDto> result = new ArrayList<>();
        list.forEach(v -> result.add(new ModelMapper().map(v,MemberDto.class)));
        return result;
    }

    @Override
    public MemberDto getMember(String name) {
        MemberEntity member1 = repository.findByName(name);
        MemberDto result = new ModelMapper().map(member1, MemberDto.class);
        return result;
    }

    @Override
    public MemberDto saveMember(MemberDto memberDto) {
        MemberEntity member = new MemberEntity();
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        MemberEntity member1 = repository.save(member);
        MemberDto result = new ModelMapper().map(member1, MemberDto.class);
        return  result;
    }

    @Override
    public String deleteMember(String email) {
        try{
            MemberEntity member = repository.findByEmail(email);
            if(member.getEmail() != null) {
                repository.deleteByEmail(email);
            }
        }catch (NullPointerException ex) {
            ex.printStackTrace();
            return "회원이 없음";
        }
        return "삭제완료";
    }


}
