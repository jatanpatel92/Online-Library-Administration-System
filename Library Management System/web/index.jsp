<%-- 
    Document   : index
    Created on : Oct 30, 2014, 2:13:39 PM
    Author     : Jatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Management System</title>
        <link rel="stylesheet" 	type="text/css" href="style.css">
        <script src="jquery-1.7.1.min.js" type="text/javascript"></script>
        <script src="script.js" type="text/javascript"></script>
    </head>
    <body>
        <center>
        <h1>Welcome to the Library</h1>
        <!--input type="text" name="search" value="" size="100" /-->
        <div id="menu">
            <ul style="list-style: none;">
                <div class="button" id="search">
                    <a href="explore.jsp" target="frame3">Explore</a>
                </div>
                <br>
                <br>
                <div class="button" id="chkout">
                    <a href="chkout.jsp" target="frame3">Check Out</a>
                </div>
                <br>
                <br>
                <div class="button" id="chkin">
                    <a href="chkin.jsp" target="frame3">Check In</a>
                </div>
                <br>
                <br>
                <div class="button" id="borrow">
                    <a href="borrower.jsp" target="frame3">Borrower Management</a>
                </div>
                <br>
                <br>
                <div class="button" id="fines">
                    <a href="fines.jsp" target="frame3">Fines</a>
                </div>
                <!--input type="submit" name="upfines" class="button" value="Update Fines" id="upfines">
                <form id="form3" method="get" action="fines">
                    <input type="submit" name="upfines" class="button" value="Update Fines" id="upfines">
                </form-->
            </ul>
	</div>
        <!--input type="submit" value="Explore" name="Search" id="search" class="button"/>
        <input type="submit" value="Check Out" name="chkout" id="chkout" class="button"/>
        <input type="submit" value="Check In" name="chkin" id="chkin" class="button"/>
        <input type="submit" value="Borrower Management" name="borrow" id="borrow" class="button"/-->
        </center>
    </body>
</html>
