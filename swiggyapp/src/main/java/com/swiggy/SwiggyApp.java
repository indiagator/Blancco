package com.swiggy;

import com.swiggy.exceptions.IncorrectMainArgumentException;
import com.swiggy.model.*;
import javafx.scene.transform.Scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SwiggyApp
{

    private Customer customer;
    private Path fileRestaurantPath;
    private Path fileDishPath;
    private Path fileLocationPath;

    //private Restaurant[] restaurants;
    private Set<Restaurant> restaurantSet;

    //private Dish[] dishes;
    private Set<Dish> dishSet;

    //private Location[] locations;
    private Set<Location> locationSet;

    private BufferedReader in_restaurant;
    private BufferedReader in_dish;
    private BufferedReader in_location;

    private Map<Integer, Restaurant> restroMap;

    SwiggyApp() // throw's' keyword means passing the buck --- delegating it to someone else
    {
        this.restaurantSet = new HashSet<>();
        this.dishSet = new HashSet<>();
        this.locationSet = new HashSet<>();
        this.restroMap = new HashMap<>();

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    private void search(SwiggyApp myApp)
    {
        System.out.println("\n You chose Search");

        //Search Logic goes here
    }



    private void browse(SwiggyApp myApp)
    {
        System.out.println("\n You chose Browse ");
        int restroCounter = 1;
        for (Restaurant restro : myApp.restaurantSet)
        {
            System.out.println("******");


            restroMap.put(new Integer(restroCounter), restro);
            System.out.println(restroCounter+". "+restro.getName()+"'s MENU below | will be delivered in "+myApp.calcDelTime(myApp.getCustomer().getLocation(),restro.getLocation() )+" Minutes");

            System.out.println("**MENU**");

            int dishCounter = 1;

           // for( Dish dish : restro.getMenu())
           // {
            //    System.out.print("    "+dishCounter+". "+dish.getName()+" ");
            //    System.out.print("INR"+dish.getPrice()+" ");
            //    if(dish.getType() == 0)
             //   {System.out.print("Veg \n");}
             //   else if (dish.getType() == 1) {System.out.print("Non Veg \n");}

             //   dishCounter++;
           // }

            for (int i = 1; i < (restro.getMenu().size()+1);i++ )
            {
                System.out.print("    "+i+". "+restro.getMenu().get(new Integer(i)).getName()+" ");
                System.out.print("INR"+restro.getMenu().get(new Integer(i)).getPrice()+" ");
                if(restro.getMenu().get(new Integer(i)).getType() == 0)
                {System.out.print("Veg \n");}
                else if (restro.getMenu().get(new Integer(i)).getType() == 1) {System.out.print("Non Veg \n");}

            }

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

        System.out.print("Please choose the Restaurant and the Dish in this format -> (Restaurant,Dish1,Dis2,Dish3...) for ex (3,4,2,1) :");
        user_choice_1 = console_in.next();

        String[] indexes = user_choice_1.split(",");

        System.out.println("You Chose :"+ this.restroMap.get(Integer.valueOf(indexes[0])).getName());

        for(String dishIndexString : indexes)
        {
            if(Integer.valueOf(dishIndexString).intValue() != 0)
            System.out.println("You Chose :"+ this.restroMap.get(Integer.valueOf(indexes[0])).getMenu().get(Integer.valueOf(dishIndexString)).getName() );
        }



    }

    private int calcDelTime( Location custLocation, Location restroLocation)
    {
        double defSpeed = 10.0; // units/min

        double distance = Math.sqrt((custLocation.getLat()-restroLocation.getLat())*(custLocation.getLat()-restroLocation.getLat()) + (custLocation.getLongi() - restroLocation.getLongi())*(custLocation.getLongi() - restroLocation.getLongi()));

        return ((Double)(distance/defSpeed)).intValue(); // time taken in Minutes
    }


    public static void main(String... args)  //main method which is the Entry Point
    {
         // local variable declaration
            Scanner console_in = new Scanner(System.in);
            int user_choice_1 = 0;

        SwiggyApp myApp = new SwiggyApp(); // Constructor for Swiggy App

        Customer customer1 = new Customer("Hemant","9876344214");
        customer1.setLocation(new Location(customer1.getName(),675,908));
        myApp.setCustomer(customer1);

        System.out.println("Welcome to Swiggy!" );


        do
        {

        try{
            System.out.print("How do you want to Order today (1. Search 2. Browse) :");

            user_choice_1 = console_in.nextInt();

            if (user_choice_1 == 1) //SEARCH
            {
                myApp.search(myApp);
            }
            else if (user_choice_1 == 2)  //BROWSE
            {
                myApp.browse(myApp);
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

        myApp.chooseDishes();

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


        Person person1 = new Customer("Tushar", "7865432123");
        System.out.println(person1);

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
        System.out.println("Value returned from Increment Method is : "+payment.incrementInt(i));
        System.out.println("Value of Local Variable i after increment is : "+i);

        System.out.println("Wallet Balance before Increment is :" + myWallet.getBalance());
        System.out.println(" Balance returned from the increment Method  is :" + payment.incrementWalletBalance(myWallet).getBalance());
        System.out.println("Wallet Balance after Increment is :" + myWallet.getBalance());

        System.out.println("******************");

        //LivingThing lv= LivingThingBuilder.build(args[0]);
        //System.out.println(lv.sayHello());

    }


}
