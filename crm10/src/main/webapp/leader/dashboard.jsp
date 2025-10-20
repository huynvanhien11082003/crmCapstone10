<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Leader Dashboard</title>
</head>
<body>
    <h1>Xin chào, Trưởng nhóm!</h1>
    <p>Đây là khu vực dành cho Leader quản lý công việc nhóm.</p>

    <ul>
        <li><a href="../groupwork">Dự án của tôi</a></li>
        <li><a href="../task">Công việc của nhóm</a></li>
        <li><a href="../user-table">Trang của thành viên</a></li>
    </ul>

    <form action="../logout" method="get">
        <button type="submit">Đăng xuất</button>
    </form>
</body>
</html>