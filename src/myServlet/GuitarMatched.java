package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.Builder;
import dao.GuitarSpec;
import dao.Type;
import dao.Wood;
import dao.WrappingDatabase;

/**
 * Servlet implementation class GuitarMatched
 */
@WebServlet("/GuitarMatched")
public class GuitarMatched extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuitarMatched() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");

		String keyword = request.getParameter("keyword");
		
		PrintWriter out = response.getWriter();
		JSONArray ja = new JSONArray();
		
		String sql="select * from Guitars";
		WrappingDatabase db=new WrappingDatabase();
		try{
			ResultSet rs = db.query(sql);
			while (rs.next()) {
				Builder builder=Builder.valueOf(rs.getString("builder").toUpperCase());
				String model=rs.getString("model");
				Type type=Type.valueOf(rs.getString("type").toUpperCase());
				int StringNum=Integer.parseInt(rs.getString("StringNum"));
				Wood backWood=Wood.valueOf(rs.getString("backWood").toUpperCase());
				Wood topWood=Wood.valueOf(rs.getString("topWood").toUpperCase());
				GuitarSpec spec=new GuitarSpec(builder,model,type,StringNum,backWood,topWood);
				if(matches(spec,keyword.toUpperCase())||keyword.toUpperCase().equals(rs.getString("model").toUpperCase())){
					JSONObject jo = new JSONObject();
					jo.put("serialNumber", rs.getString("serialNumber"));
					jo.put("price",rs.getString("price"));
					jo.put("builder", builder.toString());
					jo.put("model",model);
					jo.put("type", type.toString());
					jo.put("backwood", backWood.toString());
					jo.put("topwood",topWood.toString());
					jo.put("stringNum", StringNum);
					ja.put(jo);
				}
			}
			rs.close();
			out.print(ja.toString());
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public boolean matches(GuitarSpec otherSpec, String keyword) {
		if(keyword.equals(otherSpec.builder.toString().toUpperCase())){
			return true;
		}else if(keyword.equals(otherSpec.backWood.toString().toUpperCase())){
			return true;
		}else if(keyword.equals(otherSpec.topWood.toString().toUpperCase())){
			return true;
		}else if(keyword.equals(otherSpec.type.toString().toUpperCase())){
			return true;
		}else{
			return false;
		}
	}

}
