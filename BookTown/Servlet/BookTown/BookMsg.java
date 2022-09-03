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

import com.mysql.cj.xdevapi.DbDoc;

/**
 * Servlet implementation class BookMsg
 */
@WebServlet("/BookMsg")
public class BookMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String bookid = request.getParameter("bookId");
		
		Connection conn = DBConn.getConn();
		
		String sql = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			sql = "select * from booklist where bookid = ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, bookid);
			rs = pst.executeQuery();
			rs.next();
			Book book = new Book();
			book.bookId = rs.getInt("bookid");
			book.bookname = rs.getString("bookname");
			book.price = rs.getDouble("price");
			
			book.photo = rs.getString("photo");
			book.bookmsg = rs.getString("bookmsg");
			
			sql = "select * from booktype where id = ? ";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, rs.getInt("typeid"));
			
			rs = pst.executeQuery();
			if(rs.next()) {
				book.typename = rs.getString("name");
			}
			request.setAttribute("book", book);
			
			rs.close();
			pst.close();
			conn.close();
			
			request.getRequestDispatcher("bookmsg.jsp").forward(request, response);
			
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
