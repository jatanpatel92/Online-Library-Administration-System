<%-- 
    Document   : addPayment
    Created on : Nov 2, 2014, 3:36:56 PM
    Author     : Jatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Payment</title>
        <link rel="stylesheet" 	type="text/css" href="style.css">
        <script src="jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="script.js" type="text/javascript"></script>
    </head>
    <body>
        <center>
        <h1>Search Fine by Card No</h1>
        <form id="form2" method="get" action="addPayment">
                <div class="form-row">
			<span class="label"> Card No : </span> <input type="text" name="cno">
		</div>
                <!--br>
                <br>
		<div class="form-row">
			<span class="label"> Amount : </span> <input type="text" name="amount">
		</div-->
                <br>
                <br>
		<div class="form-row">
			<input class="submit" type="submit" value="Add Payment">
		</div>
        </form>
        </center>

    </body>
</html>
