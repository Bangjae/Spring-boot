<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	out.println("Model(Sub) : Hello World");
%>
<br>

${ObjectTest}

<br>

${lists}

<br>
<br>

<C:forEach var="mylist" items="${lists}">
	${mylist } <br>
</C:forEach>

<br>
<br>
당신의 이름은 ${name} 입니다.

</body>
</html>