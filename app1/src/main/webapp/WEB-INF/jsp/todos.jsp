


    <%@ include file="common/todo_header.jspf" %>
    <%@ include file="common/navigation.jspf" %>


    <div class="container">
       <h1>Enter Todo Details</h1>

        <form:form method="post" modelAttribute="todo">
            <fieldset class="mb-3">
                <label>Description:</label>
                <form:input type="text" path="description" required="required"/>
                <form:errors path="description" cssClass="text-warning"/>
            </fieldset>

            <fieldset class="mb-3">
                <label>Target Date:</label>
                <form:input type="text" path="targetDate" required="required"/>
                <form:errors path="description" cssClass="text-warning"/>
            </fieldset>

            
            <form:input type="hidden" path="id"/>
            <form:input type="hidden" path="done"/>

            <button type="submit" class="btn btn-success">Submit</button>
        </form:form> <!-- ✅ fixed closing tag -->
    </div>

    <!-- Scripts -->
    <script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>
