package com.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.rays.bean.UserBean;
import com.rays.util.JDBCDataSource;

public class UserModel {

	// ResourceBundle rb = ResourceBundle.getBundle("com.rays.bundle.system");

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(Id) from user");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

		}
		return pk + 1;

	}

	public void add(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		UserBean existBean = findByLogin(bean.getLoginId());

		if (existBean != null) {

			throw new Exception("loginId allready exist...");

		}

		conn.setAutoCommit(false);

		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLoginId());
			pstmt.setString(5, bean.getPassword());
			pstmt.setString(6, bean.getAddress());
			pstmt.setString(7, bean.getGender());
			pstmt.setDate(8, new java.sql.Date(bean.getDob().getTime()));

			int i = pstmt.executeUpdate();

			System.out.println("Data Added Successfully :" + i);

			conn.commit();
		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();

		}

	}

	public void delete(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from user where id = ?");

			pstmt.setInt(1, id);

			int i = pstmt.executeUpdate();

			System.out.println("Data Deleted Successfully : " + i);

			conn.commit();

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();

		}

	}

	public void update(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update user set firstName = ?, lastName = ?, loginId = ?, password = ?, address = ?, gender = ?, dob = ? where Id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setString(3, bean.getLoginId());
		pstmt.setString(4, bean.getPassword());
		pstmt.setString(5, bean.getAddress());
		pstmt.setString(6, bean.getGender());
		pstmt.setDate(7, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setInt(8, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Update Successfully : " + i);

	}

	public List search(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user");

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setAddress(rs.getString(6));
			bean.setGender(rs.getString(7));
			bean.setDob(rs.getDate(8));

			list.add(bean);

		}
		return list;
	}

	public UserBean findByPk(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setAddress(rs.getString(6));
			bean.setGender(rs.getString(7));
			bean.setDob(rs.getDate(8));

		}
		return bean;

	}

	public UserBean findByLogin(String loginId) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user where loginId = ?");

		pstmt.setString(1, loginId);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setAddress(rs.getString(6));
			bean.setGender(rs.getString(7));
			bean.setDob(rs.getDate(8));

		}
		return bean;
	}

	public UserBean authenticate(String loginId, String password) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user where loginId = ? and password = ?");

		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setAddress(rs.getString(6));
			bean.setGender(rs.getString(7));
			bean.setDob(rs.getDate(8));

		}
		return bean;
	}

}
