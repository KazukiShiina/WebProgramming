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
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");

		// 確認用：idをコンソールに出力
		System.out.println("ユーザID:"+id);

				HttpSession session = request.getSession(false);

				if(session == null) {
					response.sendRedirect("LoginServlet");
					return;
				}else {


		// idを引数にして、idに紐づくユーザ情報を取得
				UserDao userDao = new UserDao();
				User userDetail = userDao.UserDetail(id);


		//  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード ★

				// リクエストスコープにユーザ一覧情報をセット
				request.setAttribute("userDetail", userDetail);

				// ユーザ一覧のjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
				dispatcher.forward(request, response);
				}
			}
	}



