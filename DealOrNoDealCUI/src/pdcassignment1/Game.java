/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcassignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Mridula 17980560
 * @author Grace 16946441
 */
public class Game extends Play
{
    //-------------------------------------------------------------------------- initialized variables
    Player player = new Player();
    Banker banker = new Banker();
    Scanner scan = new Scanner(System.in);
    Briefcase briefcases[] = new Briefcase[25];
    private static String[] line; 
    
    private double userAmount = 0;
    private double offer = 0;
    private int roundCasesRemaining;
    private int roundCasesMax = 6;
    private int round = 1;
    private int numberOfCases = 25;
    
    private double listOfCases[] ={1,2,5,10,50,75,100,200,300,400,500,750,1000,5000,10000,25000,50000,75000,100000,200000,300000,400000,500000,750000,1000000};

    //-------------------------------------------------------------------------- class constructor
    public Game() throws IOException
    {
        super();
    }
    
    //-------------------------------------------------------------------------- methods
    /**
     * method that shuffles the money values to go inside the briefcases
     */
    public void shuffleCases()
    {
        Random random = new Random();
        for(int i = 0; i<listOfCases.length; i++)
        {
            int pos = random.nextInt(listOfCases.length);
            double counter = listOfCases[i];
            listOfCases[i] = listOfCases[pos];
            listOfCases[pos] = counter;
        }
    }
    
    /**
     * method that pairs a briefcase with a random money value
     */
    public void setupCase()
    {
        shuffleCases();
        for(int i = 0; i<briefcases.length; i++)
        {
            
               briefcases[i] = new Briefcase();
               double amount = listOfCases[i];
               briefcases[i].setAmount(amount);
               briefcases[i].setFaceValue(i);
            
        }
    }
    
    /**
     * method to print out the grid of briefcases while the game is still running
     * reprints the grid after a banker offer has been denied 
     * will show X instead of faceValue when a case has been picked
     */
    public void showCase()
    {
        System.out.println("");
        System.out.println("Briefcases Available:");
        for (int i = 0; i <briefcases.length; i++)
        {
            if(i == 0)
            {
                
            }
            else if(briefcases[i] == null)
            {
                System.out.print("[X]");
            }
            else
            {
                System.out.print("[" + briefcases[i].getFaceValue() + "]");
            }
            if(i % 6 == 0)
            {
                System.out.println();
            }
        }
        System.out.println();
        
    }
    
