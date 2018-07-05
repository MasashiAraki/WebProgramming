package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクト
    	HttpSession session = request.getSession();
    	if (session.getAttribute("LoginUserInfo") == null) {
    		response.sendRedirect("LoginServlet");
    		return;
    	}

    	// 取得したリクエストパラメータを引数としてDaoを実行
    	request.setCharacterEncoding("UTF-8");
    	String id = request.getParameter("id");

    	UserDao userDao = new UserDao();
    	User userInfoRecord = userDao.findByUserInfo(id);

    	request.setAttribute("userInfoRecord", userInfoRecord);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");

		UserDao userDao = new UserDao();
		userDao.DeleteUserInfo(loginId);

		response.sendRedirect("UserListServlet");
	}

}
