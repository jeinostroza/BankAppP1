function Client(){
	
	var client = document.querySelevtor('#test');
	const Http = new XMLHttpRequest();
	const url = 'approveServlet';
	Http.open("GET",url);
	Http.send();
	
	Http.onreadustatechange=function(){
		if(this.resdyState==4 && this.status==200){
			console.log("From javascript"+Http.responseText)
		}
	}
}
