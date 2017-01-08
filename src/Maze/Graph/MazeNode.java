/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.Graph;

import java.awt.Point;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author zbookey
 */
public class MazeNode {
    private Point coordinate;
    private ArrayList<Connection> connections;
    
    public MazeNode(){
        this(new Point(-1,-1));
    }
    
    public MazeNode(int x, int y){
        this(new Point(x,y));
    }
    
    public MazeNode(Point p){
        coordinate = p;
        connections = new ArrayList();
    }
    
    public Point getCoordinate(){
        return coordinate;
    }
    
    private void setCoordinate(Point p){
        coordinate = p;
    }
    
    public int getX(){
        return coordinate.x;
    }
    
    public int getY(){
        return coordinate.y;
    }
    
    public ArrayList<Connection> getConnections(){
        return connections;
    }
    
    public boolean addConnection(MazeNode connection){
        return connections.add(new Connection(connection));
    }
    
    public boolean isConnectedTo(Point p){
        for(int i = 0; i < connections.size(); i++){
            if(connections.get(i).getNode().coordinate.equals(p))
                return true;
        }
        return false;
    }
    
    public int numberOfConnections(){
        return connections.size();
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o instanceof MazeNode){
            MazeNode other = (MazeNode) o;
            Point p = other.getCoordinate();
            if(p.equals(coordinate)) return true;
            else return false;
        } else return false;
    }
    
    public void print(PrintStream out){
        out.println("Point: ("+coordinate.x+", "+coordinate.y+")");
        out.println("Connections: "+connections.size());
        if(connections.size() > 0){
            out.print("\t");
            Iterator<Connection> iter = connections.iterator();
            while(iter.hasNext()){
                Point p = iter.next().getNode().getCoordinate();
                out.print("("+p.x+", "+p.y+") ");
            }
            out.println();
        }
    }
}
