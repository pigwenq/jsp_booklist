package BookTown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		String userName = request.getParameter("userName"); 
		request.setAttribute("userName",userName);
		String password = request.getParameter("password"); 
		
		if(userName==null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		if(userName.equals("")) {
			request.setAttribute("msg", "用户名为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		if(password.equals("")) {
			request.setAttribute("msg", "密码为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		Connection conn=DBConn.getConn();
		
		String sql = "select * from User where userName=?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {//存在该用户
				//判断用户权限
				if(!rs.getBoolean("power")) {
					request.setAttribute("msg", "账户异常！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//判断密码
				if(!rs.getString("password").equals(password)) {
					request.setAttribute("msg", "密码错误，请重新输入");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				//登录成功
				{
					//重定向到成功的页面
					HttpSession se = request.getSession();
					se.setAttribute("loginTag", userName);
					response.sendRedirect("main.jsp");

				}
				
				
				
				
			}else {
				request.setAttribute("msg", "用户不存在");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
