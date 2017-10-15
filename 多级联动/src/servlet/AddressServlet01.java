package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CityBean;
import bean.ProvinceBean;

import com.alibaba.fastjson.JSON;

import dao.AddressDao;

/**
 * Servlet implementation class AddressServlet01
 */
@WebServlet("/servlet/addressServlet01")
public class AddressServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddressServlet01() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");

		if ("show".equals(method)) {
			show(req, resp);
		} else if ("getCity".equals(method)) {
			getCity(req, resp);
		}
	}

	private void getCity(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO Auto-generated method stub
		int parentId = Integer.parseInt(req.getParameter("id"));
		AddressDao addressDao = new AddressDao();
		List<CityBean> cityList = addressDao.getCityList(parentId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(cityList));  //转换成JSON字符串然后用print输出
		out.flush();
		out.close();
	}

	private void show(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		AddressDao addressDao = new AddressDao();
		List<ProvinceBean> provinces = addressDao.getProvinceList();
		req.setAttribute("provinces", provinces);
		req.getRequestDispatcher("/b.jsp").forward(req, resp);
	}
}
