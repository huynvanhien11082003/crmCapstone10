<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa Quyền</title>
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container" style="margin-top: 50px;">
    <h3>Chỉnh sửa Quyền</h3>
    <hr>
    
    <!-- Form chỉnh sửa role -->
    <form action="role-edit" method="post">
        <!-- Ẩn input id để servlet biết update role nào -->
        <input type="hidden" name="id" value="${role.id}" />
        
        <div class="form-group">
            <label for="rolename">Tên Quyền</label>
            <input type="text" class="form-control" id="rolename" name="rolename" 
                   value="${role.name}" required>
        </div>
        
        <div class="form-group">
            <label for="roledescription">Mô tả</label>
            <textarea class="form-control" id="roledescription" name="roledescription" rows="3" required>${role.description}</textarea>
        </div>
        
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="role-table" class="btn btn-secondary">Hủy</a>
    </form>
</div>

<script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
<script src="bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>
