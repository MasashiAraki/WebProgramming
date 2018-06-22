package controller;

import java.io.IOException;
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
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
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
    	userInfoMap.put("name", userInfo.getName());
    	userInfoMap.put("birthDate", userInfo.getBirthDate());
    	userInfoMap.put("createDate", userInfo.getCreateDate());
    	userInfoMap.put("updateDate", userInfo.getUpdateDate());

    	request.setAttribute("userInfoMap", userInfoMap);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userDetail.jsp");
		dispatcher.forward(request, response);


    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
