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
                data: JSON.stringify({ ownId:2,
                    shopId:2,
                    price:10,
                    categoryId:3,
                    detail:'hello',
                    name:"Maxwell",
                    photoURL:"C:\\Users\\unname\\Pictures\\Encyclopedia\\aclogo3.jpg"}),
//                data:{page:1,count:2},
                success: function(){
                alert("success");
            }}
    );
</script>
</html>
