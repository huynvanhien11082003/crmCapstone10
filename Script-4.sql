CREATE DATABASE crm10;
USE crm10;


CREATE TABLE roles(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	description VARCHAR(255),
	
	PRIMARY KEY(id)
);

CREATE TABLE users(
	id INT NOT NULL AUTO_INCREMENT,
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	username VARCHAR(255),
	password VARCHAR(255),
	roleid INT NOT NULL,
	
	PRIMARY KEY(id)
);

CREATE TABLE projects(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	startdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	enddate TIMESTAMP,
	createbyusersid INT NOT NULL,
	
	PRIMARY KEY(id)
);

CREATE TABLE projectsmember(
	id INT NOT NULL AUTO_INCREMENT,
	joindate TIMESTAMP,
	userid INT NOT NULL,
	projectsid INT NOT NULL,
	status varchar(100),
	percent INT,
	
	PRIMARY KEY(id)
);
CREATE TABLE tasks(
	id INT NOT NULL AUTO_INCREMENT,
	nametasks VARCHAR(255),
	projectsid INT,
	startdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	enddate TIMESTAMP,
	status  VARCHAR(100),
	
	
	PRIMARY KEY(id)
);

CREATE TABLE taskprogress(
	id INT NOT NULL AUTO_INCREMENT,
	usersid INT NOT NULL,
	taskid INT NOT NULL,
	status VARCHAR(255),
	progresspercent INT DEFAULT 0,
	startdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	enddate TIMESTAMP,
	
	PRIMARY KEY(id)
	
);

 
ALTER TABLE users ADD CONSTRAINT FK_users_roles FOREIGN KEY(roleid) REFERENCES roles(id);

ALTER TABLE projectsmember ADD CONSTRAINT FK_projectsmember_users FOREIGN KEY(userid) REFERENCES users(id),
							ADD CONSTRAINT FK_projectsmember_projects FOREIGN KEY(projectsid) REFERENCES projects(id);

ALTER TABLE tasks ADD CONSTRAINT FK_tasks_projects FOREIGN KEY(projectsid) REFERENCES projects(id);

ALTER TABLE taskprogress ADD CONSTRAINT FK_taskprogress_users FOREIGN KEY(usersid) REFERENCES users(id);
ALTER TABLE taskprogress ADD CONSTRAINT FK_taskprogress_task FOREIGN KEY(taskid) REFERENCES tasks(id);


INSERT INTO roles (name,description) VALUES
('ROLE_ADMIN','Quản Trị Hệ Thống'),('ROLE_LEADER','Quản lý dự án'),('ROLE_MEMBER','Nhân Viên');


INSERT INTO users (firstname, lastname, username, password, roleid) VALUES
('Nguyễn', 'Văn A', 'admin', '123456', 1),     
('Trần', 'Thị B', 'leader', '123456', 2),       
('Lê', 'Văn C', 'member1', '123456', 3),       
('Phạm', 'Thị D', 'member2', '123456', 3);

