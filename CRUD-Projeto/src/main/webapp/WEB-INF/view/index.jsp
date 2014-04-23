<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>List of teams</h1>
	<p>Here you can see the list of the teams, edit them, remove or
		update.</p>

	

	<c:foreach var="projeto" items="${projeto}">
	</c:foreach>
	<table border="1px" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="10%">id</th>
				<th width="15%">name</th>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${projeto.id}</td>
				<td>${projeto.nome}</td>
			</tr>

		</tbody>
	</table>



</body>
</html>