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
                url: "adminCustomer/searchCustomer",
                type:"POST",
                contentType:"application/json;charseutf-8",
                dataType:"json",
//                data: {name:"Maxwell"},
                data: JSON.stringify({
                	telephone : "parknshop",
               // password:"parknshop"
	}),
//                data:{page:1,count:2},
                success: function(){
                alert("success");
            }}
    );
</script>
</head>
</html>