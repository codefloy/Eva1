package svl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import dao.CineDAO;

	public class svlCine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public svlCine() {
        super();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		dao.CineDAO daocine=new dao.CineDAO();
		
		Object idCine = request.getParameter("idCine");
		
		if(idCine == null) {
			String [][] mCines =daocine.getVerCines();
			session.setAttribute("id",mCines== null ? null : "4");
			session.setAttribute("mCines",mCines);
			
		}else {
			String[] aCine = daocine.getVerCine(idCine);
			session.setAttribute("id",aCine == null ? null : "5");
			session.setAttribute("aCine",aCine);
			session.setAttribute("mTarifas",daocine.getCineTarifas(idCine));
			session.setAttribute("mPeliculas",daocine.getCinePeliculas(idCine));
		}
		response.sendRedirect("index.jsp");
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
