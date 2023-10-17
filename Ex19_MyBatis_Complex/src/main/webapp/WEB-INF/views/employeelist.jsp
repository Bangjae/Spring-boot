<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("MyBatis 사용하기 : Hellow World");
%>
<br>

	<c:forEach items="${emp}" var="dto">
		${dto.ename1} / ${dto.deptno1} / ${dto.dname1}<br>
	</c:forEach>

</body>
</html>