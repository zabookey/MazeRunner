/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.Graph;

import java.util.ArrayList;

/**
 *
 * @author zbookey
 */
public class MazeGraph {
    public ArrayList<MazeNode> nodes;
    
    public MazeGraph(){
        nodes = new ArrayList();
    }
    
    public int size(){
        return nodes.size();
    }
    
    public MazeNode getNode(int position){
        return nodes.get(position);
    }
    
    // Returns the node with a matching id.
    // If there is no node with the id then return null.
    public MazeNode getNode(String id){
        for(int i = 0; i < nodes.size(); i++){
            if(nodes.get(i).getID() == id)
                return nodes.get(i);
        }
        return null;
    }
}
