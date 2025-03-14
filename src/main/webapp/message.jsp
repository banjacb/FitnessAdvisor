<%@page import="net.etf.dto.Advisor"%>
<%@page import="net.etf.dto.AdvisorMessage"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="advisorBean" class="net.etf.beans.AdvisorBean" scope="session"></jsp:useBean>
<jsp:useBean id="advisorMessageBean" class="net.etf.beans.AdvisorMessageBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Poruke</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="styles/stylemess.css" type="text/css" rel="stylesheet">
</head>
<body >
	<%
	SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
	Integer idForMess= advisorBean.getAdvisor().getId();
	if (advisorBean == null || !advisorBean.isLoggedIn()) {
		response.sendRedirect("login.jsp");
	}
	String action = request.getParameter("action");
	session.setAttribute("notification", "");
	boolean collapse = false;
	int id = 0;

	if ("search".equals(action)) {
		session.setAttribute("notification", "");
		String str = request.getParameter("str");
		if (str != null) {
			advisorMessageBean.search(str);
		} else
		

			advisorMessageBean.findAll();
	}

	else if ("logout".equals(action)) {
		response.sendRedirect("login.jsp");
	
	}

	else {
		
		advisorMessageBean.findAll();

		if ("read".equals(action)) {

			try {
		id = Integer.parseInt(request.getParameter("id"));
		session.setAttribute("notification", "Poruka pregledana.");
		advisorMessageBean.readMessage(id);
		collapse = true;

			} catch (Exception e) {

			}
		} else if ("send".equals(action)) {

			session.setAttribute("forWho", request.getParameter("forWho"));
			response.sendRedirect("sendMessage.jsp");
			session.setAttribute("notification", "");
		}
	}
	%>
	<nav class="navbar ">
    <a class="navbar-brand" href="#">Fitnes centar</a>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="#">Poruke</a>
    </div>
    <div class="ml-auto">
        <a class="nav-item nav-link text-white" href="?action=logout">Odjava</a>
    </div>
</nav>



	<div class="container text-center">
    <div class="mt-4">
        <p>Pretraga poruka</p>
        <form action="?action=search" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="str" id="str"
                    placeholder="Unesite pojam za pretragu">
            </div>
            <button type="submit" class="btn btn-primary">Pretraga</button>
            <p class="p"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></p>
        </form>
    </div>
</div>



	<div class="container mt-4">
		<%
		for (AdvisorMessage tmp : advisorMessageBean.getAdvisorMessages()) {

			if (tmp.getAdvisorId() == idForMess) {
		%>

		<div class="card rounded-4 border mb-3">
			<div class="card-body d-flex flex-column justify-content-center">
				<div class="text-center mb-3">
					<div class="fs-5 fw-semibold"><%=tmp.getUserName()%>	<%=tmp.getUserLastName()%></div>
					<div>
						<span class="badge text-bg-danger fw-semibold fs-6 p-2"><%=tmp.getCategoryName()%></span>
					</div>
					<div class="text-secondary fw-semibold"><%=date.format(tmp.getCreatedAt())%></div>
				</div>


				<div class="text-center">
					<%
					if (tmp.isSeen()) {
					%>
					<%=tmp.getText()%>
					<%
					} else {
					%>
					<form action="?action=read" method="post">
						<input type="hidden" name="action" value="read">
						<button type="submit" name="id" value="<%=tmp.getId()%>"
							class="custom-button custom-button-unread"
							data-bs-toggle="collapse"
							data-bs-target="#messageCollapse<%=tmp.getId()%>">
							<img src="img/read.png" width="20" height="20" alt="Prikazi">
						</button>
					</form>
					<%
					}
					%>
					<div
						class="collapse <%=id == tmp.getId() && collapse ? "show" : ""%> mt-2"
						id="messageCollapse<%=tmp.getId()%>">
						<%=tmp.getText()%>
					</div>
				</div>




				<div class="d-flex justify-content-between align-items-center mt-2">
					<a href="?action=send&forWho=<%=tmp.getUserEmail()%>"
						class="btn btn-outline-primary fw-semibold position-relative">
						Odgovori <img src="img/reply.png"
						style="height: 40px; width: 40px; position: absolute; right: -45px; top: 50%; transform: translateY(-50%);"
						alt="Odgovori" class="img-fluid rounded-circle">
					</a>

				</div>

			</div>
		</div>
		<%
		}
		}
		%>
	</div>




</body>
</html>
