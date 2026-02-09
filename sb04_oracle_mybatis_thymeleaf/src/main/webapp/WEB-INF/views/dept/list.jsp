<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>2026. 2. 2. 오전 10:27:57</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">CHaCha's HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code">
    dept.jsp
  </xmp>
  <h1>Dept</h1>
  <table border="1" style="width:100%">
  	<tr>
  		<th>deptno</th>
  		<th>dname</th>
  		<th>loc</th>
  	</tr>
  	<c:forEach items="${list }" var="vo">
	  	<tr>
	  		<td>${vo.deptno }</td>
	  		<td>${vo.dname }</td>
	  		<td>${vo.loc }</td>
	  	</tr>
  	</c:forEach>
  </table>
  <br>
</div>
<script>
</script>  
</body>
</html>