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
 * Servlet implementation class AdLoginServlet
 */
@WebServlet("/AdLoginServlet")
public class AdLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdLoginServlet() {
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
		String password = request.getParameter("password"); 
		
		if(userName==null) {
			request.getRequestDispatcher("adlogin.jsp").forward(request, response);
			return;
		}
		
		if(userName.equals("")) {
			request.setAttribute("msg", "用户名为空");
			request.getRequestDispatcher("adlogin.jsp").forward(request, response);
			return;
		}
		
		if(password.equals("")) {
			request.setAttribute("msg", "密码为空");
			request.getRequestDispatcher("adlogin.jsp").forward(request, response);
			return;
		}
		
		Connection conn=DBConn.getConn();
		
		String sql = null;
		
		try {
			sql = "select * from admin where name=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {//存在该管理员
				
				//判断密码
				if(!rs.getString("password").equals(password)) {
					request.setAttribute("msg", "密码错误，请重新输入");
					request.getRequestDispatcher("adlogin.jsp").forward(request, response);
					return;
				}
				//登录成功
				{
					//转发到成功的页面
					HttpSession se = request.getSession();
					se.setAttribute("adlogintag", userName);
					response.sendRedirect("admain.jsp");
					
				}
	
			}else {
				request.setAttribute("msg", "用户不存在");
				request.getRequestDispatcher("adlogin.jsp").forward(request, response);
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
