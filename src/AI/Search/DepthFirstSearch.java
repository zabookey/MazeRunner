/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI.Search;

import AI.Heuristic.Heuristic;
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
public class DepthFirstSearch implements Search{
    
    public Maze maze;
    public MazeGraph graph;
    
    public DepthFirstSearch(Maze maze, MazeGraph graph){
        this.maze = maze;
        this.graph = graph;
    }

    @Override
    public boolean search(ArrayList<MazeNode> nodes, ArrayList<Connection> path, Heuristic heuristic) {
        MazeNode startNode = graph.getNode(maze.getStart());
        MazeNode endNode = graph.getNode(maze.getEnd());
        DFS_Node root = new DFS_Node(startNode);
        DFS_Node current = root;
        while(!root.listContains(endNode)){
            if(current == null){
                System.out.println("Current is null...");
                return false;
            }
            if(current.createNewNode()){
                current = current.getChild();
                System.out.println("On to the next!");
            } else if(current == root){
                // We've exhausted all our options and have nothing to look for...
                return false;
            } else {
                System.out.println("Retreating...");
                current = current.getParent(); // We've exhausted all options from this node so try from the parent.
            }
        }
        // From here we have a valid DFS_Node list that will get us from the start to end!
        System.out.println("Found a solution!");
        // Fill the arrays
        current = root;
        boolean done = false;
        while(!done){
            MazeNode node = current.getMazeNode();
            nodes.add(node);
            current = current.getChild();
            if(current != null){
                MazeNode next = current.getMazeNode();
                Connection c = node.getConnection(next);
                if(c!=null){ // Should always be true.
                    path.add(c);
                }
            } else done = true;
        }
        return true;
    }
    
    public class DFS_Node{
        private DFS_Node parent;
        private DFS_Node child = null;
        private MazeNode mnode;
        private ArrayList<MazeNode> visited = new ArrayList<>();
        
        public DFS_Node(MazeNode mnode){
            this.mnode = mnode;
            visited.add(mnode);
            parent = null;
        }
        
        public DFS_Node(MazeNode mnode, DFS_Node parent){
            this.mnode = mnode;
            visited.add(mnode);
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
            DFS_Node nextNode = null;
            Iterator<Connection> iter = c.iterator();
            while(iter.hasNext()){
                Connection next = iter.next();
                MazeNode node = next.getNode();
                if(!haveVisitedNode(node)){
                    visited.add(node);
                    child = new DFS_Node(node, this);
                    return true;
                }
            }
            return false;
        }
        
        public boolean haveVisitedNode(MazeNode node){
            if(visited.contains(node)) return true;
            else{
                if(parent!=null)return parent.haveVisitedNode(node);
                else return false;
            }
        }
        
        public int numberOfConnections(){
            return mnode.numberOfConnections();
        }
        
        public boolean visitedAllFromNode(){
            Iterator<Connection> iter = mnode.getConnections().iterator();
            while(iter.hasNext()){
                MazeNode node = iter.next().getNode();
                if(!visited.contains(node)) return false;
            }
            return true;
        }
        
        public boolean listContains(MazeNode node){
            // This method assumes it's being called from the root.
            if(mnode.equals(node)) return true;
            else if(child!=null) return child.listContains(node);
            else return false;
        }
        
        public void printNode(){
            mnode.print(System.out);
        }
    }
    
}
