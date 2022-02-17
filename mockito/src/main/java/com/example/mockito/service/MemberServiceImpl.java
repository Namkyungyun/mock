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

    public String hello() {
        return "hello!";
    }

}
