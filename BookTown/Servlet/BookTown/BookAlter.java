package BookTown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class BookAlter
 */
@WebServlet("/BookAlter")
@MultipartConfig
public class BookAlter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAlter() {
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
		
		
		//获取提交信息
		String bookname = request.getParameter("bookname");
		String pricetemp = request.getParameter("price");
		String booktype = request.getParameter("booktype");
		String bookmsg = request.getParameter("bookmsg");
		String bookid = request.getParameter("bookid"); 
		

		
		//判断名称
		if(bookname.equals("")||bookname == null) {
			response.sendRedirect("BookAlterMsg?bookId="+bookid);
			return;
		}
		//判断价格
		if(pricetemp == null||pricetemp.equals("")) {
			response.sendRedirect("BookAlterMsg?bookId="+bookid);
			return;
		}
	
		//获取图片
		Part part = request.getPart("photo");
		long len = part.getSize();
		String fileName=null;
	
		if(len>0) {
			String s = part.getHeader("Content-Disposition");
			int pos = s.lastIndexOf(".");
			String ext = "";//后缀名
			if(pos>0) {
				ext = s.substring(pos,s.length()-1);
			}
			fileName = System.currentTimeMillis()+ext;
			part.write(getServletContext().getRealPath("/")+fileName);
		}
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		//查询类型的ID
		
		int typeid=-1;
		try {
			conn = DBConn.getConn();
			sql = "select * from booktype where name = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, booktype);
			rs=pst.executeQuery();
			rs.next();
			typeid = rs.getInt("id");			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		conn = DBConn.getConn();
		sql = "update booklist set bookname = ? , price = ? , bookmsg = ?,"
				+ " typeid = ? ";
		if(len>0) {
			sql+=", photo = ? where bookid = ? ";
		}else {
			sql+="where bookid = ?";
		}
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1,bookname);
			pst.setDouble(2,Double.valueOf(pricetemp));
			pst.setString(3, bookmsg);
			pst.setInt(4, typeid);
			if(len>0) {
				pst.setString(5,fileName);
				pst.setString(6,bookid );
			}else {
				pst.setString(5, bookid);
			}
			
		
			pst.execute() ;
			
			response.sendRedirect("AdBook");
			
			
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
