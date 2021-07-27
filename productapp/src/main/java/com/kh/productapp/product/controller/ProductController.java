package com.kh.productapp.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//spring boot의 기본 로깅시스템은 slf4j를 인터페이스로 구현체는 logback을 사용한다
	//log의 종류는 trace,debug,info,warn,error
	//private final static Logger log = LoggerFactory.getLogger(ProductController.class);
	private final ProductSVC productSVC;
	
	//상품목록
	@GetMapping("/productList")
	public String productList(Model model) {
		List<ProductDTO> list = productSVC.productList();
		model.addAttribute("pList",list);
		return "product/productList"; //뷰 이름(스트링)을 리턴(논리적인 뷰: 템플릿밑에 product밑에 productList)
	}
	
	//상품상세
	@GetMapping("/productDetail") //요청url과
	public String productDetail(@RequestParam("id") String id, Model model) { //메소드 이름과
		log.info("productDetail() 호출됨!");
		ProductDTO productDTO = productSVC.searchProduct(id);
		model.addAttribute("product",productDTO);
		return "product/productDetail"; //뷰이름이 같을 필요는 없다. 그냥 편의상.
	}
	
	@GetMapping("/productDetail/{id}")
	public String productDetail2(@PathVariable("id") String id, Model model) {
		ProductDTO productDTO = productSVC.searchProduct(id);
		model.addAttribute("product",productDTO);
		return "product/productDetail";
	}
	
	
	//상품등록 양식
	@GetMapping("/addProductForm")
	public String addProductForm() {
		log.info("String addProductForm() 호출됨!");
		return "product/addProductForm"; 
		//template 파일 밑에 논리적 파일주소
	}
	
	//상품등록 처리
	@PostMapping("/add")
	public String add(@ModelAttribute ProductDTO productDTO) {
		productSVC.addProduct(productDTO);
		return "product/productDetail";
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
		return "redirect:/product/productList";
	}
	
	//상품삭제
	@GetMapping("/del")
	public String del(@RequestParam("id") String id) {
		String msg = "";
		int result = productSVC.deleteProduct(id);
		msg = (result == 1) ? "ok" : "nok";
		return "redirect:/product/productList";
	}
	
	
	
}
