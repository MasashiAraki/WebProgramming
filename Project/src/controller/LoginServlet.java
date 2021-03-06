package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
			return;
    	}
    	response.sendRedirect("UserListServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// 文字コード指定
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメータを取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		// リクエストパラメータを引数にして、Daoを実行
		UserDao userDao = new UserDao();
		User user = null;
		try {
			user = userDao.findByLoginInfo(loginId, password);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// テーブルに該当のデータが見つからなかった場合
		if (user == null) {
			request.setAttribute("errorMessage", "ログインIDまたはパスワードが異なります");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// テーブルに該当のデータが見つかった場合
		HttpSession session = request.getSession();
		session.setAttribute("LoginUserInfo", user);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

}
