package com.java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.db.dao.ShopLoginDao;

@WebServlet("/LoginShop")
public class LoginShop extends HttpServlet {
	String id = null;
	String pw = null;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		action(request, response);
	}

	protected void action (HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		ShopLoginDao dao = new ShopLoginDao();
		
		id = request.getParameter("shop_id");
		pw = request.getParameter("shop_pw");


		int result = dao.login(id, pw);
	
		try {
			if (result == 1) {
				session.setAttribute("shop_id",id);
				System.out.print("사업자 로그인 완료 / 아이디: " + id + " 비밀번호: " + pw );
				response.sendRedirect("FoodTruckPage/index.jsp");

			} else if (result == -1) {
				System.out.print(id + " --- " + pw + " 로그인실패 ");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}