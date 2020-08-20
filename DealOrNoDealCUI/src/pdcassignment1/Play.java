/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcassignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Mridula 17980560
 * @author Grace 16946441
 */
public class Play 
{
    //-------------------------------------------------------------------------- initialized variables
    BufferedReader br;
    PrintWriter pw;
    String line;
    String welcome;
    
    //-------------------------------------------------------------------------- class constructor
    /**
     * specifies the names of the files information will be read from and sent to
     * @throws IOException 
     */
    public Play() throws IOException 
    {
        //reads input from the text file
        this.br = new BufferedReader(new FileReader("input.txt"));
        //sends output to the text file
        this.pw = new PrintWriter("output.txt");
        this.line = null;
        this.welcome = welcome;
    }
    
    //-------------------------------------------------------------------------- main method
    /**
     * main method allows the game to run
     * @param args
     * @throws IOException 
     */ 
    public static void main(String[] args) throws IOException
    {
        Game DealOrNoDeal = new Game();
        Play play = new Play();
        DealOrNoDeal.startGame();
    }
}    