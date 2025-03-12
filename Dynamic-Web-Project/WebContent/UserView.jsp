<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="UserCtl" method="post">

		<%
			String msg = (String) request.getAttribute("msg");
		%>

		<%
			if (msg != null) {
		%>
		<h1><%=msg%></h1>
		<%
			}
		%>


		<h1 align="center">Add User</h1>
		<table align="center">
			<tr>
				<th>First Name</th>
				<td><input type="text" name="firstName"
					placeholder="Enter Your First Name" value=""></td>
			</tr>

			<tr>
				<th>Last Name</th>
				<td><input type="text" name="lastName"
					placeholder="Enter Your Last Name" value=""></td>
			</tr>

			<tr>
				<th>LoginId</th>
				<td><input type="email" name="loginId"
					placeholder="Enter Your Email ID" value=""></td>
			</tr>

			<tr>
				<th>Password</th>
				<td><input type="password" name="password"
					placeholder="Enter Your Password" value=""></td>
			</tr>

			<tr>
				<th>Address</th>
				<td><input type="text" name="address"
					placeholder="Enter Your Address" value=""></td>
			</tr>

			<tr>
				<th>Gender</th>
				<td><input type="text" name="gender"
					placeholder="Enter Your Gender" value=""></td>
			</tr>

			<tr>
				<th>Dob</th>
				<td><input type="date" name="dob" value=""></td>
			</tr>

			<tr>
				<th></th>
				<td><input type="submit" name="operation" value="Save"></td>
			</tr>
		</table>

	</form>

</body>
</html>