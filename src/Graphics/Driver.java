/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Maze.Maze;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author zbookey
 */
public class Driver extends JPanel{
    
    public final int WINDOW_WIDTH = 500;
    public final int WINDOW_HEIGHT = 500;
    private Maze m;
    
    public Driver(){
        setFocusable(true);
    }
    public Driver(String filename){
        super();
        m = new Maze(filename);
    }
    
    private void update(){
        
    }
    
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        m.draw(g2d,WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    
    public static void main(String[] args){
        String filename = "/home/zbookey/NetBeansProjects/MazeRunner/src/InputFiles/input.txt";
        Maze m = new Maze(filename);
        m.print(System.out);
        JFrame frame = new JFrame("");
        Driver driver = new Driver(filename);
        frame.add(driver);
        frame.setSize(driver.WINDOW_WIDTH, driver.WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        driver.repaint();
    }
}


