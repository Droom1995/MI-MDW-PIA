package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Oleksandr on 17.12.2016.
 */
public class DataServlet extends javax.servlet.http.HttpServlet {

    public DataServlet(){
        /*String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try {
            Class.forName(driver).newInstance();
            String protocol = "jdbc:derby:";
            Connection conn = DriverManager.getConnection(protocol + "derbyDB;create=true");
            init(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static void init(Connection conn) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "RECORDS", null);
        if (rs.next()) {
            System.out.println("Table " +  rs.getString(3) + " exists");
        } else {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE RECORDS (ID INT PRIMARY KEY," +
                    "TYPE VARCHAR(255)," +
                    " LOCATION VARCHAR(255)," +
                    "CAPACITY INT, " +
                    "OCCUPIED INT," +
                    "TRIP INT," +
                    "PERSON VARCHAR(255))";
            stmt.executeUpdate(query);
            stmt.close();

            stmt = conn.createStatement();
            query = "INSERT INTO RECORDS VALUES (1,'TRIP','Prague',5,1,0,''),(2,'BOOKING','',0,0,1,'Me Me'))";
            stmt.executeUpdate(query);
            stmt.close();
        }
    }


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        try {
            String protocol = "jdbc:derby:";
            Connection conn = DriverManager.getConnection(protocol + "derbyDB;create=true");

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM RECORDS";
            ResultSet rs = stmt.executeQuery(query);



            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Title</title>");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            while (rs.next()) {
                out.println("ID " + rs.getInt("ID") + " TYPE: " + rs.getString("TYPE") +"\n");
            }
            out.println("</body>");
            out.println("</html>");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
