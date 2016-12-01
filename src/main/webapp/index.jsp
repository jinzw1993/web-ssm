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
                url: "product/add",
                type:"POST",
                contentType:"application/json",
                dataType:"json",
                data: JSON.stringify({ ownId:1,shopId:2,
                                name:"Maxwell",price:299.9,categoryId:2,detail:"this coffee is very good",
                                photoURL:"C:\\Users\\unname\\Pictures\\Encyclopedia\\aclogo.jpg"}),
            //    data: JSON.stringify({id:1},{name:"pname"},{}),
            //    data:{},
                success: function(){

                alert("success");
            }}
    );
</script>
</html>
