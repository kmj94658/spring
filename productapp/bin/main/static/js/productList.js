/**
 * 
 */
 'use strict';
	
	const $detailBtn = document.getElementById("detailBtn");
		$detailBtn.addEventListener('click', e=>{
			const id = e.target.dataset.id;
			window.location.href="/product/productDetail?id="+id;
		});