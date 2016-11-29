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
                url: "shop/getShops",
                type:"POST",
                //contentType:"application/json",
                dataType:"json",
                //data: JSON.stringify({ownerId:"7", name:"店2",contact:"cont",email:"123@qwe.com",telephone:"1234123",status:"0"}),
                data:{page:2,count:5},
                success: function(){
                alert("success");
            }}
    );
</script>
</html>
