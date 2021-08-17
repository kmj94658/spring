package com.kh.openapi.web.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberRepository {

   private static Map<Integer,Member> map = new HashMap<>();
   private static Integer seq = 0;
   
   //등록
   public Member add(Member member) {
      seq++;
      map.put(seq,member);
      return find(member.getId());
   }
   //수정
   public Member modify(String id, Member member) {

      Entry<Integer, Member> findedMember =
         map.entrySet().stream()
             .filter(mem->mem.getValue().getId().equals(id))
             .findFirst().orElse(null);
      
      if(findedMember != null) {
      	map.put(findedMember.getKey(), member);
      }
      
      return find(findedMember.getValue().getId());
   }
   //조회
   public Member find(String id) {
      Member findedMember = 
            map.values().stream()
                .filter(member->member.getId().equals(id))
                .findFirst().orElse(null);
      return findedMember;
   }
   //전체조회
   public List<Member> all(){
      List<Member> list = new ArrayList<>();
      map.values().forEach(member->list.add(member));
      return list;
   }
   //삭제
   public Member del(String id) {
      Entry<Integer, Member> findedMember =
         map.entrySet().stream()
             .filter(member->member.getValue().getId().equals(id))
             .findFirst().orElse(null);
      Member member = null;
      if(findedMember != null) {
      	member = map.remove(findedMember.getKey());
      }
      return member;
   }
   
   //아이디 중복 체크
   public boolean isDuplicated(String id) {
      boolean isExist = false;
      isExist = map.values().stream()
                         .anyMatch(member->member.getId().equals(id));
      return isExist;
   }
   
   //map에 저장된 요소 다 지우기
   public void clear() {
  	 map.clear();
   }
}



