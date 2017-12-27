package com.rt.DAO;

import java.util.List;

import com.rt.bo.CustomerBO;
import com.rt.bo.OrderBO;

public interface RStoreDAO {

	public CustomerBO loginUser(String name, String pass);

	public int[] ordetDetails(List<OrderBO> listbo);

	public String RRegistor(String fname, String lname, String email, String pass, String mobile, String city,
			String pincode, String country, String address);

	public boolean cCheck(String ccode);

	public int withdraw(int srcacc, float amt);

	public int deposite(int i, float amt);
	

}
