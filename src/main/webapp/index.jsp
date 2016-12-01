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
                url: "shop/count",
                //type:"POST",
                contentType:"application/json",
                dataType:"json",
//                data: JSON.stringify({ ownerId:2,
//                    name:"Maxwell",
//                    contact:"contact here3",
//                    email:"123@123.com3",
//                    telephone:"213143",
//                    status:0,
//                    idPhotoUrl:"C:\\Users\\unname\\Pictures\\Encyclopedia\\aclogo3.jpg"}),
//                data:{page:1,count:2},
                success: function(){

                alert("success");
            }}
    );
</script>
</html>
