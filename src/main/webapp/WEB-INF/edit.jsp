<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Subscription</title>
</head>
<body>
    <h1>Subscription :  ${subscription.name}</h1>
    
    <form action="/subscriptions/${subscription.id}/update" method="POST">
        <input name="cost" type="text" value="${subscription.cost}">
        <input  type="submit" value="Update Subscription">
    </form>
    <form action="/subscriptions/${subscription.id}/destroy" method="POST">
        
        <input  type="submit" value="Delete">
    </form>
</body>
</html>