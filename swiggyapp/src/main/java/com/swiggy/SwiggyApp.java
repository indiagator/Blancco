package com.swiggy;

import com.swiggy.model.*;
import javafx.scene.transform.Scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SwiggyApp
{

    private Path fileRestaurantPath;
    private Path fileDishPath;
    private Path fileLocationPath;

    private Restaurant[] restaurants;
    private Dish[] dishes;
    private Location[] locations;

    private BufferedReader in_restaurant;
    private BufferedReader in_dish;
    private BufferedReader in_location;

    SwiggyApp() throws IOException {
        this.restaurants = new Restaurant[3]; // limits the max number of restaurants
        this.dishes = new Dish[15]; // max 5 dishes per Restaurant
        this.locations = new Location[3];

        Path p1 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\swiggyapp\\src\\main\\resources\\restaurant.csv");
        this.fileRestaurantPath = p1.toAbsolutePath();
        in_restaurant = Files.newBufferedReader(this.fileRestaurantPath);

        Path p2 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\swiggyapp\\src\\main\\resources\\dish.csv");
        this.fileDishPath = p2.toAbsolutePath();
        in_dish = Files.newBufferedReader(this.fileDishPath);

        Path p3 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\swiggyapp\\src\\main\\resources\\location.csv");
        this.fileLocationPath = p3.toAbsolutePath();
        in_location = Files.newBufferedReader( this.fileLocationPath);

        parseRestaurantData();
        parseDishData();
    }

    private void parseRestaurantData() throws IOException {


        for(int i=0; i<3;i++)
        {
            String line = in_restaurant.readLine();
            if(!(line==null))
            {
                this.restaurants[i] = new Restaurant(line); // line is the name of the restaurant
            }
            else {break;}

        }

    }

    private void parseDishData() throws IOException {

        for(int i=0; i<15;i++)
        {
            String line = in_dish.readLine();
            if(!(line==null))
            {

                String[] tempDishData = line.split(",");
                this.dishes[i] = new Dish(tempDishData[1], (Integer.valueOf(tempDishData[2])).intValue(),tempDishData[0], (Integer.valueOf(tempDishData[3])).intValue()); // line is the name of the restaurant
            }
            else {break;}

        }
    }

    private void parseLocationData()
    {

    }


    public static void main(String... args) throws IOException //main method which is the Entry Point
    {
         // local variable declaration
            Scanner console_in = new Scanner(System.in);
            int user_choice_1 = 0;

        SwiggyApp myApp = new SwiggyApp();
        System.out.println("Welcome to Swiggy!" );

        do
        {
            System.out.print("How do you want to Order today (1. Search 2. Browse) :");

            user_choice_1 = console_in.nextInt();

            if (user_choice_1 == 1) {
                System.out.println("\n You chose Search");
            } else if (user_choice_1 == 2) {
                System.out.println("\n You chose Browse ");
               // System.out.println(myApp.in_restaurant.readLine());

                for (int i = 0; i < 3; i++)
                {
                    System.out.println(myApp.restaurants[i].getName());
                }

                for (int i = 0; i < 15; i++)
                {
                    if(!(myApp.dishes[i]==null))
                    {
                        System.out.println(myApp.dishes[i].getName());
                    }
                }



            } else {
                System.out.println("\n Incorrect Option , Correct Options: 1/2 ");
            }

        }while( ! ((user_choice_1 == 1) || (user_choice_1 == 2)) );

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
