/**
 * 
 */
 'use strict';
 
 const request = {
	get(url) {
		return fetch(url);
	},
	
	post(url,payload) {
		return fetch(url, {
			method:'POST',
			headers:{'content-Type': 'application/json'},
			body: JSON.stringify(payload)
		})
	},
	
	petch(url,payload) {
		return fetch(url, {
			method:'path',
			headers:{'content-Type': 'application/json'},
			body: JSON.stringify(payload)
		})
	},
	
	delete(url){
		return fetch(url, {method: 'delete'});
	}
	
};