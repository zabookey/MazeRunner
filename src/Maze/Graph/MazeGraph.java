/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.Graph;

import Maze.Maze;
import Maze.MazeTile;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author zbookey
 */
public class MazeGraph {
    public ArrayList<MazeNode> nodes;
    private int MazeXdim, MazeYdim;
    
    public MazeGraph(){
        nodes = new ArrayList();
    }
    
    // TODO This should find all the critical points but it won't create the connections between them.
    public MazeGraph(Maze m){
        nodes = new ArrayList();
        Stack<MazeNode> todo = new Stack();
        ArrayList<MazeNode> visited = new ArrayList();
        Point s = m.getStart();
        MazeNode start = new MazeNode(s);
        todo.push(start);
        while(!todo.isEmpty()){
            // Find all connected nodes to the next node in the stack.
            // If they haven't been pushed or searched yet then push them to the stack.
            MazeNode current = todo.pop();
            visited.add(current);
            //Check the north path for critical points!
            Point north = new Point(current.getX(), current.getY()+1);
            if(m.isPath(north)){
                Point critical = m.nextCriticalAlongPath(north, 0, 1);
                if(!critical.equals(new Point(-1,-1))){
                    MazeNode found = new MazeNode(critical);
                    boolean nodePreviouslyFound = true;
                    if(visited.contains(found)) found = visited.get(visited.indexOf(found));
                    else if(todo.contains(found)) found = todo.get(todo.indexOf(found));
                    else nodePreviouslyFound = false;
                    current.addConnection(found);
                    if(!nodePreviouslyFound)
                        todo.push(found);
                }
            }
            // Check the east path for critical points!
            Point east = new Point(current.getX()+1, current.getY());
            if(m.isPath(east)){
                Point critical = m.nextCriticalAlongPath(east, 1, 0);
                if(!critical.equals(new Point(-1,-1))){
                    MazeNode found = new MazeNode(critical);
                    boolean nodePreviouslyFound = true;
                    if(visited.contains(found)) found = visited.get(visited.indexOf(found));
                    else if(todo.contains(found)) found = todo.get(todo.indexOf(found));
                    else nodePreviouslyFound = false;
                    current.addConnection(found);
                    if(!nodePreviouslyFound)
                        todo.push(found);
                }
            }
            // Check the south pat for critical points!
            Point south = new Point(current.getX(), current.getY()-1);
            if(m.isPath(south)){
                Point critical = m.nextCriticalAlongPath(south, 0, -1);
                if(!critical.equals(new Point(-1,-1))){
                    MazeNode found = new MazeNode(critical);
                    boolean nodePreviouslyFound = true;
                    if(visited.contains(found)) found = visited.get(visited.indexOf(found));
                    else if(todo.contains(found)) found = todo.get(todo.indexOf(found));
                    else nodePreviouslyFound = false;
                    current.addConnection(found);
                    if(!nodePreviouslyFound)
                        todo.push(found);
                }
            }
            // Check the west path for critical points!
            Point west = new Point(current.getX()-1, current.getY());
            if(m.isPath(west)){
                Point critical = m.nextCriticalAlongPath(west, -1, 0);
                if(!critical.equals(new Point(-1,-1))){
                    MazeNode found = new MazeNode(critical);
                    boolean nodePreviouslyFound = true;
                    if(visited.contains(found)) found = visited.get(visited.indexOf(found));
                    else if(todo.contains(found)) found = todo.get(todo.indexOf(found));
                    else nodePreviouslyFound = false;
                    current.addConnection(found);
                    if(!nodePreviouslyFound)
                        todo.push(found);
                }
            }
        }
        for(int i = 0; i < visited.size(); i++)
            nodes.add(visited.get(i));
        MazeXdim = m.getXdim();
        MazeYdim = m.getYdim();
    }
    
    public int size(){
        return nodes.size();
    }
    
    public MazeNode getNode(int position){
        return nodes.get(position);
    }
    
    // Returns the node with a matching id.
    // If there is no node with the id then return null.
    public MazeNode getNode(Point p){
        for(int i = 0; i < nodes.size(); i++){
            if(nodes.get(i).getCoordinate().equals(p))
                return nodes.get(i);
        }
        return null;
    }
    
    public void draw(Graphics2D g, int width, int height){
        g.setColor(Color.orange);
        int wallSizeX = width/MazeXdim;
        int wallSizeY = height/MazeYdim;
        Iterator<MazeNode> iter = nodes.iterator();
        while(iter.hasNext()){
            MazeNode node = iter.next();
            Point p = node.getCoordinate();
            g.fillRect(p.x*wallSizeX, p.y*wallSizeY, wallSizeX, wallSizeY);
        }
    }
    
    public void print(PrintStream out){
        Iterator<MazeNode> iter = nodes.iterator();
        while(iter.hasNext()){
            MazeNode node = iter.next();
            node.print(out);
        }
    }
}
