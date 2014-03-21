





<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.GuestbookDatastore.Message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Livre d'or</title>
		<meta charset="utf-8" />
	</head>

	
<body  bgcolor="#115675" img src ="/home/ziad/Desktop/index.jpeg">

		<h1>Vous avez aimé Notre mariage? Dites-le et donner nous vos voeux!</h1>
		<form action="/post" method="post">
			<p>
				<label>Votre nom : <input type="text" name="name" /></label>
			</p>
			<p>
				<label>Votre message :<br />
				<textarea name="message" style="width: 200px; height: 100px;"></textarea></label>
			</p>
			<p>
				<input type="submit" value="comment" />
			</p>
		</form>
	
		<h1>Ils ont aimé :</h1>
		<%
			List<Message> messages = (List<Message>) request.getAttribute("messages");
			for (Message message : messages) {
		%>
		<p>
			<strong><%= message.getName() %></strong> a écrit :<br />
			<%= message.getMessage() %>
		</p>
		<%
			}
		%>
	</body>
</html>