
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
    <h1>Welcome to Admin Dashboard</h1>
    <h2>Costumers</h2>
    <table border="1">
        <tr>
            <th>User Name</th>
            <th>Due Date</th>
            <th>Cost</th>
            <th>Subscription Name</th>
        </tr>
        <c:forEach items="${users}" var= "i">
            <tr>
                <td> ${i.first} ${i.last} </td>
                <td> ${i.subscription.dueDate}</td>
                <td> ${ i.subscription.cost }</td>
                <td> ${ i.subscription.name }</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Packages</h2>
    <table border="1">

                <tr>
                    <th>Subscription Name</th>
                    <th>Cost</th>
                    <th>Available</th>
                    <th>Users</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${subscriptions}" var = "subscription">
                    <tr>
                        <td> ${subscription.name}  </td>
                        <td> ${subscription.cost}</td>
                        <td> ${ subscription.available }</td>
                        <td> ${ subscription.users.size() }</td>

                        <c:if test="${subscription.available}">
                            <td>
                                <form action="/subscriptions/${subscription.id}/toggle" method="POST">
                                    <input type="submit" value="Deactivate"/>
                                </form> 

                                <a href="/subscriptions/${subscription.id}/edit">Edit</a>
                            </td>
                        </c:if>
                        <c:if test="${!subscription.available}">
                                <td>
                                    <form action="/subscriptions/${subscription.id}/toggle" method="POST">
                                        <input type="submit" value="Activate"/>
                                    </form>
                                    <a href="/subscriptions/${subscription.id}/edit">Edit</a> 


                                </td>
                        </c:if>

                    </tr>
                </c:forEach>
    </table>

    <form action="/subscriptions" method="POST">
        <input required name= "name" type="text" placeholder="Sub Name" />
        <input required name= "cost"  min="1" type="text" placeholder="Sub Cost" />
        <input type="submit" value="Create"/>
    </form>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
</body>
</html>