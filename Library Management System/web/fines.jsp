<%-- 
    Document   : fines
    Created on : Nov 2, 2014, 2:50:27 PM
    Author     : Jatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fines</title>
        <link rel="stylesheet" 	type="text/css" href="style.css">
        <script src="jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="script.js" type="text/javascript"></script>
    </head>
    <body>
        <center>
        <h1>Fines</h1>
        <div class="button" id="fines">
            <a href="addPayment.jsp" target="frame3">Add Payment</a>
        </div>
        <br>
        <br>
        <form id="form2" method="get" action="fines">
            <input type="submit" name="upfines" class="button" value="Refresh Fines" id="upfines">
            
        </form>
        </center>
    </body>
</html>
