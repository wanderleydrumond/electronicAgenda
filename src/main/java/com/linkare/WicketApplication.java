package com.linkare;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see com.linkare.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private Connection connection;

	private final String DRIVER = "com.mysql.jdbc.Driver",
						 USER = "root",
						 PASSWORD = "020885",
//						 URL = "jdbc:mysql://localhost:3306/eletronicagenda"
						 URL = "jdbc:mysql://localhost:3306/eletronicagenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	;

	public Connection getConnection() {
		return connection;
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Begin.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
		connection = createConection();
	}

	private Connection createConection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}