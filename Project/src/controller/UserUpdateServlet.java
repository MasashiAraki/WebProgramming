package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
    	if (session.getAttribute("userInfo") == null) {
    		response.sendRedirect("LoginServlet");
    		return;
    	}

    	// 取得したリクエストパラメータを引数としてDaoを実行
    	request.setCharacterEncoding("UTF-8");
    	String id = request.getParameter("id");
    	UserDao userDao = new UserDao();
    	User userInfo = userDao.findByUserInfo(id);

    	// Dapの結果をMapに入れてフォワード
    	Map<String, String> userInfoMap = new HashMap<>();
    	userInfoMap.put("loginId", userInfo.getLoginId());
    	userInfoMap.put("userName", userInfo.getName());
    	userInfoMap.put("birthDate", userInfo.getBirthDate());

    	request.setAttribute("userInfoMap", userInfoMap);
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
		String userName = request.getParameter("userName");
		String birthDate = request.getParameter("birthDate");

		// 登録エラー時にjspへ入力情報をフォワード
		Map<String, String> userInfoMap = new HashMap<>();
		userInfoMap.put("loginId", loginId);
		userInfoMap.put("userName", userName);
		userInfoMap.put("birthDate", birthDate);

		// ユーザ名or生年月日が未入力の場合
		if (userName.equals("") || birthDate.equals("")) {
			request.setAttribute("errorMessage", "ユーザ名または生年月日を入力してください");
			errorAction(request, response, userInfoMap);
			return;
		}

		// パスワードとパスワード確認の入力内容が異なる場合
		if (!password.equals(passwordConfirm)) {
			request.setAttribute("errorMessage", "パスワードが異なります");
			errorAction(request, response, userInfoMap);
			return;
		}

		// ユーザ情報更新メソッドへ
		UserDao userDao = new UserDao();
		try {
			if (password.equals("") && passwordConfirm.equals("")) {
				userDao.UpdateNonPasswordUserInfo(loginId, userName, birthDate);
				response.sendRedirect("UserListServlet");
			} else {
				userDao.UpdateUserInfo(loginId, userName, birthDate, password);
				response.sendRedirect("UserListServlet");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param inputParameterMap
	 * @throws ServletException
	 * @throws IOException
	 */
	private void errorAction(HttpServletRequest request, HttpServletResponse response,
			Map<String, String> userInfoMap) throws ServletException, IOException {
		request.setAttribute("userInfoMap", userInfoMap);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

}
