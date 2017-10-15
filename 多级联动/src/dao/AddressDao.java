package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;

import bean.CityBean;
import bean.ProvinceBean;


public class AddressDao {
	//得到城市的列表
	public List<ProvinceBean> getProvinceList() {
		// TODO Auto-generated method stub
		String sql="select * from province";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<ProvinceBean> provinces = new ArrayList<ProvinceBean>();
		ProvinceBean province=null;
		
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				province=new ProvinceBean();
				province.setId(rs.getInt("id"));
				province.setName(rs.getString("name"));
				province.setPostcode(rs.getString("postcode"));
				province.setCreateDate(rs.getString("create_date"));
				provinces.add(province);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return provinces;	
	}
	
	public List<CityBean> getCityList(int parentId) {
		// TODO Auto-generated method stub
		String sql="select * from city where province_id='"+parentId+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<CityBean> citys = new ArrayList<CityBean>();
		CityBean city=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				city =new CityBean();
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setPostcode(rs.getString("postcode"));
				city.setProvince_id(rs.getInt("province_id"));
				city.setCreatedate(rs.getString("create_date"));
				citys.add(city);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(rs, state, conn);
		}
		return citys;
	}
}