    /**
     * when the game first starts, this method is called
     * reads input from the input text file
     * controls the number of cases to be opened per round
     * 
     * @throws IOException 
     * @throws java.io.FileNotFoundException 
     */
    public void startGame() throws IOException, FileNotFoundException 
    {
        boolean gameStatus = true;
        int userAnswer;

        while (gameStatus == true) 
        {
            try {
                System.out.println("   --  Welcome to Deal or No Deal! Have you played this game before?   --");
                System.out.println("");
                System.out.println("   --                  1: YES -or- 2: SEE INSTRUCTIONS                 --");
                System.out.println("   ---------------- Type a number, and then press [ENTER] ---------------");
                System.out.print("   ~ ");
                userAnswer = scan.nextInt();

                if (userAnswer == 1) 
                {
                    setupCase();
                    showCase();

                    int choice = player.UserCase();
                    userAmount = briefcases[choice].getAmount();
                    briefcases[choice] = null;
                    numberOfCases--;

                    while (gameStatus == true) {
                        showCase();
                        if (numberOfCases == 24 || numberOfCases == 17 || numberOfCases == 12 || numberOfCases == 8 || numberOfCases == 5) {
                            for (roundCasesRemaining = roundCasesMax; roundCasesRemaining > 0; roundCasesRemaining--) {
                                int remove = player.RemoveCase(roundCasesRemaining, briefcases);
                                briefcases[remove] = null;
                                numberOfCases--;
                            }
                            roundCasesMax--;
                            round++;
                            banker.setOffer(round, briefcases, userAmount);
                            offer = banker.getOffer(round, briefcases, userAmount);
                            gameStatus = player.gameStatus();
                        } else if (numberOfCases == 2) {
                            int remove = player.RemoveCase(1, briefcases);
                            briefcases[remove] = null;
                            gameStatus = false;
                        } else {
                            int remove = player.RemoveCase(1, briefcases);
                            briefcases[remove] = null;
                            numberOfCases--;
                            banker.setOffer(round, briefcases, userAmount);
                            offer = banker.getOffer(round, briefcases, userAmount);
                            gameStatus = player.gameStatus();
                        }
                    }
                    finishGame();
                } 
                else if (userAnswer == 2) {
                    
                    Scanner br = new Scanner(new File("input.txt"));
                    ArrayList<String> instructions = new ArrayList<String>();

                    while (br != null && br.hasNextLine()) 
                    {
                        instructions.add(br.nextLine());
                    }

                    br.close();

                    String[] sentenceArray = instructions.toArray(new String[instructions.size()]);

                    for (int r = 0; r < sentenceArray.length; r++) {
                        line = sentenceArray[r].split("(?<=[.!?])\\s*");
                        for (int i = 0; i < line.length; i++) {
                            System.out.println("   " + line[i]);
                        }

                    }
                    setupCase();
                    showCase();

                    int choice = player.UserCase();
                    userAmount = briefcases[choice].getAmount();
                    briefcases[choice] = null;
                    numberOfCases--;

                    while (gameStatus == true) {
                        showCase();
                        if (numberOfCases == 24 || numberOfCases == 17 || numberOfCases == 12 || numberOfCases == 8 || numberOfCases == 5) 
                        {
                            for (roundCasesRemaining = roundCasesMax; roundCasesRemaining > 0; roundCasesRemaining--) {
                                int remove = player.RemoveCase(roundCasesRemaining, briefcases);
                                briefcases[remove] = null;
                                numberOfCases--;
                            }
                            roundCasesMax--;
                            round++;
                            banker.setOffer(round, briefcases, userAmount);
                            offer = banker.getOffer(round, briefcases, userAmount);
                            gameStatus = player.gameStatus();
                        } else if (numberOfCases == 2) {
                            int remove = player.RemoveCase(1, briefcases);
                            briefcases[remove] = null;
                            gameStatus = false;
                        } else {
                            int remove = player.RemoveCase(1, briefcases);
                            briefcases[remove] = null;
                            numberOfCases--;
                            banker.setOffer(round, briefcases, userAmount);
                            offer = banker.getOffer(round, briefcases, userAmount);
                            gameStatus = player.gameStatus();
                        }
                    }
                    finishGame();
                } 
                else {
                    System.out.println("                               {Invalid input!}");
                    System.out.println("");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.err.println("You have input an invalid value!");
                e.printStackTrace();
                System.out.println("");
                System.out.println("                               {Invalid input!}");
                System.out.println("   ----------------------- Please restart the game. ---------------------");
                System.out.println("");
                System.exit(round);
            }
            catch (FileNotFoundException g)
            {
                System.out.println("This file cannot be found and/or opened");
                g.printStackTrace(pw);
                System.exit(round);
            }
        }
    }

    /**
     * this method is called when the game ends or when the Banker offer has been accepted
     * when the game ends, the final bank offer rejection message is displayed
     * when the Bank offer has been accepted, the acceptance message is displayed
     */
    public void finishGame()
    {
        DecimalFormat df = new DecimalFormat("####.##"); //Formats money variables to 2 decimal places
        if(numberOfCases == 2)
        {
            //prints results to console
            System.out.println("");
            System.out.println("*---------------------------------- NO DEAL ------------------------------*");
            System.out.println("*---                 You have REJECTED the Bankers Offers.             ---*");
            System.out.println("");
            System.out.printf("Your case contains: $%.2f", userAmount);
            System.out.printf(" and the Bankers Offer is: $%.2f\n", offer);
            System.out.printf("You are walking away with: $%.2f\n", userAmount);
            System.out.println("");
            
            //writes results to output.txt
            String nodeal = String.format("*---------------------------------- NO DEAL ------------------------------*");
            String reject = String.format("*---                 You have REJECTED the Bankers Offers.             ---*");
            String header = ("");
            String contain = String.format("Your case contains: $" + df.format(userAmount));
            String banker = String.format(" and the Bankers Offer is: $" + df.format(offer));
            String result = String.format("You are walking away with: $" + df.format(userAmount));
            String footer = ("");
            pw.println(nodeal);
            pw.println(reject);
            pw.println(header);
            pw.print(contain);
            pw.println(banker);
            pw.println(result);
            pw.println(footer);
            
            if(userAmount > offer)
            {
                System.out.println("*-------------------------------- You WIN! -------------------------------*");
                String winner = "*-------------------------------- You WIN! -------------------------------*";
                pw.println(winner);
            }
            else
            {
                System.out.println("*------------------------------ You LOSE! --------------------------------*");
                String loser = "*------------------------------ You LOSE! --------------------------------*";
                pw.println(loser);
            }
            
        }
        else
        {
            //prints results to console
            System.out.println("");
            System.out.println("*----------------------------------- DEAL --------------------------------*"); 
            System.out.println("*---                  You have ACCEPTED the Bankers Offer.             ---*");
            System.out.println("");
            System.out.printf("Your case contains: $%.2f", userAmount);
            System.out.printf(" and the Bankers Offer is: $%.2f\n", offer);
            System.out.printf("You are walking away with: $%.2f\n", offer);
            System.out.println("");
            
            //writes results to output.txt
            String deal = String.format("*----------------------------------- DEAL --------------------------------*");
            String accept = String.format("*---                  You have ACCEPTED the Bankers Offer.             ---*");
            String header = ("");
            String contain = String.format("Your case contains: $" + df.format(userAmount));
            String banker = String.format(" and the Bankers Offer is: $" + df.format(offer));
            String result = String.format("You are walking away with: $" + df.format(offer));
            String footer = ("");
            pw.println(deal);
            pw.println(accept);
            pw.println(header);
            pw.print(contain);
            pw.println(banker);
            pw.println(result);
            pw.println(footer);
            
            if(userAmount > offer)
            {
                System.out.println("*------------------------------ You LOSE! --------------------------------*");
                String loser = "*------------------------------ You LOSE! --------------------------------*";
                pw.println(loser);
                
            }
            else
            {
                System.out.println("*-------------------------------- You WIN! -------------------------------*");
                String winner = "*-------------------------------- You WIN! -------------------------------*";
                pw.println(winner);
                
            }
        }
        if (pw != null)
        {
            pw.close();
        }
    }
    
}

