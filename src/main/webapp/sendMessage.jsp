<%@page import="java.nio.file.Paths"%>
<%@page import="java.io.File"%>
<%@page import="javax.activation.DataHandler"%>
<%@page import="javax.activation.FileDataSource"%>
<%@page import="javax.activation.DataSource"%>
<%@page import="java.io.InputStream"%>
<%@page import="javax.mail.Multipart"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.mail.AuthenticationFailedException"%>
<%@page import="javax.mail.internet.MimeMultipart"%>
<%@page import="javax.mail.internet.MimeBodyPart"%>
<%@page import="javax.mail.internet.AddressException"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="advisorMessageBean"
	class="net.etf.beans.AdvisorMessageBean" scope="session"></jsp:useBean>
	<jsp:useBean id="advisorBean" class="net.etf.beans.AdvisorBean"
	scope="session"></jsp:useBean>
<%
String action = request.getParameter("action");
String primalac = (String) session.getAttribute("forWho");
session.setAttribute("notification", "");

if ("send".equals(action)) {
	Session newSession = null;
	Properties props = System.getProperties();
	props.put("mail.smtp.port", "587");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	newSession = Session.getDefaultInstance(props, null);

	String emailSubject = request.getParameter("subject");
	String text = request.getParameter("message");

	MimeMessage mimeMessage = new MimeMessage(newSession);

	try {
		mimeMessage.addRecipients(Message.RecipientType.TO, primalac);
		mimeMessage.setSubject(emailSubject);

		if (request.getParameter("attachment") != null && !request.getParameter("attachment").isEmpty()) {
	MimeMultipart multiPart = new MimeMultipart();


	MimeBodyPart textPart = new MimeBodyPart();
	textPart.setContent(text, "text/plain");
	multiPart.addBodyPart(textPart);

	MimeBodyPart attachmentPart = new MimeBodyPart();
	DataSource source = new FileDataSource(request.getParameter("attachment"));
	attachmentPart.setDataHandler(new DataHandler(source));
	attachmentPart.setFileName(request.getParameter("attachment"));
	multiPart.addBodyPart(attachmentPart);
	System.out.println("ovo je putanja:" + request.getParameter("attachment"));

	mimeMessage.setContent(multiPart);
		} else {
	mimeMessage.setContent(text, "text/plain");
		}

		String from = "bojbanj@gmail.com";
		String pass = "gccacoeacwncvzyk";
		String emailHost = "smtp.gmail.com";

		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, from, pass);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		session.setAttribute("notification", "Uspješno poslan mejl");

	} catch (AddressException e) {
		e.printStackTrace();
	} catch (MessagingException e) {
		e.printStackTrace();
		System.out.println("Exception occurred while sending the email message.");
	}
}
else if (advisorBean == null || !advisorBean.isLoggedIn()) {
	response.sendRedirect("login.jsp");
}

else if("logout".equals(action))
{
	response.sendRedirect("login.jsp");

}
else if("message".equals(action))
{
	response.sendRedirect("message.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Početna strana</title>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="styles/stylemess.css" rel="stylesheet" type="text/css">
</head>
<body>

<nav class="navbar">
    <a class="navbar-brand" href="#">Fitnes centar</a>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="?action=message">Poruke</a>
    </div>
     <div class="ml-auto">
        <a class="nav-item nav-link text-white" href="?action=logout">Odjava</a>
    </div>
</nav>

	<div class="container ">
		<div class="row justify-content-center ">
			<div class="col-md-6 mt-5">
				<div class="card ">
					<div class="card-header">
						<h1 class="text-center">Novi email</h1>
						<p class="p"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></p>
					</div>
					<div class="card-body">
						<form method="post" action="?action=send">
							<div class="form-group email-input">
								<label for="email">E-mail:</label> <input type="email"
									class="form-control" id="email" name="email"
									value="<%=primalac%>">
							</div>
							<div class="form-group email-input">
								<label for="subject">Naslov:</label> <input type="text"
									class="form-control" id="subject" name="subject">
							</div>
							<div class="form-group email-input">
								<label for="message">Poruka:</label>
								<textarea class="form-control" id="message" name="message"></textarea>
							</div>
							<div class="form-group email-input">
								<label for="attachment">Priloži dokument:</label> <input
									type="file" class="form-control-file" id="attachment"
									name="attachment">
							</div>
							<button type="submit" name="submit" id="submit"
								class="btn btn-primary btn-block">Pošalji</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
