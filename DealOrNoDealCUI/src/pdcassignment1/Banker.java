/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcassignment1;

import java.util.Random;

/**
 * 
 * @author Mridula 17980560
 * @author Grace 16946441
 */
public class Banker 
{
    //-------------------------------------------------------------------------- initialized variables
    private double total;
    private double amount;
    double average;
    private int counter;
    
    //-------------------------------------------------------------------------- constructor
    public Banker()
    {
        this.total = 0;
        this.amount = 0;
        this.average = 0;
        this.counter = 0;        
    }
    
    //-------------------------------------------------------------------------- methods
    /**
     * calculates the amount of money the banker will offer the player
     * @param round
     * @param cases
     * @param userAmount 
     */
    public void setOffer(int round, Briefcase[] cases, double userAmount)
    {
        Random random = new Random();
        for (int i = 0; i <cases.length; i++)
        {
            if(cases[i] == null)
            {
                
            }
            else
            {
                total = total + cases[i].getAmount();
                counter++;
                
            }
        }
        int extra = random.nextInt(20);
        average = userAmount + total / counter;
        
        if (round >=2)
        {
            amount = average * round / extra - 13567;
            if (amount >= 800000)
            {
                amount = 20000 + (200 * extra);
            }
            else if(amount <= 0)
            {
                amount = 30000;
            }
        }
        else
        {
            amount = average * round / 10;
            if (amount >= 800000)
            {
                amount = 20000 + (200 * extra);
            }
            else if(amount <= 0)
            {
                amount = 30000;
            }
        }
        
    }
    
    /**
     * returns the amount of money the banker will offer the player
     * @param round
     * @param cases
     * @param userAmount
     * @return amount
     */
    public double getOffer(int round, Briefcase[] cases, double userAmount)
    {
        setOffer(round, cases, userAmount);
        System.out.println("");
        System.out.println("");
        System.out.printf("$------------------- The Banker Offers You: $%.2f", amount);
        System.out.printf(" --------------------$ \n\n");
        return amount;
    }
    
}
