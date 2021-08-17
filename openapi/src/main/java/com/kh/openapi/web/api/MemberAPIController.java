
package com.kh.openapi.web.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.openapi.web.api.req.MemberCreateReq;
import com.kh.openapi.web.api.req.MemberModifyReq;
import com.kh.openapi.web.api.req.Result;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberAPIController {

//웹은 굉장이 변동이 많은 곳. 웹에서 변화가 있더라도 service,repository에 영향 쉽게 받지 않도록 분리해서 관리(MemberCreateReq와 Member가 똑같은 이유)
	
   private final MemberRepository memberRepository;
   
   //회원등록   post   /api/members
   @ResponseBody
   @PostMapping("")
   public Result register(@RequestBody MemberCreateReq memberCreateReq) {
      
      //중복아이디 체크
      if(memberRepository.isDuplicated(memberCreateReq.getId())) {
         return new Result("01","중복아이디",null);
      }
      
      //중복 아이디가 없으면
      log.info("register:{}",memberCreateReq);
      Member member = new Member();
      
      BeanUtils.copyProperties(memberCreateReq, member);
      log.info("toMember:{}",member);
      
      Member savedMember = memberRepository.add(member);
      log.info("savedMember:{}",savedMember);
      
      return new Result("00","성공",savedMember);
   }
   //   회원수정   patch   /api/members
   @ResponseBody
   @PatchMapping("/{id}")
   public Result modify(
         @PathVariable String id,
         @RequestBody MemberModifyReq memberModifyReq) {

      Member member = new Member();
      BeanUtils.copyProperties(memberModifyReq, member);
      Member modifyedMember = memberRepository.modify(id, member);
      
      Result result = new Result();   
      if(modifyedMember == null) {
         result.setRtcd("01");
         result.setRtmsg("존재하는 회원이 없습니다.");
      }else {
         result.setRtcd("00");
         result.setRtmsg("성공");
         result.setData(modifyedMember);
      }

      return result;
   }
   //   회원조회   get   /api/members/{id}
   @ResponseBody
   @GetMapping("/{id}")
   public Result findById(@PathVariable String id) {
      
      Member member = memberRepository.find(id);
      Result result = new Result();   
      if(member == null) {
         result.setRtcd("01");
         result.setRtmsg("존재하는 회원이 없습니다.");
      }else {
         result.setRtcd("00");
         result.setRtmsg("성공");
         result.setData(member);
      }
      return result;
   }
   //   회원삭제   delete   /api/members/{id}
   @ResponseBody
   @DeleteMapping("/{id}")
   public Result del(@PathVariable String id) {
      
      Member member = memberRepository.del(id);
      Result result = new Result();   
      if(member == null) {
         result.setRtcd("01");
         result.setRtmsg("삭제하고자 하는 회원이 없습니다.");
      }else {
         result.setRtcd("00");
         result.setRtmsg("성공");
      }
      return result;
   }   
   
   //회원목록
   @ResponseBody
   @GetMapping("/all")
   public Result all(){
      List<Member> list = memberRepository.all();
      Result result = new Result();   
      if(list.size() == 0) {
         result.setRtcd("01");
         result.setRtmsg("회원 정보가 없습니다.");         
      }else {
         result.setRtcd("00");
         result.setRtmsg("성공");
         result.setData(list);
      }
      
      return result;
   }
   
   //모두 삭제
   @ResponseBody
   @DeleteMapping("/all")
   public Result removeAll() {
  	 memberRepository.clear();
  	 return new Result("00","모두 삭제되었습니다", null);
   }
}