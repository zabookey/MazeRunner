/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.Graph;

import java.awt.Color;
import java.awt.Graphics2D;
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
    
    public void draw(Graphics2D g, int wallSizeX, int wallSizeY, int Xoffset, int Yoffset){
        g.setColor(Color.red);
        for(int i = 0; i<path.size()-1; i++){
            Point start = path.get(i);
            Point end = path.get(i+1);
            g.drawLine(start.x*wallSizeX+Xoffset, start.y*wallSizeY+Yoffset, end.x*wallSizeX+Xoffset, end.y*wallSizeY+Yoffset);
        }
    }
}
