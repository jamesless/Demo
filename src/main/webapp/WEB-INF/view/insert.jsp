<%@ page pageEncoding="UTF-8"%>
<!-- 引入防篡改标签 -->
<%@ taglib prefix="sfpay" uri="http://www.sf-pay.com/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
this is insert jsp.....

	<form method="post" action="<%=request.getContextPath()%>/order/test">
		<!-- sfpay-formToken用于产生一个防重复提交的令牌 -->
		<sfpay:formToken id="html_token" />
		<!-- sfpay-formParam标签之前必须存在一个sfpay-formToken标签，
		sfpay-formParam标签的防篡改信息和离其最近的上一个sfpay-formToken标签的令牌信息组成了完整的防篡改信息，
		被framework2框架保存到会话中。 -->
		<sfpay:formParam id="html_first" name="first" value="first" />
		<input id="html_name" name="name" value="" type="text" /> 
		<input id="html_submit" type="submit" value="submit" />
	</form>

</body>
</html>