INSERT INTO projects (name, startdate, enddate) VALUES
('Dự án CRM', '2025-05-01', '2025-08-01'),     
('Dự án Website Bán Hàng', '2025-06-01', '2025-09-01';

INSERT INTO projectsmember (joindate, userid, projectsid, status, percent) VALUES
('2025-05-01', 3, 1, 'Đang tham gia', 100),   
('2025-05-02', 4, 1, 'Đang tham gia', 80),   
('2025-06-01', 3, 2, 'Đang tham gia', 50),   
('2025-06-01', 2, 1, 'Quản lý dự án', 100);


INSERT INTO tasks (nametasks, projectsid, startdate, enddate, status) VALUES
('Phân tích yêu cầu khách hàng', 1, '2025-05-05', '2025-05-10', 'Hoàn thành'),
('Thiết kế cơ sở dữ liệu', 1, '2025-05-11', '2025-05-20', 'Đang thực hiện'),
('Tạo giao diện đăng nhập', 2, '2025-06-05', '2025-06-15', 'Chưa bắt đầu'),
('Kiểm thử module khách hàng', 1, '2025-05-21', '2025-05-30', 'Chưa bắt đầu');


INSERT INTO taskprogress (usersid, taskid, status, progresspercent, startdate, enddate) VALUES
(3, 1, 'Đã hoàn thành', 100, '2025-05-05', '2025-05-10'),
(4, 2, 'Đang thực hiện', 60, '2025-05-11', NULL),
(3, 3, 'Chưa bắt đầu', 0, '2025-06-05', NULL),
(4, 4, 'Chưa bắt đầu', 0, '2025-05-21', NULL),
(2, 1, 'Đã hoàn thành', 100, '2025-05-05', '2025-05-10'),
(2, 2, 'Đang thực hiện', 70, '2025-05-11', NULL);

SELECT * FROM projectsmember p 



SELECT * FROM  tasks t 
DROP TABLE tasks 

SELECT *
FROM users u 
WHERE u.id = 1;


DELETE FROM users 
WHERE id = 6;

INSERT INTO users (firstname, lastname, username, password, roleid) VALUES (?,?,?,?,?); 

UPDATE users
SET firstname = 'update1', lastname ='updatelast', username =  'update@gmail,com',  password = 'updatepasss', roleid ='2'
where  id = 6

SELECT * FROM taskprogress

SELECT u.firstname, u.lastname ,  t.status, t.progresspercent, t2.nametasks , t2.startdate ,t2.enddate
FROM taskprogress t JOIN users u on t.usersid = u.id
					JOIN tasks t2 on t.taskid = t2.id 
WHERE t.usersid = 2


SELECT * FROM roles r

SELECT * FROM roles r WHERE r.id = 1

UPDATE roles r 
SET name = ?, description  = ?
WHERE r.id  = ?

DELETE FROM roles WHERE id = 4

SELECT * FROM projects p 

UPDATE projects p
SET name = ?, startdate = ?, enddate = ?
WHERE p.id = ?

SELECT  * from projects p WHERE p.id =3

DELETE FROM projects WHERE id = 3

SELECT 
    p.id AS project_id,
    p.name AS project_name,
    u.firstname,
    u.lastname,
    tm.status AS member_status,
    t2.nametasks AS task_name,
    tp.status AS task_status,
    tp.progresspercent,
    tp.startdate,
    tp.enddate
FROM projectsmember tm
JOIN users u ON tm.userid = u.id
JOIN projects p ON tm.projectsid = p.id
JOIN taskprogress tp ON tp.usersid = u.id
JOIN tasks t2 ON tp.taskid = t2.id AND t2.projectsid = p.id
WHERE p.id =1
ORDER BY p.id, u.id;



SELECT t.id , t2.nametasks, p.name , u.firstname , u.lastname  , t.startdate ,t.enddate,t.status 
FROM taskprogress t JOIN tasks t2 on t.taskid = t2.id 
					JOIN projects p on  t2.projectsid = p.id 
					JOIN users u on t.usersid = u.id 
					
SELECT * FROM taskprogress t 

INSERT INTO taskprogress (usersid, taskid, status, progresspercent, startdate, enddate) VALUES
(2, 2, 'Test', 100, '2025-05-05', '2025-05-10');





SELECT t.status ,p2.percent, u.firstname ,u.lastname ,t.nametasks 
FROM tasks t JOIN projects p on t.projectsid = p.id 
			JOIN projectsmember p2 on p.id  = p2.projectsid  
			JOIN users u on p2.userid = u.id
WHERE p.id = 1  


SELECT p.status 
FROM projectsmember p   

SELECT *FROM tasks t 

SELECT * FROM roles r 

SELECT *  FROM users u 
SELECT u.id,u.username, r.id , r.name
FROM users u JOIN roles r on u.roleid = r.id
WHERE u.username = 'admin@gmail.com' AND u.password ='123456'

SELECT *
FROM  taskprogress t 
WHERE t.usersid =2;

SELECT u.username, r.name AS role_name
FROM users u 
JOIN roles r ON u.roleid = r.id;


SELECT t.id, t.nametasks, p.name,t.startdate , t.enddate,t.status 
FROM tasks t JOIN projects p on t.projectsid  = p.id
			JOIN projectsmember p2 on p.id =p2.projectsid 
WHERE p2.userid = 2
