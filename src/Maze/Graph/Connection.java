/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.Graph;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Zach
 */
public class Connection {
    private MazeNode node;
    private ArrayList<Point> path;
    
    public Connection(MazeNode node){
        this.node = node;
    }
    
    public Connection(MazeNode node, ArrayList<Point> path){
        this.node = node;
        this.path = path;
    }
    
    public MazeNode getNode(){
        return node;
    }
    
    public ArrayList<Point> getPath(){
        return path;
    }
}
