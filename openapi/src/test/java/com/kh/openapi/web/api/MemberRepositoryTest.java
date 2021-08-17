package com.kh.openapi.web.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberRepositoryTest {

   private MemberRepository members = new MemberRepository();
      
   @Test
   @Order(1)
   @DisplayName("add 테스트")
   void add() {
      Member member = new Member();
      member.setId("id1");
      member.setPw("pw1");
      member.setName("name1");
      members.add(member);
      
      Member member2 = new Member();
      member2.setId("id2");
      member2.setPw("pw2");
      member2.setName("name2");
      members.add(member2);
      
      log.info("add:{}",members.all());
      
   }
   
   @Test
   @Order(2)
   @DisplayName("modify 테스트")
   void modify() {
      Member member = new Member();
      member.setId("id1");
      member.setPw("pw1");
      member.setName("name10");
      
      Member beforeMember = members.find("id1");      
      members.modify("id1", member);
      Member afterMember = members.find("id1");
      
      Assertions.assertThat(beforeMember.getName()).isNotEqualTo(afterMember.getName());
      log.info("modify:{}",members.all());
   }
   
   @Test
   @Order(3)
   @DisplayName("find 테스트")
   void find() {
      
      Member member = members.find("id1");
      log.info("find:{}",member.toString());
      Assertions.assertThat("id1").isEqualTo(member.getId());
   }
   
   @Test
   @Order(4)
   @DisplayName("del 테스트")
   void del() {
      members.del("id1");
      Assertions.assertThat(members.find("id1")).isEqualTo(null);
      
      log.info("all:{}",members.all());
   }
   
   @Test
   @Order(5)
   @DisplayName("아이디 중복확인")
   void isDupCkh() {
      Assertions.assertThat(members.isDuplicated("id2")).isEqualTo(true);
      
   }
}









