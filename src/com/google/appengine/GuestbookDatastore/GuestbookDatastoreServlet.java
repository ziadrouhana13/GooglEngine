package com.google.appengine.GuestbookDatastore;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class GuestbookDatastoreServlet extends HttpServlet {
	
	    // Enregistrement de la classe persistable auprès d'Objectify
	    static {
	        ObjectifyService.register(Message.class);
	    }
	    
	    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
	        try {
	            // Requête Objectify
	            List<Message> messages = ofy().load().type(Message.class).ancestor(KeyFactory.createKey("LivreOr", "livreOr")).order("-date").limit(100).list();
	            
	            req.setAttribute("messages", messages);
	            this.getServletContext().getRequestDispatcher("/WEB-INF/guestbook.jsp").forward(req, resp);
	        } catch (ServletException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    public void doPost(HttpServletRequest req, HttpServletResponse resp) {  
	        try {
	            // Création de l'objet
	            Message message = new Message(req.getParameter("name"), req.getParameter("message"));
	            // Enregistrement de l'objet dans le Datastore avec Objectify
	            ofy().save().entity(message).now();
	            resp.sendRedirect("/");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}