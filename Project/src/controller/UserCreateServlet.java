package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

		// ログインID比較用
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();
		request.setAttribute("userList", userList);


		// 登録エラー時に使う
		Map<String, String> inputParameterMap = new HashMap<>();
		inputParameterMap.put("loginId", loginId);
		inputParameterMap.put("userName", userName);
		inputParameterMap.put("birthdate", birthdate);



		// すでにログインIDが存在した場合
		if (request.getAttribute("userList").equals(loginId)) {
			errorAction(request, response, inputParameterMap);
			return;
		}

		// パスワードとパスワード確認の入力内容が異なる場合
		if (password.equals(passwordConfirm)) {
			errorAction(request, response, inputParameterMap);
			return;
		}

		// 入力項目に未入力がある場合
		if (!loginId.equals("") || !password.equals("") ||
				!passwordConfirm.equals("") || !userName.equals("") || !birthdate.equals("")) {
			errorAction(request, response, inputParameterMap);
			return;
		}
		
		
	}


	/**
	 * エラーが発生した際の処理
	 * @param request
	 * @param response
	 * @param inputParameterMap
	 * @throws ServletException
	 * @throws IOException
	 */
	private void errorAction(HttpServletRequest request, HttpServletResponse response,
			Map<String, String> inputParameterMap) throws ServletException, IOException {
		request.setAttribute("inputParameter", inputParameterMap);
		request.setAttribute("errorMessage", "入力された内容は正しくありません");
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userCreate.jsp");
		dispatcher.forward(request, response);
	}

}
