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
			request.setAttribute("msg", "�û���Ϊ��");
			request.getRequestDispatcher("adlogin.jsp").forward(request, response);
			return;
		}
		
		if(password.equals("")) {
			request.setAttribute("msg", "����Ϊ��");
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
			if(rs.next()) {//���ڸù���Ա
				
				//�ж�����
				if(!rs.getString("password").equals(password)) {
					request.setAttribute("msg", "�����������������");
					request.getRequestDispatcher("adlogin.jsp").forward(request, response);
					return;
				}
				//��¼�ɹ�
				{
					//ת�����ɹ���ҳ��
					HttpSession se = request.getSession();
					se.setAttribute("adlogintag", userName);
					response.sendRedirect("admain.jsp");
					
				}
	
			}else {
				request.setAttribute("msg", "�û�������");
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
