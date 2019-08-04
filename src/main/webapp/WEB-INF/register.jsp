
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h1>Registration</h1>
    
    <c:if test="${userError != null}">
        <p class="error"> ${userError}</p> 
    </c:if>

    <form:form action="/users" method="POST" modelAttribute="user">
    
        <p><form:label path="first"> First Name: </form:label></p>
        <p><form:errors path="first"></form:errors> </p>
        <p><form:input path="first"></form:input></p>

        <p><form:label path="last"> Last Name: </form:label></p>
        <p><form:errors path="last"></form:errors> </p>
        <p><form:input path="last"></form:input></p>

        <p><form:label path="email"> Email: </form:label></p>
        <p><form:errors path="email"></form:errors> </p>
        <p><form:input path="email"></form:input></p>

        <p><form:label path="password"> Password: </form:label></p>
        <p><form:errors path="password"></form:errors></p> 
        <p><form:input path="password" type="password" ></form:input></p>

        <p><form:label path="confirm"> Password Confirmation: </form:label></p>
        <p><form:errors path="confirm"></form:errors> </p>
        <p><form:input path="confirm" type="password"></form:input></p>
        
        <p><input type="submit" value="Register" /></p>
        
    </form:form>
	
	<h1> Login </h1>
	
    <form:form action="/users/login" method="POST">
        <c:if test="${loginError != null}">
        	<p class="error"> ${loginError}</p> 
   	 	</c:if>
    	<p>Email:</p>
        <p><input type="text" name="email"  /></p>
        
        <p>Password:</p>
        <p><input type="password" name="password" /></p>
        
        <p><input type="submit" value="Login" /></p>
        
    </form:form>
    

    
</body>
</html>