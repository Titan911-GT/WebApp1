<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <title>Add Todo Page</title>
</head>

<body>
    <div class="container">
       <h1>Enter Todo Details</h1>

        <form:form method="post" modelAttribute="todo">
            <div class="mb-3">
                <label>Description:</label>
                <form:input path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="form-control"/>
            </div>

            <form:input type="hidden" path="id"/>
            <form:input type="hidden" path="done"/>

            <button type="submit" class="btn btn-success">Add Todo</button>
        </form:form> <!-- ✅ fixed closing tag -->
    </div>

    <!-- Scripts -->
    <script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>
