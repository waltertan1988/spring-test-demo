<html>
<head>
	<title>登录</title>
</head>

<body>
	<form action="${base}/j_spring_security_check" method="post">
		账号：<input type="text" name="username"/><br/>
		密码：<input type="password" name="password"/><br/>
		<input type="submit" value="登录">
	</form>
	<font color="red">${error!""}</font>
</body>
</html>