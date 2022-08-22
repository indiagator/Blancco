import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main
{

    public static void dbConnect() throws SQLException {
        Connection dbconnection = (new DbConnection()).getDbconnection();

        if (dbconnection.isValid(0))
        {
            System.out.println("Connection Established");
        }

        String query = "SELECT * FROM customer";
        Statement statement = null;
        ResultSet resultSet = null;

        try
        {
            statement = dbconnection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                String s1 = (resultSet.getString(1));
                String s2 = (resultSet.getString(2));

                System.out.print(s1+" ");
                System.out.println(s2);

                // System.out.println("Welcome to Swiggy "+resultSet.getString(2)+"! Your current registered phonenumber is "+resultSet.getString(1));

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");

        List<TestClass> names = new ArrayList<>();


        names.add(new TestClass("delhi"));

        names.add(new TestClass("mumbai"));

        names.add(new TestClass("chennai"));

        names.add(new TestClass("noida"));

        names.add(new TestClass("bengaluru"));

        names.add(new TestClass("gangtok"));

        //Matcher matcher = pattern.matcher("ram,shyam");

        //if(matcher.find())
       // {
        //    System.out.println(matcher.group());
       // }


        Function<String, Predicate<TestClass>> findPatternFunction =

                searchString -> {
                    Pattern pattern  = Pattern.compile(searchString);

                     Predicate<TestClass> findRam =
                            name -> {
                                Matcher matcher = pattern.matcher(name.getName());
                                return matcher.find();

                            };

                    return findRam;
                };

        List<TestClass> output = names.stream().filter(findPatternFunction.apply("bai")).collect(Collectors.toList());

        output.forEach(obj -> System.out.println(obj.getName()));



    }
}