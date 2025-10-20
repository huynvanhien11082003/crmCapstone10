<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Chỉnh sửa công việc</title>
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link
	href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
<link rel="stylesheet" href="./css/custom.css">
</head>
<body>
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>

	<div id="wrapper">
		<!-- Navbar -->
		<nav class="navbar navbar-default navbar-static-top m-b-0">
			<div class="navbar-header">
				<a class="logo" href="index.html"> <b><img
						src="plugins/images/pixeladmin-logo.png" alt="home" /></b> <span
					class="hidden-xs"><img
						src="plugins/images/pixeladmin-text.png" alt="home" /></span>
				</a>
			</div>
		</nav>

		<!-- Sidebar -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse slimscrollsidebar">
				<ul class="nav" id="side-menu">
					<li><a href="index.html" class="waves-effect"><i
							class="fa fa-clock-o fa-fw"></i><span class="hide-menu">Dashboard</span></a></li>
					<li><a href="user-table" class="waves-effect"><i
							class="fa fa-user fa-fw"></i><span class="hide-menu">Thành
								viên</span></a></li>
					<li><a href="role-table" class="waves-effect"><i
							class="fa fa-modx fa-fw"></i><span class="hide-menu">Quyền</span></a></li>
					<li><a href="groupwork.html" class="waves-effect"><i
							class="fa fa-table fa-fw"></i><span class="hide-menu">Dự
								án</span></a></li>
					<li><a href="task" class="waves-effect active"><i
							class="fa fa-tasks fa-fw"></i><span class="hide-menu">Công
								việc</span></a></li>
				</ul>
			</div>
		</div>

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row bg-title">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<h4 class="page-title">Chỉnh sửa công việc</h4>
					</div>
					<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12 text-right">
						<a href="task" class="btn btn-default btn-sm">Quay lại</a>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<div class="white-box">
							<form action="task-edit" method="post" class="form-horizontal">

								<!-- Hidden inputs -->
								<input type="hidden" name="id" value="${taskProgress.id}">
								<input type="hidden" name="taskid"
									value="${taskProgress.taskid.id}">

								<div class="form-group">
									<label class="col-md-3 control-label">Tên công việc</label>
									<div class="col-md-9">
										<input type="text" class="form-control" name="nameTasks"
											value="${taskProgress.taskid.nameTasks}" required>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Dự án</label>
									<div class="col-md-9">
										<select name="projectid" class="form-control" required>
											<c:forEach items="${projectList}" var="p">
												<option value="${p.id}"
													${taskProgress.taskid.projectsid.id == p.id ? 'selected' : ''}>
													${p.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Người thực hiện</label>
									<div class="col-md-9">
										<select name="userid" class="form-control" required>
											<c:forEach items="${userList}" var="u">
												<option value="${u.id}"
													${taskProgress.usersid.id == u.id ? 'selected' : ''}>
													${u.firstname} ${u.lastname}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Ngày bắt đầu</label>
									<div class="col-md-9">
										<input type="date" class="form-control" name="startDate"
											value="${taskProgress.startDate}" required>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Ngày kết thúc</label>
									<div class="col-md-9">
										<input type="date" class="form-control" name="endDate"
											value="${taskProgress.endDate}" required>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Trạng thái</label>
									<div class="col-md-9">
										<select name="status" class="form-control">
											<option value="Chưa bắt đầu"
												${taskProgress.status == 'Chưa bắt đầu' ? 'selected' : ''}>Chưa
												bắt đầu</option>
											<option value="Đang thực hiện"
												${taskProgress.status == 'Đang thực hiện' ? 'selected' : ''}>Đang
												thực hiện</option>
											<option value="Hoàn thành"
												${taskProgress.status == 'Hoàn thành' ? 'selected' : ''}>Hoàn
												thành</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Tiến độ (%)</label>
									<div class="col-md-9">
										<input type="number" class="form-control"
											name="progresspercent"
											value="${taskProgress.progressPercent}" min="0" max="100"
											required>
									</div>
								</div>

								<div class="form-group text-center">
									<button type="submit" class="btn btn-success">Cập nhật</button>
									<a href="task" class="btn btn-default">Hủy</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

			<footer class="footer text-center">2018 &copy; myclass.com</footer>
		</div>
	</div>

	<!-- Scripts -->
	<script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<script src="js/jquery.slimscroll.js"></script>
	<script src="js/waves.js"></script>
	<script src="js/custom.min.js"></script>
</body>
</html>
