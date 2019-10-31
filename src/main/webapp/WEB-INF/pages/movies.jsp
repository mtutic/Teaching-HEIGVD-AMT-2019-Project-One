<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movies JSP Page</title>
  </head>
  <body>
    <h1>Movies</h1>
    <table>
      <c:forEach items="${movies}" var="movie">
        <tr>
          <td>${movie.id}</td>
          <td>${movie.title}</td>
          <td>${movie.year}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>