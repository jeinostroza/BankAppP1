function validateUser(){
	let userid = document.getElementById("username").value;
	let userpassword=document.getElementById("password").value;
//	console.log(userid + " " + userpassword);
	var message="";
	var flag=true;
	if(userid==""){
		message="Invalid Username";
		flag=false;
	}
	if(flag==true){
		if(userpassword==""){
			message="Invalid Password";
			flag=false;
		}
	}
	document.getElementById("errorMessage").innerText=message;
	return flag
	
}