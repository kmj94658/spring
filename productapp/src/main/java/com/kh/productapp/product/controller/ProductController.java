package com.kh.productapp.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.productapp.product.dto.ProductDTO;
import com.kh.productapp.product.svc.ProductSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductSVC productSVC;
	
	//상품등록 양식
	@GetMapping("addProductForm")
	public String addProductForm() {
		log.info("String addProductForm() 호출됨!");
		return "product/addProductForm"; 
		//template 파일 밑에 논리적 파일주소
	}
	
	//상품등록 처리
	@PostMapping("/add")
	public String add(ProductDTO productDTO) {
		productSVC.addProduct(productDTO);
		return "redirect:/";
	}
	
	//상품수정 양식
	@GetMapping("/modifyForm")
	public String modifyForm(@RequestParam("id") String id, Model model) {
		ProductDTO productDTO = productSVC.searchProduct(id);
		model.addAttribute("pdto", productDTO);
		return "product/modifyForm";
	}
	
	//상품수정
	@PostMapping("/modify")
	public String modify(ProductDTO productDTO) {
		String msg = "";
		int result = productSVC.modifyProduct(productDTO);
		msg = (result == 1) ? "ok" : "nok";
		return "redirect:/product/all";
	}
	
	//상품삭제
	@GetMapping("/del")
	public String del(@RequestParam("id") String id) {
		String msg = "";
		int result = productSVC.deleteProduct(id);
		msg = (result == 1) ? "ok" : "nok";
		return "redirect:/product/all";
	}
	
	//전체상품
	@GetMapping("/productList")
	public String productList(Model model) {
		List<ProductDTO> list = productSVC.productList();
		model.addAttribute("plist",list);
		return "product/productList";
	}
	
	
}
