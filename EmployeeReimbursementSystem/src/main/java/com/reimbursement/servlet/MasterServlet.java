package com.reimbursement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/api/*")
public class MasterServlet extends HttpServlet {
	final static Logger log = Logger.getLogger(MasterServlet.class);
	private static final long serialVersionUID = 1L;
	
	public MasterServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Inside master servlets DOGET method");
		RequestHelper.process(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Inside Master Servlet DO POST method");
		doGet(request,response);
	}
}
