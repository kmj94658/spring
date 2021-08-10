package com.kh.gongiapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.gongiapp.dto.GongiDTO;
import com.kh.gongiapp.svc.GongiSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/gongi")
@RequiredArgsConstructor
public class GongiController {

	private final GongiSVC gongiSVC;
	
	/**
	 * 공지 등록 양식
	 * @return
	 */
	@GetMapping("/create")
	public String createForm() {
		log.info("String createForm() 호출됨!");
		
		return "gongi/createForm";
	}
	
	/**
	 * 공지 등록 처리
	 * @param gongiDTO
	 * @return
	 */
	@PostMapping("/create")
	public String create(@ModelAttribute GongiDTO gongiDTO, Model model, RedirectAttributes redirectAttributes) {
		log.info("String create() 호출됨!");
		log.info("num:{}, writer:{}, title:{}, content:{}, wdate:{}, udate:{}",
							gongiDTO.getNum(),gongiDTO.getWriter(),gongiDTO.getTitle(),gongiDTO.getContent(),gongiDTO.getWdate(),gongiDTO.getUdate());
		
		GongiDTO storedGongiDTO =  gongiSVC.createGongi(gongiDTO);
		model.addAttribute("gongiDTO", storedGongiDTO);
		redirectAttributes.addAttribute("num", storedGongiDTO.getNum());
		return "redirect:/gongi/detail/{num}";
	}
	
	/**
	 * 공지 조회
	 * @param num
	 * @return
	 */
	@GetMapping("/find")
	public String find(@RequestParam String num) {
		GongiDTO gongiDTO = gongiSVC.findGongi(num);
		log.info("String find() 호출됨!");
		
		return gongiDTO.toString();
	}
	
	/**
	 * 공지 수정 양식
	 * @param num
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{num}")
	public String editForm(@PathVariable String num, Model model) {
		GongiDTO gongiDTO = gongiSVC.findGongi(num);
		model.addAttribute("gongiDTO", gongiDTO);
		log.info("String editForm() 호출됨!");
		log.info("num:{}",num);
		
		return "gongi/editForm";
	}
	
	/**
	 * 공지 수정 처리
	 * @param num
	 * @param gongiDTO
	 * @param model
	 * @return
	 */
	@PostMapping("/edit/{num}")
	public String edit(@PathVariable String num, @ModelAttribute GongiDTO gongiDTO, Model model) {
		GongiDTO editedgongiDTO = gongiSVC.editGongi(num, gongiDTO);
		model.addAttribute("gongiDTO", editedgongiDTO);
		log.info("String edit() 호출됨!");
		
		return "redirect:/gongi/detail/{num}";
	}
	
	/**
	 * 공지 삭제
	 * @param num
	 * @return
	 */
	@GetMapping("/del/{num}")
	public String delete(@PathVariable String num) {
		gongiSVC.deleteGongi(num);
		log.info("String delete() 호출됨!");
		
		return "redirect:/gongi/gongiList";
	}
	
	/**
	 * 공지 목록
	 * @param model
	 * @return
	 */
	@GetMapping("/gongiList")
	public String gongiList(Model model) {
		List<GongiDTO> list = gongiSVC.GongiList();
		model.addAttribute("glist", list);
		log.info("String gongiList() 호출됨!");
		
		return "gongi/gongiList";
	}
	
	/**
	 * 공지 상세
	 * @param num
	 * @param model
	 * @return
	 */
	@GetMapping("/detail/{num}")
	public String gongiDetail(@PathVariable("num") String num, Model model) {
		GongiDTO gongiDTO = gongiSVC.findGongi(num);
		model.addAttribute("gongiDTO", gongiDTO);
		
		return "gongi/gongiDetail";
	}
}
