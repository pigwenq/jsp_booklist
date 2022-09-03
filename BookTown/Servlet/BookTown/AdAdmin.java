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
@WebServlet("/AdAdmin")
public class AdAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String username="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdAdmin() {
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
		
		//未登录
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
		
		
		//查询管理员
		try {
			sql = "select * from admin where name like ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, '%'+username +'%');
			rs = pst.executeQuery();
			
			List<Admin> admins = new ArrayList<Admin>();
			
			while(rs.next()) {
				Admin admin = new Admin();
				admin.id = rs.getInt("id");
				admin.name = rs.getString("name");
				//排除自身
				if(!rs.getString("name").equals(se.getAttribute("adlogintag")))
					admins.add(admin);
			}
			
			rs.close();
			pst.close();
			conn.close();
			
			
			request.setAttribute("admins", admins);
			request.getRequestDispatcher("adadmin.jsp").forward(request, response);
			
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
