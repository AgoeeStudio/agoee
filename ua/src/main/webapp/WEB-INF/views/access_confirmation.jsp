<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
  <title>Agoee</title>
</head>

<body>

  <h1>Agoee</h1>

  <div id="content">

    <% if (session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null && !(session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) instanceof UnapprovedClientAuthenticationException)) { %>
      <div class="error">
        <h2>Woops!</h2>

        <p>Access could not be granted. (<%= ((AuthenticationException) session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>)</p>
      </div>
    <% } %>
    <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>

    <authz:authorize ifAllGranted="ROLE_USER">
      <h2>Please Confirm</h2>

      <p>You hereby authorize "<c:out value="${client.clientId}"/>" to access your protected resources.</p>

      <form id="confirmationForm" name="confirmationForm" action="<%=request.getContextPath()%>/oauth/authorize" method="post">
        <input name="user_oauth_approval" value="true" type="hidden"/>
        <ul>
        <c:forEach items="${scopes}" var="scope"><c:set var="approved"><c:if test="${scope.value}"> checked</c:if></c:set><c:set var="denied"><c:if test="${!scope.value}"> checked</c:if></c:set>
        <li>${scope.key}: <input type="radio" name="${scope.key}" value="true"${approved}>Approve</input><input type="radio" name="${scope.key}" value="false"${denied}>Deny</input></li> 
        </c:forEach>
        </ul>
        <label><input name="authorize" value="Submit" type="submit"/></label>
      </form>
    </authz:authorize>
  </div>

  <div id="footer">Sample application for <a href="http://github.com/SpringSource/spring-security-oauth" target="_blank">Spring Security OAuth</a></div>


</body>
</html>
