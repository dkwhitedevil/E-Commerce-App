package modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class din_DAO {

    private PreparedStatement pw;

	public void insert_data(din_modal s) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "cscorner");

            // Prepare the SQL statement
            ps = con.prepareStatement("INSERT INTO dincart(username, pw) VALUES(?, ?)");

            // Set the parameters
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getpw());

            // Execute the update
            ps.executeUpdate();
            System.out.println("Data inserted successfully.");
    }
    
	public boolean fetch_data(din_modal s) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "cscorner");

            // Prepare the SQL statement
            ps = con.prepareStatement("SELECT pw FROM dincart WHERE username = ?");

            // Set the parameters
            ps.setString(1, s.getUsername());

            // Execute the query
            rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("pw");
                return s.getpw().equals(storedPassword);
            } else {
                return false;
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
	
	public void saveOrder(String username, String productName, String price, Date date) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "cscorner");

            String sql = "INSERT INTO orders (username, product_name, price, order_date, order_time) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, productName);
            ps.setString(3, price);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            ps.setString(4, dateFormat.format(date));
            ps.setString(5, timeFormat.format(date));

            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
}