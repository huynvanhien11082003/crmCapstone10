<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Xin chào, Quản trị viên!</h1>
    <p>Chào mừng bạn đến trang quản lý hệ thống.</p>

    <ul>
        <li><a href="../user-table">Quản lý người dùng</a></li>
        <li><a href="../groupwork">Quản lý dự án</a></li>
        <li><a href="../task">Tất cả công việc</a></li>
    </ul>

    <form action="../logout" method="get">
        <button type="submit">Đăng xuất</button>
    </form>
</body>
</html>