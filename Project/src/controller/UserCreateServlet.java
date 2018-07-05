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
    	if (session.getAttribute("LoginUserInfo") == null) {
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
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		// UserのBeansに保存
		User inputUserInfo = new User(loginId, name, birthDate);

		// 入力項目に未入力がある場合
		if (loginId.equals("") || password.equals("") ||
				passwordConfirm.equals("") || name.equals("") || birthDate.equals("")) {
			request.setAttribute("errorMessage", "全て入力してください");
			errorAction(request, response, inputUserInfo);
			return;
		}

		// パスワードとパスワード確認の入力内容が異なる場合
		if (!password.equals(passwordConfirm)) {
			request.setAttribute("errorMessage", "パスワードが異なります");
			errorAction(request, response, inputUserInfo);
			return;
		}

		// すでにログインIDが存在した場合
		UserDao userDao = new UserDao();
		User user = userDao.findByLoginId(loginId);
		if (user != null) {
			request.setAttribute("errorMessage", "既にログインIDが存在します");
			errorAction(request, response, inputUserInfo);
			return;
		}

		// 新規登録処理
		try {
			userDao.InsertUserInfo(loginId, name, birthDate, password);
			response.sendRedirect("UserListServlet");
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param inputUserInfo
	 * @throws ServletException
	 * @throws IOException
	 */
	private void errorAction(HttpServletRequest request, HttpServletResponse response, User inputUserInfo)
			throws ServletException, IOException {
		request.setAttribute("inputUserInfo", inputUserInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userCreate.jsp");
		dispatcher.forward(request, response);
	}


}
