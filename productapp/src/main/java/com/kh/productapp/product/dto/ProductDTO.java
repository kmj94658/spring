package com.kh.productapp.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor //디폴트생성자 만들어준다
@AllArgsConstructor //생성자 만들어준다
@Getter @Setter
@ToString
public class ProductDTO {
	private String id; //상품아이디
	private String name; //상품명
	private Integer stock; //상품수량
	private Integer price; //상품가격
		
}
