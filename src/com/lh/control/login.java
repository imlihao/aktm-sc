package com.lh.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lh.dao.userDao;
import com.lh.daoImp.userDaoImp;
import com.lh.vo.user;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user u=new user();
        
        userDao ud=new userDaoImp();
        //boolean flag=ud.exist(u);
        boolean flag=true;
		String s=request.getParameter("uid");
		String s2=request.getParameter("psd");
		flag=true;
		if(!flag)response.sendRedirect("/AKTM/index.html");
		else{
			request.getSession().setAttribute("name", u.getUname());
			request.getRequestDispatcher("/WEB-INF/jsp/surface.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
