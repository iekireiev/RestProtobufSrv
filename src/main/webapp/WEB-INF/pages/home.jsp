<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home page</title>
</head>
<body>
<h1>Home page</h1>
<p>
${message}<br/>
<a href="${pageContext.request.contextPath}/user/register/TestLogin/TestPassword.html">register test user</a><br/>
<a href="${pageContext.request.contextPath}/user/check/TestLogin/TestPassword.html">check login/password</a><br/>
<a href="${pageContext.request.contextPath}/user/test.html">test protobuf</a><br/>
</p>
</body>
</html>