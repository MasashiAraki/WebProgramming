package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserCreateServlet
 */
@WebServlet("/UserCreateServlet")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// ログインセッションがない場合、ログイン画面にリダイレクト
    	HttpSession session = request.getSession();
    	if (session.getAttribute("userInfo") == null) {
    		response.sendRedirect("LoginServlet");
    		return;
    	}

    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userCreate.jsp");
		dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード指定。日本語パラメータに対応するため。
		request.setCharacterEncoding("UTF-8");

		// 入力されたリクエストパラメータを取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String userName = request.getParameter("userName");
		String birthdate = request.getParameter("birthdate");



	}

}
