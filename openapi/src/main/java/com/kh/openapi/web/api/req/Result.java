package com.kh.openapi.web.api.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * http api 응답메세지 구조
 * @author mypc
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

	private String rtcd;
	private String rtmsg;
	private T data;
}
