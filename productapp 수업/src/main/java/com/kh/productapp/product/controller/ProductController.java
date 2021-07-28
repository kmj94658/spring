package com.kh.productapp.product.controller;


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
	
	//@Autowired  //생성자를 통한 DI
//	public ProductController(ProductSVC productSVC) {
//		this.productSVC = productSVC;
//	}
	
//	@Autowired  //setter메소드를 통한 DI
//	public void setterProductSVC(ProductSVC productSVC) {
//		this.productSVC = productSVC;
//	}
	
	//spring boot의 기본 로깅시스템은 slf4j를 인터페이스로 구현체는 logback을 사용한다.
	//private final static Logger log = LoggerFactory.getLogger(ProductController.class);
	/**
	 * 상품목록
	 * @return
	 */
	@GetMapping("/productList")
	public String productList(Model model) {
		
		List<ProductDTO> list = productSVC.findAll();
		model.addAttribute("pList", list);
		return "product/productList";
	}
	/**
	 * 상품등록
	 * @return
	 */
	@GetMapping("/productForm")
	public String productForm() {
		
		return "product/productForm"; //view의 논리적 이름.
	}
	/**
	 * 상품등록
	 * @return
	 */
	//@PostMapping("")
	public String product(
			@RequestParam("pid") String pid,
			@RequestParam("pname") String pname,
			@RequestParam("pcount") Integer pcount,
			@RequestParam("pprice") Integer pprice) {
		
		//trace,degug,info,warn,error
//		log.trace("pid:{},pname:{},pcount:{},pprice:{}",pid,pname,pcount,pprice);
//		log.debug("pid:{},pname:{},pcount:{},pprice:{}",pid,pname,pcount,pprice);
		log.info("pid:{},pname:{},pcount:{},pprice:{}",pid,pname,pcount,pprice);
//		log.warn("pid:{},pname:{},pcount:{},pprice:{}",pid,pname,pcount,pprice);
//		log.error("pid:{},pname:{},pcount:{},pprice:{}",pid,pname,pcount,pprice);
		
		return "product/productDetail";
	}
	
	//@PostMapping("")
	public String product2(
			@RequestParam String pid,			//파라미터 이름과 매개변수이름이 같으면 생략가능.
			@RequestParam String pname,
			@RequestParam Integer pcount,
			@RequestParam Integer pprice) {
		
		log.info("pid:{},pname:{},pcount:{},pprice:{}",pid,pname,pcount,pprice);		
		return "product/productDetail";
	}	
	
	@PostMapping("")
	public String product3(
			@ModelAttribute ProductDTO productDTO,
			Model model,
			RedirectAttributes redirectAttributes) {
		
//		log.info("pid:{},pname:{},pcount:{},pprice:{}",
//				productDTO.getPid(),
//				productDTO.getPname(),
//				productDTO.getPcount(),
//				productDTO.getPprice());	
		
//		model.addAttribute("productDTO", productDTO);
		
		ProductDTO storedProductDTO = productSVC.createProduct(productDTO);
		model.addAttribute("productDTO", storedProductDTO);
		redirectAttributes.addAttribute("pid", storedProductDTO.getPid());
		
		log.info("저장후-productdto:{}",productDTO.toString());
		return "redirect:/product/productDetail/{pid}";
	}		
	
	/**
	 * 상품상세
	 * @return
	 */
	//@GetMapping("/productDetail")
	public String productDetail(
			@RequestParam("pid") String pid, Model model) {
		
		ProductDTO productDTO = productSVC.findProduct(pid);
		model.addAttribute("productDTO", productDTO);
		return "product/productDetail";
	}
	
	/**
	 * 상품상세
	 * @return
	 */	
	@GetMapping("/productDetail/{pid}")
	public String productDetail2(
			@PathVariable("pid") String pid, Model model) {
		
		ProductDTO productDTO = productSVC.findProduct(pid);
		model.addAttribute("productDTO", productDTO);
		return "product/productDetail";
	}	
	/**
	 * 상품수정양식
	 * @param pid
	 * @param model
	 * @return
	 */
	@GetMapping("/modifyForm/{pid}")
	public String modifyForm(
			@PathVariable("pid") String pid, Model model) {
		
		log.info("pid:{}",pid);
		ProductDTO productDTO = productSVC.findProduct(pid);
		model.addAttribute("productDTO", productDTO);
		
		return "product/modifyProductForm";
	}
	/**
	 * 상품수정
	 * @param pid
	 * @param productDTO
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/{pid}/modi")
	public String modifyProduct(
			@PathVariable("pid") String pid,
			@ModelAttribute ProductDTO productDTO,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		ProductDTO modifyedProductDTO = productSVC.modifyProduct(pid, productDTO);
		model.addAttribute("productDTO", modifyedProductDTO);
		redirectAttributes.addAttribute("pid", modifyedProductDTO.getPid());
		
		return "redirect:/product/productDetail/{pid}";
	}
	/**
	 * 상품삭제
	 * @param pid
	 * @return
	 */
	@GetMapping("/{pid}/del")
	public String deleteProduct(@PathVariable("pid") String pid) {
		
		productSVC.deleteProduct(pid);
		return "redirect:/product/productList";
	}
}

