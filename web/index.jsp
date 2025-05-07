<%-- 
    Document   : index
    Created on : May 6, 2025, 9:28:11 PM
    Author     : n_fol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All People</title>
    </head>
    <body>
        <h1>All The PEEPS!</h1>
        <a href="API/employees">All Employees</a>
        <a href="API/hourly">Hourly Employees</a>
        <a href="API/salaried">Salary Employees</a>
        <table border="1">
            <tr>
                <th>Employee ID</th>
                <th>Full Name</th>
                <th>Hire Date</th>
                <th>Employee Type</th>
                <th>Yearly Cost</th>
            </tr>
            <c:forEach var="person" items="${persons}">
                <tr>
                    <td>
                        <!-- Creates a link to each employee's info using my API controller -->
                        <a href="API/employees/${person.value.employeeID}">${person.value.employeeID}</a>
                    </td>
                    <td>${person.value.firstName} ${person.value.middleName}  ${person.value.lastName}</td>
                    <td>${person.value.hireDate}</td>
                    <!-- Instead of using c:choose and using getClass(), I chose to create methods in each class that returns 
                       the appropriate type based on which class it was called from. This is a cleaner approach in my eyes.-->
                    <td>${person.value.getEmployeeType()}</td>
                    <!-- Wasn't sure how to do this so I found something similar on StackOverflow.com 
                    Exact code referenced: 
                    fmt:formatNumber type="currency" value="${camp.montoTotal}"/ 
                    URL: https://stackoverflow.com/questions/35725747/fmt-formatnumber-type-currency-shows-%c2%a4-instead-of-actual-currency-symbol -->
                    <!-- If compensation is 0 then supply "None" to the column, otherwise calculate compensation. -->
                    <td><c:choose>
                            <c:when test="${person.value.calcYearlyCompensation() == 0}">
                                None
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${person.value.calcYearlyCompensation()}" type="currency" />
                            </c:otherwise>
                        </c:choose></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
