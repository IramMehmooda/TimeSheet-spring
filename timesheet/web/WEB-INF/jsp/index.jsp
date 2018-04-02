<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 <%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Management System</title>
</head>
<body>
<h1><b>Time Management System</b></h1>

<ul>
<li><a href='managers'>List Managers</a></li>
<li><a href='employees'>List Employees</a></li>
<li><a href='tasks'>List Tasks</a></li>
<li><a href='timesheets'>List Timesheets</a></li>
</ul>
<h2><a href='timesheet-service'>Check Timesheet</a></h2>

Date: <fmt:formatDate pattern='dd=MM-yyy' value="${today}" />

</body>
</html>