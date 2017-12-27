package com.rt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import com.rt.Service.RService;
import com.rt.util.ContainerUtility;
import com.rt.util.SSLEmail;
import com.rt.util.cart;

public class PaymentOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationContext ctx=null;
	RService service=null;
	 HttpSession session=null;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		   PrintWriter out=res.getWriter();
			ctx=ContainerUtility.getContainer();
			session=req.getSession();
			RequestDispatcher rd=null;
			Map<String,Map<String,Map<String,String>>> hp=cart.getCartItems();
		    String fname=req.getParameter("fname");
			String lname=req.getParameter("lname");        
	        String email=req.getParameter("email");	        
	        String mobile=req.getParameter("phone");
	        String city=req.getParameter("city");
	        String pincode=req.getParameter("pincode");
	        String country=req.getParameter("country");
	        String address=req.getParameter("addr");
            String famt=req.getParameter("famt");
            String srcaccno=req.getParameter("srcaccno");
         service=ctx.getBean("MService",RService.class);
         
		String result=service.userOrder(fname,lname,email,mobile,city,pincode,country,address,hp,famt,srcaccno);
		if(result!=null) { 	
			       SSLEmail.send(email,hp);
					rd=req.getRequestDispatcher("/Done.jsp");
					rd.forward(req, res);  
					
				}else {
				 out.println("<html><body><center>Transaction Failed<br>");
	             out.println("<a href='Products.jsp'>Try Again</a></center></body></html>");
				}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
