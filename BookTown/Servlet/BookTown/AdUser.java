package BookTown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AdUser
 */
@WebServlet("/AdUser")
public class AdUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String username="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		HttpSession se = request.getSession();
		
		if(se.getAttribute("adlogintag")==null) {
			response.sendRedirect("adlogin.jsp");
			return;
		}
		
		//获取用户名
		String un =request.getParameter("findOfName");
		
		username= un!=null?un:username;
		
		
		request.setAttribute("findOfName", username);
		
		Connection conn = DBConn.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		
		
		//查询用户
		try {
			sql = "select * from user where username like ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, '%'+username +'%');
			rs = pst.executeQuery();
			
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User user = new User();
				user.id = rs.getInt("id");
				user.username = rs.getString("username");
				user.power = rs.getBoolean("power")==false?"禁用":"启用";
				users.add(user);
			}
			
			//查询下单次数
			
			for(User user : users) {
				sql = "select * from goods where userid = ? ";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, user.id);
				rs = pst.executeQuery();
				rs.last();
				user.sum = rs.getRow();
			}
			rs.close();
			pst.close();
			conn.close();
			
			
			request.setAttribute("users", users);
			request.getRequestDispatcher("aduser.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
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
