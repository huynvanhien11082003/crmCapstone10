<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Sửa thông tin người dùng</title>
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
	<h3>Cập nhật thông tin người dùng</h3>

	<form action="user-edit" method="post">
		<input type="hidden" name="id" value="${users.id}" />

		<div class="form-group">
			<label>First Name:</label> <input type="text" name="firstname"
				class="form-control" value="${users.firstname}" required>
		</div>

		<div class="form-group">
			<label>Last Name:</label> <input type="text" name="lastname"
				class="form-control" value="${users.lastname}" required>
		</div>

		<div class="form-group">
			<label>Username:</label> <input type="text" name="username"
				class="form-control" value="${users.username}" required>
		</div>

		<div class="form-group">
			<label>Password:</label> <input type="password" name="password"
				class="form-control" value="${users.password}" required>
		</div>

		<div class="form-group">
			<label class="col-sm-12">Select Roles</label>
			<div class="col-sm-12">
				<select name="roles" class="form-control form-control-line">
					<option value="1">ADMIN</option>
					<option value="2">LEADER</option>
					<option value="3">MEMBER</option>
				</select>
			</div>
		</div>

		<button type="submit" class="btn btn-success">Lưu thay đổi</button>
		<a href="user-table" class="btn btn-secondary">Quay lại</a>
	</form>
</body>
</html>