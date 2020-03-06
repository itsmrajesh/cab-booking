package com.cab.driverdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cab.dbutil.DBUtil;
import com.cab.domain.Cabdriver;
import com.cab.domain.Ride;

public class DDaoImpl implements DDao {

	private DBUtil db = DBUtil.db;
	private Connection con = db.getConnection();
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;

	@Override
	public Cabdriver login(String email, String dlid) {
		String sql = "SELECT * FROM cabdriver WHERE EMAIL = ? AND DLID = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, dlid);
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

	@Override
	public void assignDriver(String dlid, String status) {
		String sql = "update avaldrivers set status = ? where dlid = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, dlid);
			int count = pst.executeUpdate();
			if (count == 1) {
				System.out.println("Assigned success");
			} else {
				System.out.println("Assign failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Ride getOngoingRide(String dlid) {
		String sql = "SELECT * FROM userrides WHERE DLID = ? and status = 'going' ";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, dlid);
			rs = pst.executeQuery();
			while (rs.next()) {
				return Ride.builder().dlid(rs.getString("DLID")).route(rs.getString("route")).fair(rs.getDouble("fair"))
						.status(rs.getString("status")).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean endRide(String dlid) {
		String sql = "UPDATE USERRIDES SET STATUS = 'COMPLETED' WHERE DLID = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, dlid);
			int count = pst.executeUpdate();
			if (count == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Ride> getAllRides(String dlid) {
		String sql = "SELECT * FROM userrides WHERE DLID = ? ";
		List<Ride> list = new ArrayList<>();
		Ride ride = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, dlid);
			rs = pst.executeQuery();
			while (rs.next()) {
				ride = Ride.builder().dlid(rs.getString("DLID")).route(rs.getString("route")).fair(rs.getDouble("fair"))
						.status(rs.getString("status")).build();
				list.add(ride);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
