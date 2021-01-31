package Controller.DataBase;

        import java.sql.Connection;
        import java.sql.DriverManager;

public class SQLConnection {
    private static final String url = "jdbc:mysql://localhost:3306/plakka?serverTimezone=UTC";
    private static final String user = "root";
    private static final String pwd = "";


    private static SQLConnection myConnection = null;

    public Connection con;

    public void tryToConn()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url,user,pwd);

        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }

    private SQLConnection(){

    }

    public static SQLConnection getInstance() {
        if(myConnection == null)
        {
            myConnection = new SQLConnection();
            myConnection.tryToConn();
        }

        return myConnection;
    }
}