<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Sample Form</title>
  </head>
  <body>
<!--
In a scriptlet, you use the request object like so:

<% Map myMap = (Map) request.getAttribute("data"); %>

If you want to use something like JSTL instead of a scriptlet, you could do:

<c:forEach var="entry" items="${data}">
Name:  ${entry.key} <br/>
Value: ${entry.value} <br/>
</c:forEach>
-->
  </body>
</html>