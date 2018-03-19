package com.dvg.facebook.community.data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSource {

	private static DataSource datasource;
	private final Map<String, ComboPooledDataSource> mapConnectionPool;

	private DataSource() throws IOException, SQLException, PropertyVetoException {

		mapConnectionPool = new HashMap<>();
		String strDatabases = ResourceBundleUtils.getValueByKey("database", "databases");
		String[] arrDatabases = strDatabases.split(",");
		for (String databaseName : arrDatabases) {
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
			cpds.setJdbcUrl(ResourceBundleUtils.getValueByKey("database", databaseName + ".mysql.url"));
			cpds.setUser(ResourceBundleUtils.getValueByKey("database", databaseName + ".mysql.username"));
			cpds.setPassword(ResourceBundleUtils.getValueByKey("database", databaseName + ".mysql.password"));

			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(Integer.parseInt(ResourceBundleUtils.getValueByKey("database", databaseName + ".c3p0.minPoolSize")));
			cpds.setMaxPoolSize(Integer.parseInt(ResourceBundleUtils.getValueByKey("database", databaseName + ".c3p0.maxPoolSize")));
			cpds.setAcquireIncrement(Integer.parseInt(ResourceBundleUtils.getValueByKey("database", databaseName + ".c3p0.accquireIncrement")));
			cpds.setMaxStatements(Integer.parseInt(ResourceBundleUtils.getValueByKey("database", databaseName + ".c3p0.maxStatements")));
			mapConnectionPool.put(databaseName, cpds);
		}

	}

	public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (datasource == null) {
			datasource = new DataSource();
			return datasource;
		} else {
			return datasource;
		}
	}

	public Connection getConnection(String databaseName) throws SQLException {
		return this.mapConnectionPool.get(databaseName).getConnection();
	}
	
	public Connection getFBCommunityConnection() throws SQLException{
		return getConnection("fb_comm");
	}
	
	public Connection getFBCommunityStoreConnection() throws SQLException{
		return getConnection("fb_comm_store");
	}

}
