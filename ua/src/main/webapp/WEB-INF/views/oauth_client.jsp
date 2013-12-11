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
    <h2>OAuth Client Register</h2>
    <c:if test="${not empty error}">
        <h1>Woops!</h1>
        <p class="error">${error}</p>
    </c:if>
    <form:form modelAttribute="oauthclient" cssClass="form-horizontal" action="${pageContext.request.contextPath}/oauth_client/register" method="post">
        <div class="control-group">
            <form:label for="client_id" path="client_id" cssClass="control-label">客户端标识：</form:label>
            <div class="controls">
                <form:input path="client_id" value="ua" />
                <form:errors path="client_id" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="client_secret" path="client_secret" cssClass="control-label">客户端密码：</form:label>
            <div class="controls">
                <form:password path="client_secret" value="secret" />
                <form:errors path="client_secret" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="resource_ids" path="resource_ids" cssClass="control-label">资源ID：</form:label>
            <div class="controls">
                <form:input path="resource_ids" value="ua" />
                <form:errors path="resource_ids" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="scope" path="scope" cssClass="control-label">操作权限：</form:label>
            <div class="controls">
                <form:input path="scope" value="read,write" />
                <form:errors path="scope" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="authorized_grant_types" path="authorized_grant_types" cssClass="control-label">授权标识：</form:label>
            <div class="controls">
                <form:input path="authorized_grant_types" value="authorization_code" />
                <form:errors path="authorized_grant_types" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="web_server_redirect_uri" path="web_server_redirect_uri" cssClass="control-label">回调地址：</form:label>
            <div class="controls">
                <form:input path="web_server_redirect_uri" value="http://localhost:8080/tonr2/ua/apis" />
                <form:errors path="web_server_redirect_uri" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label for="authorities" path="authorities" cssClass="control-label">角色：</form:label>
            <div class="controls">
                <form:input path="authorities" value="ROLE_USER" />
                <form:errors path="authorities" cssClass="help-inline"/>
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
