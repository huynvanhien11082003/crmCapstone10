<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa Dự án</title>
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	background-color: #f4f6f8;
}

.container {
	max-width: 600px;
	margin-top: 60px;
	background: white;
	padding: 30px;
	border-radius: 15px;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	margin-bottom: 25px;
	text-align: center;
	color: #007bff;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Chỉnh sửa Dự án</h2>
		<form action="groupwork-edit" method="post">
			<div class="mb-3">
				<label for="id" class="form-label">Mã dự án</label> <input
					type="text" id="id" name="projectIds" class="form-control"
					value="${project.id}" readonly>
			</div>

			<div class="mb-3">
				<label for="name" class="form-label">Tên dự án</label> <input
					type="text" id="name" name="name" class="form-control"
					value="${project.name}" required>
			</div>

			<div class="mb-3">
				<label for="startdate" class="form-label">Ngày bắt đầu</label> <input
					type="datetime-local" id="startdate" name="startdate"
					class="form-control"
					value="${project.startdate != null ? project.startdate.toString().replace(' ', 'T') : ''}"
					required>
			</div>

			<div class="mb-3">
				<label for="enddate" class="form-label">Ngày kết thúc</label> <input
					type="datetime-local" id="enddate" name="enddate"
					class="form-control"
					value="${project.enddate != null ? project.enddate.toString().replace(' ', 'T') : ''}"
					required>
			</div>

			<div class="d-flex justify-content-between">
				<a href="groupwork" class="btn btn-secondary">Quay lại</a>
				<button type="submit" class="btn btn-primary">Lưu thay đổi</button>
			</div>
		</form>
	</div>

	<script src="bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
