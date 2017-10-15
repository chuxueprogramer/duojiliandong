package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


import bean.CityBean;
import bean.ProvinceBean;

import dao.AddressDao;

/**
 * Servlet implementation class AdressServlet
 */
@WebServlet("/servlet/addressServlet")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");

		if ("show".equals(method)) {
			show(req, resp);
		}else if ("getCity".equals(method)) {
			System.out.println("nihao!!!");
			getCity(req, resp);
		}
	}

	private void getCity(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO Auto-generated method stub
		int parentId =Integer.parseInt(req.getParameter("id"));
		AddressDao addressDao = new AddressDao();
		List<CityBean> cityList = addressDao.getCityList(parentId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(cityList));
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
