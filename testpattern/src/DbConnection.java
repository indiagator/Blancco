
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection
{
    private Connection dbconnection;

    public Connection getDbconnection()
    {
        return dbconnection;
    }

    public DbConnection()
    {
        String url = "jdbc:postgresql://localhost:5432/test";
        try
        {
            dbconnection = DriverManager.getConnection(url,"postgres","Dedsec@7");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }
}
