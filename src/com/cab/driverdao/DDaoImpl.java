package com.cab.driverdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cab.dbutil.DBUtil;
import com.cab.domain.Cabdriver;

public class DDaoImpl implements DDao {

	private DBUtil db = DBUtil.db;
	private Connection con = db.getConnection();
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;

	@Override
	public Cabdriver login(String email, String dlid) {
		String sql = "SELECT * FROM cabrides WHERE EMAIL = ? AND DLID = ?";

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

}
