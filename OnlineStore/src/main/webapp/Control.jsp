<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.rt.util.cart" %>
<%
ServletRequest req=pageContext.getRequest();
ServletResponse res=pageContext.getResponse();
RequestDispatcher rd=null;
cart shoppingCart;
shoppingCart = (cart) session.getAttribute("cart");
if(shoppingCart == null){
 shoppingCart = new cart();
 session.setAttribute("cart", shoppingCart);
 System.out.println(session.getAttribute("cart"));
 }
if(request.getParameter("product")!=null)
{
String url = request.getParameter("url");
String product = request.getParameter("product");
String amount = request.getParameter("cost");
String noi = request.getParameter("quantity");
shoppingCart.addToCart(url,product,amount,noi);
session.setAttribute("cart", shoppingCart);
System.out.println(session.getAttribute("cart"));
}
if(("Add TO Cart".equals(req.getParameter("cart")))){
	  
	 rd=request.getRequestDispatcher("/cart.jsp");
	 rd.forward(request, response);
	}
else if(("Remove".equals(req.getParameter("delete")))){
		String name = request.getParameter("deleteitem");
		shoppingCart.deleteFromCart(name);
		session.setAttribute("cart", shoppingCart);
		rd=request.getRequestDispatcher("/cart.jsp");
		 rd.forward(request, response);
	}
else{
		
     if((session.getAttribute("uname")!=null)&&(session.getAttribute("pass")!=null)){
      rd=req.getRequestDispatcher("/checkout.jsp");
      rd.forward(req, res);
      }else{
    	    req.setAttribute("lcheck", "0");
	        rd=req.getRequestDispatcher("/login.jsp");
	        rd.forward(req, res);
           }
}

%>