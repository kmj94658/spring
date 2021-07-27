/**
 * 
 */
 'use strict';
 
 const $delBtn = document.getElementById("delBtn");
	$delBtn.addEventListener('click', e=>{
		const id = e.target.dataset.id;
		if(window.confirm('삭제하시겠습니까?')){
			window.location.href="/product/del?id="+id;}
	});

	const $modifyBtn = document.getElementById("modifyBtn");
		$modifyBtn.addEventListener('click', e=>{
			const id = e.target.dataset.id;
			window.location.href="/product/modifyForm";
		});