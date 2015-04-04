<%-- 
    Document   : borrower
    Created on : Nov 2, 2014, 12:09:12 PM
    Author     : Jatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrower Management</title>
        <link rel="stylesheet" 	type="text/css" href="style.css">
        <script src="jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="script.js" type="text/javascript"></script>
    </head>
    <body>        <center>
        <h1>Add Borrower</h1>
        <form id="form2" method="get" action="borrower">
		<div class="form-row">
			<span class="label"> First Name : </span> <input type="text" name="brofname" id="brofname2">
		</div>
                <br>
                <br>
		<div class="form-row">
                    <span class="label"> Last Name : </span> <input type="text" name="brolname" id="brolname2">
		</div>
                <br>
                <br>
		<div class="form-row">
                    <span class="label"> Address : </span> <input type="text" name="broaddress" id="broaddress2">
		</div>
                <br>
                <br>
                <div class="form-row">
                    <span class="label"> Phone No (Optional) : </span> <input type="text" name="brono" id="brono">
		</div>
                <br>
                <br>
		<div class="form-row">
                    <input class="submit" type="submit" value="ADD" id="bro">
		</div>
	</form>        </center>
    </body>
</html>
