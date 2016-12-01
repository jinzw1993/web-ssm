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
                url: "product/update",
                type:"POST",
                contentType:"application/json",
                dataType:"json",
                data: JSON.stringify({
                    id:11,
                    productPhotoId:18,
                    categoryId:250,
                    name:"AMD",
                    price:1,
                    categoryId:200,
                    detail:"garbage cpu",
                    photoURL:"D:\\Users\\Pictures\\Encyclopedia\\aclogo3.jpg"}),
         //       data:{page:1,count:2},
                success: function(){

                alert("success");
            }}
    );
</script>
</html>
