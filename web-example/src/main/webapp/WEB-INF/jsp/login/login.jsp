<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>learning-example</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body id=userlogin_body>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<table>
			<tr>
				<td>用户名:</td>
				<td><input id="username" name="username" /></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td><input id="password" type="password" name="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>