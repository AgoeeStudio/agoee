<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Agoee</title>
</head>

<body>

<h1>Agoee</h1>

<div id="content">
    <h2>Register</h2>
    <c:if test="${not empty error}">
        <h1>Woops!</h1>
        <p class="error">${error}</p>
    </c:if>
    <form:form modelAttribute="user" cssClass="form-horizontal" action="${pageContext.request.contextPath}/user/register" method="post">
        <div class="control-group">
            <form:label for="username" path="username" cssClass="control-label">用户名：</form:label>
            <div class="controls">
                <form:input path="username"/>
                <form:errors path="username" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="password" path="password" cssClass="control-label">登录密码：</form:label>
            <div class="controls">
                <form:password path="password"/>
                <form:errors path="password" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="email" path="email" cssClass="control-label">邮箱：</form:label>
            <div class="controls">
                <form:input path="email"/>
                <form:errors path="email" cssClass="help-inline"/>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" value="申请注册" class="btn btn-primary"/>
            <input type="reset" value="重置" class="btn"/>
        </div>
    </form:form>
</div>

<div id="footer">
    Sample application for <a
        href="http://github.com/SpringSource/spring-security-oauth"
        target="_blank">Spring Security OAuth</a>
</div>

</body>
</html>
