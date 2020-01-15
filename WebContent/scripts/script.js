console.log("working");
var formular = document.getElementById("formu");

formular.addEventListener('submit', function(e){
    e.preventDefault();
    console.log('you click me');

    var data = new FormData(formular);
    if (data.get('firstname') == ""){
        // var result=document.getElementById("firstname")
        // result.innerHTML="First name needed"
        alert ("You need to provide your first name.");
    }else if (data.get('lastname')==""){
        alert ("You need to provide your last name")
    }else if (data.get('street')=="" || data.get('city')=="" || data.get('state')==""|| data.get('zip')==""){
        alert ("You need to provide a valid address")
    }else if (data.get('email')==""){
        alert ("Please provide an email")
    }else if (data.get('username')==""){
        alert ("Please provide a username")
    }else if (data.get('password')== ""){
        alert ("Please provide a password")
    }
    console.log(data);
    console.log(data.get('firstname'));
})