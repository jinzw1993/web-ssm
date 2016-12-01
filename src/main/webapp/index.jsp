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
                url: "product/getNum",
             //   type:"POST",
                contentType:"application/json",
               dataType:"json",
            //  data: JSON.stringify({password:"qwifj",email:"emai@.com",status:2,isEmailVerified:1}),
               data:{ownerId:2,page:1,pageNum:3},
//
//                data: JSON.stringify({
//                    id: 11,
//                    photoURL:"updated path",
//                    productPhotoId:18,
//                    categoryId:2501,
//                   name:"AMD The 360 degree rolled",
//                    price:2,
//                    detail:"garbage cpu unlimited",
////                    photoURL:"D:\\Users\\Pictures\\Encyclopedia\\aclogo3.jpg"}),
//                }),
           //     data:{ownerId:2,page:1,pageNum:10},
                success: function(){
                alert("success");
            }}
    );
</script>
</html>
