<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.rt.util.cart" %>
<%
ServletRequest req=pageContext.getRequest();
ServletResponse res=pageContext.getResponse();
RequestDispatcher rd=null;
cart shoppingCart;
shoppingCart = (cart) session.getAttribute("cart");
System.out.println(session.getAttribute("cart"));
if(request.getParameter("product")!=null)
{
String url = request.getParameter("url");
String product = request.getParameter("product");
String amount = request.getParameter("cost");
String noi = request.getParameter("quantity");
shoppingCart.addToCart(url,product,amount,noi);
session.setAttribute("cart", shoppingCart);
}
	 rd=request.getRequestDispatcher("/cart.jsp");
	 rd.forward(request, response);

%>