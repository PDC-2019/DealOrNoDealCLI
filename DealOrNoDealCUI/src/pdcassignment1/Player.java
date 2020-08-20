/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcassignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Mridula 17980560
 * @author Grace 16946441
 */
public class Player 
{
    //-------------------------------------------------------------------------- initialized variables
    Scanner scan = new Scanner(System.in);
    Banker banker = new Banker();
    
    //-------------------------------------------------------------------------- methods
    /**
     * checks whether the game is currently running or not
     * while the game is not running the Banker is offering the player a deal, so the prompt appears
     * 
     * @return true or false
     */
    public boolean gameStatus() 
    {
        boolean isTrue = false;
        int userAnswer;
        
        try {

            System.out.println("$---        Deal or No Deal? Enter a number then press [Enter]         ---$");
            System.out.println("$---                --- 1: DEAL -or- 2: NO DEAL ---                    ---$");
            System.out.println("$--- Pressing a Symbol or a Letter will automatically accept the offer ---$");
            System.out.print("~ ");
            userAnswer = scan.nextInt();
            System.out.println("");
        } 
        //if an input that is not an integer is typed in, the game stops
        //the bankers offer is automatically accepted
        catch (InputMismatchException e) 
        {
            System.out.println("                    {Offer automatically accepted!}                        ");
            isTrue = true; //stops while loop
            return false; //offer is accepted, game is stopped
        }

        while (isTrue == false) 
        {
            try 
            {
                if (userAnswer == 1) 
                {
                    
                    isTrue = true; //stops the while loop
                    return false; //offer is accepted, game is stopped
                } 
                else if (userAnswer == 2) 
                {    
                    isTrue = true; //stops the while loop
                    return true; //offer is rejected, game continues
                }           
                else
                {
                    System.out.println("");
                    System.out.println("$---        Deal or No Deal? Enter a number then press [Enter]         ---$");
                    System.out.println("$---                --- 1: DEAL -or- 2: NO DEAL ---                    ---$");
                    System.out.println("$--- Pressing a Symbol or a Letter will automatically accept the offer ---$");
                    System.out.print("~ ");
                    userAnswer = scan.nextInt();
                    System.out.println("");
                }
                isTrue = false;
            }
            //if an input that is not an integer is typed in, the game stops
            //the bankers offer is automatically accepted
            catch (InputMismatchException e) 
            {
                System.out.println("                    {Offer automatically accepted!}                        ");
                isTrue = true; //stops while loop
                return false; //offer is accepted, game is stopped
            }
            isTrue = false;
        }
        return isTrue = false;
    }
  
    /**
     * allows the user to choose the case they will keep for the entire game
     *
     * @return userCase
     */
    public int UserCase() 
    {
        boolean isUsed = false;
        int userCase = 1;

        while (isUsed == false) 
        {
            try {
                System.out.println("");

                System.out.println("                        Please select your first Case: ");
                System.out.println("   ---------------- Type a number, and then press [ENTER] ---------------");  
                System.out.print("   ~ ");
                userCase = scan.nextInt();

                if (userCase < 1 || userCase >= 25) 
                {
                    
                    System.out.println("                   {Invalid case number, please try again!}");
                } 
                else 
                {
                    //takes case out of the game because it is now the users case
                    isUsed = true; 
                }
            }
            //IN-PROGRESS
            //an attempt was made to re-route the game back to the beginning when an invalid input is typed in
            //but it made the game stuck in an endless loop
            catch (InputMismatchException e) 
            {
                System.err.println("You have input an invalid value!");
                e.printStackTrace();
                System.out.println(""); 
                System.out.println("                               {Invalid input!}");
                System.out.println("   ----------------------- Please restart the game. ---------------------");
                System.out.println("");
                System.exit(userCase);
                
            }
        }
        return userCase;
    }
    
    /**
     * allows the user to choose which cases they want to open and remove from the grid
     * then displays the money value when the case is removed
     *
     * @param i
     * @param cases
     * @return userChoice
     */
    public int RemoveCase(int i, Briefcase cases[]) 
    {
        int userChoice = 0;
        boolean validChoice = false;
        try 
        {
            while (validChoice == false) 
            {

                
                    System.out.println(""); 
                    System.out.println("                            Please remove " + i + " case/s: ");
                    System.out.println("   ---------------- Type a number, and then press [ENTER] ---------------");  
                    System.out.print("   ~ ");
                    userChoice = scan.nextInt();
                
                if (userChoice <= 0 || userChoice >= cases.length || cases[userChoice] == null) 
                {
                    System.out.println("");
                    System.out.println("                   {Invalid case number, please try again!}");
                } 
                else 
                {

                    System.out.println("                          You just removed case No. " + userChoice);
                    System.out.printf("                         Case No. " + userChoice + " contains $%.2f", cases[userChoice].getAmount());
                    System.out.println("");
                    validChoice = true;

                }
            }
        } 
        //IN-PROGRESS
        //an attempt was made to re-route the game back to the beginning when an invalid input is typed in
        //but it made the game stuck in an endless loop
        catch (InputMismatchException e) 
        {
            System.err.println("You have input an invalid value!");
            e.printStackTrace();
            System.out.println("");
            System.out.println("                                 {Invalid input!}");
            System.out.println("   ---------------------- Please restart the game. ----------------------");
            System.out.println("");
            System.exit(i);

        }
        return userChoice;

    }
    
}
