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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		request.setAttribute("userName", userName);
		String password1 = request.getParameter("password1"); 
		String password2 = request.getParameter("password2"); 
		
		if(userName==null) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		//密码不相同
		if(!password1.equals(password2)) {
			request.setAttribute("msg", "密码不一致");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(userName.equals("")) {
			request.setAttribute("msg", "用户名为空");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		if(password1.equals("")) {
			request.setAttribute("msg", "密码为空");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		
		Connection conn=DBConn.getConn();
		
		String sql = "select * from User where userName=?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {//已存在
				request.setAttribute("msg", "用户已存在");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}else {
				sql = "insert into User values(null,?,?,true)";
				pst = conn.prepareStatement(sql);
				pst.setString(1,userName);
				pst.setString(2, password1);
				if(pst.executeUpdate()>0) {//插入成功
					request.setAttribute("msg", "注册成功");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", "Error");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
			
			pst.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
