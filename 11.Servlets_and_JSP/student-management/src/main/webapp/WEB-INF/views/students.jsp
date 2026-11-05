<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
</head>
<body>

    <h2>Student Management System</h2>

    <a href="${pageContext.request.contextPath}/students?action=new">
        Add Student
    </a>

    <table border="1" cellpadding="10">

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="student" items="${students}">

            <tr>
                <td>${student.id}</td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.email}"/></td>
                <td><c:out value="${student.course}"/></td>

                <td>

                    <a href="${pageContext.request.contextPath}/students?action=edit&id=${student.id}">
                        Edit
                    </a>

                    <form action="${pageContext.request.contextPath}/students"
                          method="post">

                        <input type="hidden"
                               name="action"
                               value="delete">

                        <input type="hidden"
                               name="id"
                               value="${student.id}">

                        <button type="submit">Delete</button>

                    </form>

                </td>
            </tr>

        </c:forEach>

    </table>

</body>
</html>
