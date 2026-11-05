<!DOCTYPE html>
<html>
<head>
    <title>Student Form</title>
</head>
<body>

    <h2>Student Details</h2>

    <form action="${pageContext.request.contextPath}/students"
          method="post">

        <input type="hidden"
               name="id"
               value="${student.id}">

        <input type="hidden"
               name="action"
               value="${student == null ? 'create' : 'update'}">

        Name:
        <input type="text"
               name="name"
               value="${student.name}"
               required>

        <br><br>

        Email:
        <input type="email"
               name="email"
               value="${student.email}"
               required>

        <br><br>

        Course:
        <input type="text"
               name="course"
               value="${student.course}"
               required>

        <br><br>

        <button type="submit">Save</button>

    </form>

</body>
</html>
