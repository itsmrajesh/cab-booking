package com.cab.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cab.dbutil.DBUtil;
import com.cab.domain.Cabdriver;
import com.cab.domain.Route;
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
		String sql = "INSERT INTO cabdriver VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, cab.getName());
			pst.setInt(2, cab.getAge());
			pst.setString(3, cab.getEmail());
			pst.setString(4, cab.getPhone());
			pst.setString(5, cab.getDlid());
			pst.setString(6, cab.getCarname());
			pst.setString(7, cab.getVechileno());
			pst.setString(8, cab.getAddress());
			pst.setString(9, cab.getStatus());
			int count = pst.executeUpdate();
			if (count == 1) {
				driverInit(cab.getDlid(),"null");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void driverInit(String dlid, String status) {
		String sql = "INSERT INTO avaldrivers VALUES (?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, dlid);
			pst.setString(2, status);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
						.vechileno(rs.getString(7)).address(rs.getString(8)).status(rs.getString(9)).build();
				list.add(cab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addNewRoute(Route route) {
		String sql = "INSERT INTO ROUTES VALUES (?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, route.getRoute());
			pst.setInt(2, route.getKms());
			pst.setDouble(3, route.getFair());
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
	public List<Route> getAllRoutes() {
		String sql = "SELECT * FROM ROUTES";
		List<Route> list = new ArrayList<>();
		Route route = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				route = new Route(rs.getString(1), rs.getInt(2), rs.getDouble(3));
				list.add(route);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Route getRoute(String route) {
		String sql = "SELECT * FROM ROUTES WHERE ROUTE = ?";
		Route routeObj = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, route);
			rs = pst.executeQuery();
			while (rs.next()) {
				routeObj = new Route(rs.getString(1), rs.getInt(2), rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return routeObj;
	}

	@Override
	public boolean assignRoute(Route route, String myRoute) {
		String sql = "UPDATE ROUTES SET ROUTE = ?,DISTANCE = ? , FAIR = ? WHERE ROUTE = ?";

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, route.getRoute());
			pst.setInt(2, route.getKms());
			pst.setDouble(3, route.getFair());
			pst.setString(4, myRoute);
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
	public List<User> getAllUsers() {
		String sql = "SELECT * from usersignup";
		List<User> list = new ArrayList<>();
		User user = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				user = User.builder().email(rs.getString("email")).name(rs.getString("name"))
						.phone(rs.getString("phone")).build();

				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean updateStatus(String dlid, String status) {
		String sql = "UPDATE CABDRIVER SET STATUS = ? WHERE DLID = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, dlid);
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
	public Cabdriver getCabDriver(String dlid) {
		String sql = "select * from cabdriver where dlid = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, dlid);
			rs = pst.executeQuery();
			while (rs.next()) {
				return Cabdriver.builder().name(rs.getString(1)).email(rs.getString(3)).age(rs.getInt(2))
						.phone(rs.getString(4)).dlid(rs.getString(5)).carname(rs.getString(6))
						.vechileno(rs.getString(7)).address(rs.getString(8)).status(rs.getString(9)).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
