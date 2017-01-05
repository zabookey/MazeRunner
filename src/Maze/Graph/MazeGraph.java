/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.Graph;

import Maze.Maze;
import Maze.MazeTile;
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
    
    public MazeGraph(){
        nodes = new ArrayList();
    }
    
    // TODO This should find all the critical points but it won't create the connections between them.
    public MazeGraph(Maze m){
        nodes = new ArrayList();
        Stack<Point> todo = new Stack();
        ArrayList<Point> visited = new ArrayList();
        Point start = m.getStart();
        todo.push(start);
        while(!todo.isEmpty()){
            // Find all connected nodes to the next node in the stack.
            // If they haven't been pushed or searched yet then push them to the stack.
            Point current = todo.pop();
            visited.add(current);
            //Check the north path for critical points!
            Point north = new Point(current.x, current.y+1);
            if(m.isPath(north)){
                Point critical = m.nextCriticalAlongPath(north, 0, 1);
                if(!todo.contains(critical) && !visited.contains(critical))
                    todo.push(critical);
            }
            // Check the east path for critical points!
            Point east = new Point(current.x+1, current.y);
            if(m.isPath(east)){
                Point critical = m.nextCriticalAlongPath(east,1,0);
                if(!todo.contains(critical) && !visited.contains(critical))
                    todo.push(critical);
            }
            // Check the south pat for critical points!
            Point south = new Point(current.x, current.y-1);
            if(m.isPath(south)){
                Point critical = m.nextCriticalAlongPath(south,0,-1);
                if(!todo.contains(critical) && !visited.contains(critical))
                    todo.push(critical);
            }
            // Check the west path for critical points!
            Point west = new Point(current.x-1, current.y);
            if(m.isPath(west)){
                Point critical = m.nextCriticalAlongPath(west, -1, 0);
                if(!todo.contains(critical) && !visited.contains(critical))
                    todo.push(critical);
            }
        }
        for(int i = 0; i < visited.size(); i++)
            nodes.add(new MazeNode(visited.get(i)));
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
    
    public void print(PrintStream out){
        Iterator<MazeNode> iter = nodes.iterator();
        while(iter.hasNext()){
            MazeNode node = iter.next();
            node.print(out);
        }
    }
}
