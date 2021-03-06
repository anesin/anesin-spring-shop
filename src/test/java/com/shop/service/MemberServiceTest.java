package com.shop.service;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  PasswordEncoder passwordEncoder;


  public Member createMember() {
    MemberFormDto memberFormDto = new MemberFormDto();
    memberFormDto.setEmail("test@email.com");
    memberFormDto.setName("아네신");
    memberFormDto.setAddress("경기도 성남시 판교");
    memberFormDto.setPassword("1234");
    return Member.createMember(memberFormDto, passwordEncoder);
  }


  @Test
  @DisplayName("회원 가입 테스트")
  void saveMember() {
    Member member = createMember();
    Member savedMember = memberService.saveMember(member);
    assertEquals(member.getEmail(),    savedMember.getEmail());
    assertEquals(member.getName(),     savedMember.getName());
    assertEquals(member.getAddress(),  savedMember.getAddress());
    assertEquals(member.getPassword(), savedMember.getPassword());
    assertEquals(member.getRole(),     savedMember.getRole());
  }


  @Test
  @DisplayName("중복 회원 가입 테스트")
  void saveDuplicateMember() {
    Member member1 = createMember();
    Member member2 = createMember();
    Member savedMember = memberService.saveMember(member1);
    Throwable e = assertThrows(IllegalStateException.class, () -> {
      memberService.saveMember(member2);
    });
    assertEquals("Already Member.", e.getMessage());
  }

}