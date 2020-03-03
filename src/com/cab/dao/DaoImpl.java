package com.cab.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cab.dbutil.DBUtil;
import com.cab.domain.Cabdriver;
import com.cab.domain.User;

public class DaoImpl implements Dao {

	private DBUtil db = DBUtil.db;
	private Connection con = db.getConnection();
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;

	@Override
	public boolean userSignup(User user) {
		String sql = "INSERT INTO USERSIGNUP VALUES (?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPhone());
			pst.setString(4, user.getPassword());
			int count = pst.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User userLogin(String email, String password) {
		String sql = "SELECT * from usersignup where email = ? and password = ?";
		User user = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while (rs.next()) {
				user = User.builder().email(rs.getString("email")).name(rs.getString("name"))
						.phone(rs.getString("phone")).build();
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean driverSignup(Cabdriver cab) {
		String sql = "INSERT INTO cabdriver VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, cab.getName());
			pst.setInt(2, cab.getAge());
			pst.setString(3, cab.getEmail());
			pst.setString(4, cab.getPhone());
			pst.setString(5, cab.getDlid());
			pst.setString(6, cab.getCarname());
			pst.setString(7, cab.getVechileno());
			pst.setString(8, cab.getRoute());
			pst.setString(9, cab.getAddress());
			pst.setString(10, cab.getStatus());
			int count = pst.executeUpdate();
			if (count == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Cabdriver> getAllCabDrivers() {
		List<Cabdriver> list = new ArrayList<>();
		Cabdriver cab = null;
		String sql = "SELECT * FROM cabdriver";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cab = Cabdriver.builder().name(rs.getString(1)).email(rs.getString(3)).age(rs.getInt(2))
						.phone(rs.getString(4)).dlid(rs.getString(5)).carname(rs.getString(6))
						.vechileno(rs.getString(7)).route(rs.getString(8)).address(rs.getString(9))
						.status(rs.getString(10)).build();
				list.add(cab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
