/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import AI.Heuristic.Heuristic;
import AI.MazeRunner;
import AI.Search.DepthFirstSearch;
import Maze.Graph.MazeGraph;
import Maze.Maze;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import AI.Search.Search;

/**
 *
 * @author zbookey
 */
public class Driver extends JPanel{
    
    public final int WINDOW_WIDTH = 800;
    public final int WINDOW_HEIGHT = 800;
    
    private final boolean DRAW_GRAPH = false;
    private final boolean DRAW_SOLUTION = true;
    
    private Maze m;
    private MazeGraph mg;
    private MazeRunner ai;
    
    public Driver(){
        setFocusable(true);
    }
    public Driver(String filename){
        super();
        m = new Maze(filename);
        mg = new MazeGraph(m);
        ai = new MazeRunner(m,mg);
    }
    
    private void update(){
        
    }
    
    public void solve(Search method, Heuristic h){
        ai.solve(method,h);
    }
    
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        m.draw(g2d,WINDOW_WIDTH-50, WINDOW_HEIGHT-50);
        if(DRAW_GRAPH)
            mg.draw(g2d,WINDOW_WIDTH-50, WINDOW_HEIGHT-50);
        if(DRAW_SOLUTION){
            ai.draw(g2d,WINDOW_WIDTH-50, WINDOW_HEIGHT-50);
        }
    }
    
    public static void main(String[] args){
        String filename = "D:\\Documents\\GitHub\\MazeRunner\\src\\InputFiles\\20x20Input.txt";
        //Maze m = new Maze(filename);
        //m.print(System.out);
        JFrame frame = new JFrame("");
        Driver driver = new Driver(filename);
        driver.m.print(System.out);
        driver.mg.print(System.out);
        System.out.println("All things printed! Only graphics remain");
        frame.add(driver);
        frame.setSize(driver.WINDOW_WIDTH, driver.WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        driver.repaint();
        driver.solve(new DepthFirstSearch(driver.m, driver.mg), null);
    }
}


