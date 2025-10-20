<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Dashboard</title>
</head>
<body>
    <h1>Xin chào, Thành viên!</h1>
    <p>Đây là trang hiển thị các công việc được giao cho bạn.</p>

    <ul>
        <li><a href="../task">Công việc của tôi</a></li>
        <li><a href="../groupwork">Dự án đang tham gia</a></li>
    </ul>

    <form action="../logout" method="get">
        <button type="submit">Đăng xuất</button>
    </form>
</body>
</html>