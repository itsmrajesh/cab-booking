package com.cab.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cab.dao.Dao;
import com.cab.dao.DaoImpl;
import com.cab.dbutil.DBUtil;
import com.cab.domain.Booking;
import com.cab.domain.Cabdriver;
import com.cab.domain.Route;

public class UserDaoImpl implements UserDao {

	private DBUtil db = DBUtil.db;
	private Connection con = db.getConnection();
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;

	private Dao dao = new DaoImpl();

	@Override
	public boolean canRide(String email) {
		String sql = "SELECT EMAIL FROM userrides WHERE EMAIL = ? AND STATUS = ?";

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, "null");
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Booking getRideDetails(String route) {
		Route routeObj = dao.getRoute(route);
		String dlid = getAvalDriver();
		if (dlid != null) {
			Cabdriver cabDriverObj = dao.getCabDriver("KA05IK88");
			return new Booking(routeObj, cabDriverObj);
		}
		return null;
	}

	private String getAvalDriver() {
		String sql = "SELECT DLID FROM avaldrivers where status = 'null' ";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
