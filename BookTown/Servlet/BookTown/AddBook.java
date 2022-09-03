package BookTown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
@MultipartConfig
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
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
		
		//δ��¼
		
		if(se.getAttribute("adlogintag")==null) {
			response.sendRedirect("adlogin.jsp");
			return;
		}
		
		//��ȡ����		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		try {
			sql = "select * from booktype";
			conn = DBConn.getConn();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			List<BookType> booktypes = new ArrayList<BookType>();
			while(rs.next()) {
				BookType booktype = new BookType();
				booktype.id=rs.getInt("id");
				booktype.name=rs.getString("name");
				booktype.msg = rs.getString("msg");
				booktypes.add(booktype);
			}
			request.setAttribute("booktypes", booktypes);
			rs.close();
			pst.close();
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//��ȡ�ύ��Ϣ
		String bookname = request.getParameter("bookname");
		String pricetemp = request.getParameter("price");
		String booktype = request.getParameter("booktype");
		String bookmsg = request.getParameter("bookmsg");
		double price=0;
		
		request.setAttribute("bookname", bookname);
		request.setAttribute("price", price);
		request.setAttribute("booktype", booktype);
		request.setAttribute("bookmsg", bookmsg);
		
		
		//�ж�����
		if(booktype == null) {
			request.getRequestDispatcher("addbook.jsp").forward(request, response);
			return;
		}
		//�ж�����
		if(bookname.equals("")||bookname == null) {
			request.setAttribute("msg1","����������");
			request.getRequestDispatcher("addbook.jsp").forward(request, response);
			return;
		}
		//�жϼ۸�
		if(pricetemp == null||pricetemp.equals("")) {
			request.setAttribute("msg2","������۸�");
			request.getRequestDispatcher("addbook.jsp").forward(request, response);
			return;
		}
		
		//��ȡͼƬ
		Part part = request.getPart("photo");
		long len = part.getSize();
		String fileName=null;
	
		if(len>0) {
			String s = part.getHeader("Content-Disposition");
			int pos = s.lastIndexOf(".");
			String ext = "";//��׺��
			if(pos>0) {
				ext = s.substring(pos,s.length()-1);
			}
			fileName = System.currentTimeMillis()+ext;
			part.write(getServletContext().getRealPath("/")+fileName);
			System.out.println(getServletContext().getRealPath("/"));
		}
		
		//��ѯ���͵�ID
		
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
		sql = "insert into booklist value(null,?,?,?,?,?)";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1,bookname);
			pst.setDouble(2,Double.valueOf(pricetemp));
			pst.setString(3, bookmsg);
			pst.setInt(4, typeid);
			pst.setString(5,fileName);
		
			pst.execute() ;
			request.removeAttribute("bookname");
			request.removeAttribute("price");
			request.removeAttribute("booktype");
			request.removeAttribute("bookmsg");
		
			
			request.getRequestDispatcher("addbook.jsp").forward(request, response);
			
			
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
