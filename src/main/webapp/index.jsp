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
                                name:"Intel",price:299.9,categoryId:4,detail:"Powerful cpu",
                                photoURL:"C:\\Users\\unname\\Pictures\\Encyclopedia\\aclogo.jpg"}),

           /*     data : JSON.stringify({id:3, name:"new name", price:6399, categoryId:300, detail:"A new computer", photoURL:"urlstring",
                                    productPhotoId:1}),*/

            //    data: JSON.stringify({id:1},{name:"pname"},{}),
            //    data:{},

                success: function(){

                alert("success");
            }}
    );
</script>
</html>
