<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
    <h1>Welcome, ${ user.first }</h1>
    
    <h1>Your Current Subscription: ${ user.subscription.name}</h1>
    <h1>Subscription Cost: ${ user.subscription.cost}</h1>
    <h1>Your next bill: ${ user.dueDate}</h1>

    <h1>Swtich Subscription:</h1>
    <form action="/subscriptions/change" method="POST">

        <select name="subscription">
            <c:forEach items= "${subscriptions}" var="subscription">
                <option value="${subscription.id}" > ${subscription.name}</option>
            </c:forEach>
         </select>
         <input type="submit" value="Switch Subscription">
    </form>
</body>
</html>
</html>