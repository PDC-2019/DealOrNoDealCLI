/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcassignment1;

/**
 * 
 * @author Mridula 17980560
 * @author Grace 16946441
 */
public class Briefcase 
{
    //-------------------------------------------------------------------------- initialized variables
    private double amount;
    private int faceValue;
    
    //-------------------------------------------------------------------------- class constructor
    public Briefcase()
    {
        super();
    }
    
    //-------------------------------------------------------------------------- getter and setter methods 
    /**
     * sets the value of amount 
     * @param amount 
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    /**
     * gets the value of amount
     * @return amount
     */
    public double getAmount()
    {
        return this.amount;
    }
    
    /**
     * sets the value of faceValue
     * @param faceValue 
     */
    public void setFaceValue(int faceValue)
    {
        this.faceValue = faceValue;
    }
    /**
     * gets the value of faceValue
     * @return faceValue
     */
    public int getFaceValue()
    {
        return faceValue;
    }
    
}
