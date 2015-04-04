<%-- 
    Document   : explore
    Created on : Oct 31, 2014, 2:48:19 PM
    Author     : Jatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Explore</title>
        <link rel="stylesheet" 	type="text/css" href="style.css">
        <script src="jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="script.js" type="text/javascript"></script>
    </head>
    <body>
        <center>
        <h1>Search books by ISBN and/or Title and/or Author</h1>
        <form id="form2" method="get" action="searchBook">
		<div class="form-row">
			<span class="label"> Book ID/ISBN </span> <input type="text" name="isbn" id="isbn1">
		</div>
                <br>
                <br>
		<div class="form-row">
                    <span class="label"> Book Title: </span> <input type="text" name="btitle" id="btitle1">
		</div>
                <br>
                <br>
		<div class="form-row">
                    <span class="label"> Author: </span> <input type="text" name="aname" id="aname1">
		</div>
                <br>
                <br>
		<div class="form-row">
                    <input class="submit" type="submit" value="Explore" id="explore">
		</div>
	</form>
        </center>
    </body>
</html>
