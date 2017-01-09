/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI.Search;

import Maze.Graph.Connection;
import Maze.Graph.MazeGraph;
import Maze.Graph.MazeNode;
import Maze.Maze;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Zach
 */
public class DepthFirst implements SearchInterface{
    
    public Maze maze;
    public MazeGraph graph;
    
    public DepthFirst(Maze maze, MazeGraph graph){
        this.maze = maze;
        this.graph = graph;
    }

    @Override
    public void search(ArrayList<MazeNode> nodes, ArrayList<Connection> path) {
        MazeNode startNode = graph.getNode(maze.getStart());
        MazeNode endNode = graph.getNode(maze.getEnd());
    }
    
    public class DFS_Node{
        private DFS_Node parent;
        private DFS_Node child = null;
        private MazeNode mnode;
        private ArrayList<MazeNode> visited = new ArrayList<>();
        
        public DFS_Node(MazeNode mnode){
            this.mnode = mnode;
            parent = null;
        }
        
        public DFS_Node(MazeNode mnode, DFS_Node parent){
            this.mnode = mnode;
            this.parent = parent;
        }
        
        public MazeNode getMazeNode(){
            return mnode;
        }
        
        public boolean hasParent(){
            return parent!=null;
        }
        
        public DFS_Node getParent(){
            return parent;
        }
        
        public boolean hasChild(){
            return child!=null;
        }
        
        public DFS_Node getChild(){
            return child;
        }
        
        public DFS_Node getRoot(){
            if(this.hasParent()){
                return parent.getRoot();
            } else {
                return this;
            }
        }
        
        public DFS_Node getLast(){
            if(this.hasChild()){
                return child.getLast();
            } else {
                return this;
            }
        }
        
        public void removeParent(){
            parent = null;
        }
        
        public void removeChild(){
            child = null;
        }
        
        // Returns true if a new node can be created from this and false if not possible
        public boolean createNewNode(){
            ArrayList<Connection> c = mnode.getConnections();
            Iterator<Connection> iter = c.iterator();
            while(iter.hasNext()){
                Connection next = iter.next();
                MazeNode node = next.getNode();
                if(!visited.contains(node)){
                    visited.add(node);
                    child = new DFS_Node(node, this);
                    return true;
                }
            }
            return false;
        }
        
        public int numberOfConnections(){
            return mnode.numberOfConnections();
        }
        
        public boolean visitedAll(){
            Iterator<Connection> iter = mnode.getConnections().iterator();
            while(iter.hasNext()){
                MazeNode node = iter.next().getNode();
                if(!visited.contains(node)) return false;
            }
            return true;
        }
    }
    
}
