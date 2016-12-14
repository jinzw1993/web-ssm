<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>

<h2>Hello World!</h2>
</body>
<head>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery
/jquery-1.4.min.js"></script>
<script type="text/javascript">
    $.ajax(
            {
                url: "admin/login",
                type:"POST",
                contentType:"application/json;charseutf-8",
                dataType:"json",
//                data: {name:"Maxwell"},
                data: JSON.stringify({
	                /* ownId : 1,
	               	shopId : 1, 
	               	name : "Android", 
	               	price : 11111, 
	               	categoryId : 1, 
	               	detail : "detail2", 
	               	photoURL : "https://www.baidu.com/img/bd_logo1.png" */
	               	name : "asas",
                	password:"parknshop"
	}),
//                data:{page:1,count:2},
                success: function(){
                alert("success");
            }}
    );
</script>
</head>
</html>