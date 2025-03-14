<%@page import="net.etf.beans.AdvisorMessageBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="net.etf.beans.AdvisorBean"%>
<jsp:useBean id="advisorBean" class="net.etf.beans.AdvisorBean" scope="session"></jsp:useBean>
<%
String action = request.getParameter("action");
if ("login".equals(action)) {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	if (advisorBean.login(username, password) && advisorBean.isAdvisor(username)) {
		session.setAttribute("advisorBean", advisorBean);
		int ida = advisorBean.getUserIdByUsername(username);
		session.setAttribute("idAdvisor", ida);
		String pass = advisorBean.getAdvisor().getEmail();
		session.setAttribute("from", pass);
		session.setAttribute("idForMess", ida);
		AdvisorMessageBean advisorMessageBean = new AdvisorMessageBean();
		session.setAttribute("advisorMessageBean", advisorMessageBean);
		response.sendRedirect("message.jsp");
		
	} else {
		session.setAttribute("notification", "Pogresni podaci za prijavu.");
	}
} else if ("logout".equals(action)) {
	session.invalidate();
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pocetna strana</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link href="styles/style.css" type="text/css" rel="stylesheet">
</head>
<body  style="background-image: url('img/weights.jpg');" >
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">
						<h1 class="text-center">Fitnes centar</h1>
					</div>
					<div class="card-body">
						<form method="post" action="?action=login">
							<div class="form-group">
								<label for="username">Korisnicko ime:</label> <input type="text"
									class="form-control" id="username" name="username">
							</div>
							<div class="form-group">
								<label for="password">Lozinka:</label> <input type="password"
									class="form-control" id="password" name="password">
								<p class="p"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></p>
							</div>
							<button type="submit" class="btn btn-primary btn-block">Prijavi	se</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>