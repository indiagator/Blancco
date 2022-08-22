package com.swiggy;

import com.swiggy.database.DbConnection;
import com.swiggy.exceptions.IncorrectMainArgumentException;
import com.swiggy.exceptions.InsufficientFundsException;
import com.swiggy.model.*;
import javafx.scene.transform.Scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SwiggyApp
{

    private Set<Customer> customerSet;
    private Customer customer;
    private Path fileRestaurantPath;
    private Path fileDishPath;
    private Path fileLocationPath;

    private Path fileCustomerPath;

    //private Restaurant[] restaurants;
    private Set<Restaurant> restaurantSet;

    //private Dish[] dishes;
    private Set<Dish> dishSet;

    //private Location[] locations;
    private Set<Location> locationSet;

    private BufferedReader in_restaurant;
    private BufferedReader in_dish;
    private BufferedReader in_location;

    private BufferedReader in_customer;

    private Map<Integer, Restaurant> restroMap;

    private String[] indexes; // holds the Order Indexes

    private Order order;



    SwiggyApp() // throw's' keyword means passing the buck --- delegating it to someone else
    {
        this.restaurantSet = new HashSet<>();
        this.dishSet = new HashSet<>();
        this.locationSet = new HashSet<>();
        this.restroMap = new HashMap<>();
        this.customerSet = new HashSet<>();

        try
        {
            Path p1 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\swiggyapp\\src\\main\\resources\\restaurant.csv");
            this.fileRestaurantPath = p1.toAbsolutePath();
            in_restaurant = Files.newBufferedReader(this.fileRestaurantPath);

            Path p2 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\swiggyapp\\src\\main\\resources\\dish.csv");
            this.fileDishPath = p2.toAbsolutePath();
            in_dish = Files.newBufferedReader(this.fileDishPath);

            // throw means actually throwing an exception

            Path p3 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\swiggyapp\\src\\main\\resources\\location.csv");
            this.fileLocationPath = p3.toAbsolutePath();
            in_location = Files.newBufferedReader(this.fileLocationPath);

            Path p4 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\swiggyapp\\src\\main\\resources\\customer.csv");
            this.fileCustomerPath = p4.toAbsolutePath();
            in_customer = Files.newBufferedReader(this.fileCustomerPath);

            parseCustomerData();
            parseDishData();
            parseLocationData();
            parseRestaurantData();

        }
        catch (IOException|NullPointerException|ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.getStackTrace().toString() + e.getCause() + e.getMessage());
        }
        finally // optional and this is not a replacement for ELSE
        {
            //System.out.println("This is from the Finally Block of the SwiggyApp Constructor ");
        }
    }

    public Set<Restaurant> getRestaurantSet() {
        return restaurantSet;
    }

    public Map<Integer, Restaurant> getRestroMap() {
        return restroMap;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public Set<Dish> getDishSet() {
        return this.dishSet;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    private void parseRestaurantData() throws IOException {


        while (true)
        {
            String line = in_restaurant.readLine();
            if(!(line==null)) // Menu Data and Location have to be set right here
            {

                Map<Integer, Dish> tempDishMap  = new HashMap<>();

                int  dishCounter = 1;
                for( Dish dish : this.dishSet)
                {
                    if(dish.getRestroName().equals(line))
                    {
                        tempDishMap.put(new Integer(dishCounter), dish);
                        dishCounter++;

                    }

                }


                this.restaurantSet.add
                        (
                                new Restaurant(
                                        line,
                                        tempDishMap,
                        this.locationSet.stream().filter(location -> location.getName().equals(line)).collect(Collectors.toList()).get(0))
                        ) ; // line is the name of the restaurant


                //Dish[] tempMenu = new Dish[5];

                //Set<Dish> tempMenu = new HashSet<>();
                //List<Location> tempLocation = new ArrayList<>();

               // tempMenu = this.dishSet.stream().filter(dish -> dish.getRestroName().equals(line)).collect(Collectors.toSet());
               // tempLocation = this.locationSet.stream().filter(location -> location.getName().equals(line)).collect(Collectors.toList());



                // int tempMenuCntr = 0;
                //for(int j = 0; j < this.dishSet.size() ; j++) // parse dishes.array
                //{

                  //  if(this.dishes[j].getRestroName().equals(line))
                  //  {
                  //      tempMenu[tempMenuCntr] = this.dishes[j];
                  //      tempMenuCntr++;
                    //}

                //}

               // for(Dish dish : this.dishSet) // simplified For loop when you wis to iterate over te wole list or set
              //  {
               //     if(dish.getRestroName().equals(line))
               //     {
                //        tempMenu.add(dish);
                //    }
               // }



               // for(int k = 0; k<3; k++) // parse locations array
               // {
                 //   if(this.locations[k].getName().equals(line))
                 //   {
                 //       tempLocation = this.locations[k];
                 //   }
               // }

               // for ( Location location : this.locationSet)
                //{
                 //   if(location.getName().equals(line))
                  //  {
                  //      tempLocation = location; break;
                   // }
               // }



            }
            else {break;}

        }

    }

    private void parseDishData() throws IOException {

        while (true)
        {
            String line = in_dish.readLine();
            if(!(line==null))
            {
                String[] tempDishData = line.split(",");
                this.dishSet.add(new Dish(tempDishData[1], (Integer.valueOf(tempDishData[2])).intValue(),tempDishData[0], (Integer.valueOf(tempDishData[3])).intValue())) ; // line is the name of the restaurant
            }
            else {break;}
        }
    }

    private void parseLocationData() throws IOException
    {

       while(true)
        {
            String line = in_location.readLine();
            if(!(line==null))
            {
                String[] tempLocation = line.split(",");
                this.locationSet.add(new Location(tempLocation[0], (Integer.valueOf(tempLocation[1])).intValue(), (Integer.valueOf(tempLocation[2])).intValue())); // line is the name of the restaurant
            }
            else {break;}
        }

    }

    private void parseCustomerData() throws IOException {
        while(true)
        {
            String line = in_customer.readLine();
            if(!(line==null))
            {
                String[] tempCustomerData = line.split(",");
                Customer tempCustomer = new Customer(tempCustomerData[0],tempCustomerData[3],tempCustomerData[2] ,tempCustomerData[1] );
                tempCustomer.setLocation(new Location(tempCustomer.getName(), Integer.valueOf(tempCustomerData[4]) , Integer.valueOf(tempCustomerData[5]).intValue() ));

                this.customerSet.add (tempCustomer); // line is the name of the restaurant
            }
            else {break;}
        }

    }

    private void search(SwiggyApp myApp)
    {
        System.out.println("\n You chose Search");
        System.out.println("*****");

        System.out.print("What are you thinking about?: ");
        Scanner console_in = new Scanner(System.in);
        String userSearchString = console_in.next();


        Function<String, Predicate<Dish>> findPatternFunction =

                searchString -> {
                    Pattern pattern  = Pattern.compile(searchString);

                    Predicate<Dish> checkDish =
                            dish -> {
                                Matcher matcher = pattern.matcher(dish.getName());
                                return matcher.find();

                            };

                    return checkDish;
                };

        Set<Dish> searchDishSet = myApp.getDishSet().stream().filter(findPatternFunction.apply(userSearchString)).collect(Collectors.toSet());
        //searchDishSet.forEach (dish -> System.out.println (dish.getName() ) );

        int restroCounter = 1;
        for(Dish dish : searchDishSet)
        {
            if(!myApp.getRestroMap().values().stream().anyMatch(restro -> restro.getName().equals(dish.getRestroName())))
            {
                myApp.getRestroMap().put(Integer.valueOf(restroCounter), myApp.getRestaurantSet().stream().filter(restro -> restro.getName().equals(dish.getRestroName())).findAny().get());
                restroCounter++;
            }
        }

        myApp.getRestroMap().entrySet().forEach(entry ->
                                                    {
                                                        System.out.println(entry.getKey().intValue()+". "+entry.getValue().getName());
                                                        entry.getValue().printMenu();
                                                    } );






        //Search Logic goes here
    }



    private void browse(SwiggyApp myApp)
    {
        System.out.println("\n You chose Browse ");
        int restroCounter = 1;
        for (Restaurant restro : myApp.restaurantSet)
        {
            System.out.println("******");


            this.restroMap.put(new Integer(restroCounter), restro);
            System.out.println(restroCounter+". "+restro.getName()+"'s MENU below | will be delivered in "+myApp.calcDelTime(myApp.getCustomer().getLocation(),restro.getLocation() )+" Minutes");

            System.out.println("**MENU**");

            //int dishCounter = 1;

           // for( Dish dish : restro.getMenu())
           // {
            //    System.out.print("    "+dishCounter+". "+dish.getName()+" ");
            //    System.out.print("INR"+dish.getPrice()+" ");
            //    if(dish.getType() == 0)
             //   {System.out.print("Veg \n");}
             //   else if (dish.getType() == 1) {System.out.print("Non Veg \n");}

             //   dishCounter++;
           // }

            restro.printMenu();

            System.out.println("******");
            restroCounter++;
        }


        // System.out.println(myApp.in_restaurant.readLine());

        //for (int i = 0; i < 3; i++) // Basic Loop to Process all Restros
       // {
        //    System.out.println(myApp.restaurants[i].getName()+"'s MENU below | will be delivered in "+myApp.calcDelTime(myApp.getCustomer().getLocation(),myApp.restaurants[i].getLocation() )+" Minutes");

        //    for(int j = 0; j <5 ; j++) // Inner Loop to process the Dishes of the Restro
         //   {
          //      if(myApp.restaurants[i].getMenu()[j] != null)
           //     {
          //          Dish tempDish = myApp.restaurants[i].getMenu()[j];

          //          System.out.print(tempDish.getName()+" ");
           //         System.out.print("INR"+tempDish.getPrice()+" ");

           //         if(tempDish.getType() == 0)
           //         {System.out.print("Veg \n");}
             //       else if (tempDish.getType() == 1) {System.out.print("Non Veg \n");}
             //   }
           // }
          //  System.out.println("******");
       // }

    }

    public void chooseDishes()
    {

        Scanner console_in = new Scanner(System.in);
        String user_choice_1 = "";

        System.out.print("Please choose the Restaurant and the Dish in this format -> (Restaurant,Dish1,Qty1,Dish2,Qty2,Dish3,Qty3...) for ex (3,4,2,1) :");
        user_choice_1 = console_in.next();

        this.indexes = user_choice_1.split(","); // This is where the Order is being Created | First index holds the Restro and others hold the dishes

        Restaurant restro = this.restroMap.get(Integer.valueOf(this.indexes[0]));

        System.out.println("You Chose Restro:"+ restro.getName());

        this.order = new Order(this.customer, restro.getName());

        Map<Dish, Integer> tempDishMap = new HashMap<>();


        boolean firstIterationFlag = false;
        boolean dishQtyFlag = false;
        Dish tempDish = null;
        for(String dishIndexString : indexes)
        {


            if(firstIterationFlag == true)
            {


                if(dishQtyFlag == false) // this block will be executed for odd index values which are the dish references
                {
                       tempDish = restro.getMenu().get(Integer.valueOf(dishIndexString));
                       dishQtyFlag = true;
                }
                else if (firstIterationFlag == true) // this block will be executed for even index values which are the quantities
                {
                    tempDishMap.put(tempDish,Integer.valueOf(dishIndexString));
                    System.out.println("You Chose: "+dishIndexString+" Units of:"+ tempDish.getName() );
                    dishQtyFlag = false;

                }
               // System.out.println("You Chose Dish:"+ restro.getMenu().get(Integer.valueOf(dishIndexString)).getName() );


            }

            firstIterationFlag = true;
        }

        this.order.setDishMap(tempDishMap);

    }

    public void confirmOrder()
    {
        System.out.println("Do you wish to make a payment of INR"+this.getOrder().getOrderAmnt()+" for this order? (yes/no)");

        Scanner console_in = new Scanner(System.in);
        String user_choice_1 = console_in.next();

        if(user_choice_1.equals("yes"))
        {

            try
            {
                this.getCustomer().getWallet().deductPayment(this.getOrder().getOrderAmnt());

                System.out.println("Your updated Wallet Balance is INR"+this.getCustomer().getWallet().getBalance());
                Restaurant restro = this.restaurantSet.stream().filter(restaurant -> restaurant.getName().equals(this.getOrder().getRestroName()) ).findFirst().get();
                System.out.println("Your Order is Confirmed and will be delivered in "+this.calcDelTime(this.getCustomer().getLocation(),restro.getLocation() )+" Minutes");


            }
            catch (InsufficientFundsException e)
            {
                System.out.println(e.getMessage());

                System.out.println("Do you wish to update Wallet Balance ? (yes/no)" );
                Scanner console_in_1 = new Scanner(System.in);
                String user_choice_2 = console_in_1.next();

                if(user_choice_2.equals("yes"))
                {
                    this.updateWalletBalance();
                    this.confirmOrder(); // recursion

                }
                else
                {
                    System.out.println("Your Order is Deleted!");
                    this.order.setStatusDeleted();
                }

            }

        }
        else if (user_choice_1.equals("no"))
        {
            System.out.println("Your Order is Deleted!");
            this.order.setStatusDeleted();
        }


    }

    public void updateWalletBalance()
    {
        System.out.print("Please enter Recharge Amount: ");
        Scanner console_in = new Scanner(System.in);
        String user_choice_1 = console_in.next();

        int updatedBalance = this.getCustomer().getWallet().updateBalance(Integer.valueOf(user_choice_1));

        System.out.println("Your Updated Balance is : "+this.getCustomer().getWallet().getBalance());


    }

    private int calcDelTime( Location custLocation, Location restroLocation)
    {
        double defSpeed = 10.0; // units/min

        double distance = Math.sqrt((custLocation.getLat()-restroLocation.getLat())*(custLocation.getLat()-restroLocation.getLat()) + (custLocation.getLongi() - restroLocation.getLongi())*(custLocation.getLongi() - restroLocation.getLongi()));

        return ((Double)(distance/defSpeed)).intValue(); // time taken in Minutes
    }

    private static void testDbConnect() throws SQLException
    {
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
                String phonenumber = (resultSet.getString(1)); // Database Schema is actually shared with the Developer
                String name = (resultSet.getString(2));

                System.out.print(phonenumber+" ");
                System.out.println(name);

                // System.out.println("Welcome to Swiggy "+resultSet.getString(2)+"! Your current registered phonenumber is "+resultSet.getString(1));

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        dbconnection.close();

    }

    private void authenticateCustomer(SwiggyApp myApp) throws SQLException {

        System.out.print("USERNAME: ");
        Scanner console_in_username = new Scanner(System.in);
        String username = console_in_username.next();


        Optional<Customer> optionalCustomer = this.getCustomerSet().stream().filter(customer1 -> customer1.getUsername().equals(username)).findAny();
        Customer tempCustomer = null;

        if(optionalCustomer.isPresent())
        {
            System.out.print("PASSWORD: ");

            tempCustomer = optionalCustomer.get();
            Scanner console_in_password = new Scanner(System.in);
            String password = console_in_password.next();

            if(tempCustomer.getPassword().equals(password))
            {
                myApp.setCustomer(tempCustomer);
                myApp.exploreApp();
            }
            else
            {
                System.out.println("Incorrect Password!");
                SwiggyApp.initApp();
            }
        }
        else
        {
            System.out.println("Invalid Username | Please SignUp!");

            //Invoke the Signup Method!
            SwiggyApp.initApp();
        }

    }

    private void exploreApp()
    {

        // local variable declaration
        Scanner console_in = new Scanner(System.in);
        int user_choice_1 = 0;
        do
        {

            try{
                System.out.print("How do you want to Order today (1. Search 2. Browse) :");

                user_choice_1 = console_in.nextInt();

                if (user_choice_1 == 1) //SEARCH
                {
                    this.search(this);
                }
                else if (user_choice_1 == 2)  //BROWSE
                {
                    this.browse(this);
                }
                else
                {
                    //System.out.println("\n Incorrect Option , Correct Options: 1/2 ");
                    throw new IncorrectMainArgumentException();

                }
            }catch (IncorrectMainArgumentException e)
            {
                System.out.println(e.getMessage());
                // System.out.println(e.getCause());
                // System.out.println(e.getStackTrace().toString());
            }
            finally
            {
                //System.out.println("This is the Finally Block of the Main Argument try block");
            }

        }while( ! ((user_choice_1 == 1) || (user_choice_1 == 2)) );
    }

     public static void initApp() throws SQLException {

         SwiggyApp myApp = new SwiggyApp(); // Constructor for Swiggy App


         //Customer customer1 = new Customer("Hemant","9876344214","jldshfljsd","sdgfsgf");
         //customer1.setLocation(new Location(customer1.getName(),675,908));
        // myApp.setCustomer(customer1);

         myApp.authenticateCustomer(myApp);

         myApp.chooseDishes();

         myApp.confirmOrder(); // Make Payment

     }

    public static void main(String... args) throws SQLException   //main method which is the Entry Point
    {

        //testDbConnect();

        System.out.println("Welcome to Swiggy!" );

        SwiggyApp.initApp();


    }

    public void justAnotherMethod()
    {
        getCustCount(8);

    }
    public int getCustCount(int i)
    {

        return i+5;

    }
    private void toTest()
    {
        int i = 6; // Declaration of a Local Variable because it is Declaraed within the Method body
        int j = User.i;
        User user ;

        System.out.println("******************");

        Person personNew = new Person("Pratibha", "897731339");


        //Person person1 = new Customer("Tushar", "7865432123");
        //System.out.println(person1);

        System.out.println("******************");

        System.out.println("******************");


        Vendor person2 = new Vendor("Dhairya", "6574320987");
        System.out.println(person2);

        person2.setThisName("Monali");
        System.out.println(person2.getThisName());

        System.out.println(person2.getSuperName());

        System.out.println(person2.getName());


        System.out.println("******************");

        Payment payment = new Payment();


        Wallet myWallet = new Wallet(2000);
        //myWallet.setBalance(1000);

        System.out.println("Value of Local Variable i before increment is : "+i);
        //System.out.println("Value returned from Increment Method is : "+payment.incrementInt(i));
        System.out.println("Value of Local Variable i after increment is : "+i);

        System.out.println("Wallet Balance before Increment is :" + myWallet.getBalance());
        //System.out.println(" Balance returned from the increment Method  is :" + payment.incrementWalletBalance(myWallet).getBalance());
        System.out.println("Wallet Balance after Increment is :" + myWallet.getBalance());

        System.out.println("******************");

        //LivingThing lv= LivingThingBuilder.build(args[0]);
        //System.out.println(lv.sayHello());

    }


}
