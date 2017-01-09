/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import AI.Search.SearchInterface;
import Maze.Graph.Connection;
import Maze.Graph.MazeGraph;
import Maze.Graph.MazeNode;
import Maze.Maze;
import java.util.ArrayList;

/**
 * This is the class that will be handed a maze object and figure out how to solve it.
 * The approach I'm thinking is to return an array of MazeNodes and a corresponding
 * array of connections?
 * Then we can use these to traverse the maze and have a solution!
 * @author Zach
 */
public class MazeRunner {
    private Maze maze;
    private MazeGraph graph;
    
    private ArrayList<MazeNode> nodes;
    private ArrayList<Connection> connections;
    
    public MazeRunner(Maze maze, MazeGraph graph){
        this.maze = maze;
        this.graph = graph;
    }
    
    public void solve(SearchInterface s){
        s.search(nodes, connections);
    }
    
    public ArrayList<MazeNode> getNodes(){
        return nodes;
    }
    
    public ArrayList<Connection> getConnections(){
        return connections;
    }
}
