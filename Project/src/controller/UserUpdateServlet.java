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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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

    	// リクエストスコープにセットしてフォワード
    	forwardUserUpdateJsp(request, response, userInfoRecord);
	}

	/**
	 * @param request
	 * @param response
	 * @param userInfoRecord
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forwardUserUpdateJsp(HttpServletRequest request, HttpServletResponse response, User userInfoRecord)
			throws ServletException, IOException {
		request.setAttribute("userInfoRecord", userInfoRecord);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// パラメータを取得
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		User userInfoRecord =  new User(loginId, name, birthDate);

		// ユーザ名or生年月日が未入力の場合
		if (name.equals("") || birthDate.equals("")) {
			request.setAttribute("errorMessage", "ユーザ名または生年月日を入力してください");
			forwardUserUpdateJsp(request, response, userInfoRecord);
			return;
		}

		// パスワードとパスワード確認の入力内容が異なる場合
		if (!password.equals(passwordConfirm)) {
			request.setAttribute("errorMessage", "パスワードが異なります");
			forwardUserUpdateJsp(request, response, userInfoRecord);
			return;
		}

		// ユーザ情報更新メソッドへ
		UserDao userDao = new UserDao();
		try {
			if (password.equals("") && passwordConfirm.equals("")) {
				userDao.UpdateNonPasswordUserInfo(loginId, name, birthDate);
				response.sendRedirect("UserListServlet");
			} else {
				userDao.UpdateUserInfo(loginId, password, name, birthDate);
				response.sendRedirect("UserListServlet");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}

