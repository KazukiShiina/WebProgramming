package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession(false);


		if(session == null) {
			response.sendRedirect("LoginServlet");
			return;
		}else {



		// ユーザ一覧情報を取得
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();


		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		// ユーザ一覧のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
		dispatcher.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 request.setCharacterEncoding("UTF-8");

	// リクエストパラメータの入力項目を取得
	String loginIdData = request.getParameter("loginId");
	String nameData = request.getParameter("userNm");
	String BirthDate = request.getParameter("birthDate");
	String BirthDate2 = request.getParameter("birthDate2");



	System.out.println(loginIdData);
	System.out.println(nameData);
	System.out.println(BirthDate);
	System.out.println(BirthDate2);


	// ユーザ一覧情報を取得

	UserDao userDao = new UserDao();
	List<User> userList = userDao.searchUser(loginIdData,nameData,BirthDate,BirthDate2);

	// スコープにユーザ一覧情報をセット
	request.setAttribute("userList", userList);

	// ユーザ一覧のjspにフォワード
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
		dispatcher.forward(request, response);
			}
    		}
