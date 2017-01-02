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
public class MazeNode {
    private String id;
    private ArrayList<MazeNode> connections;
    
    public MazeNode(){
        id = "";
        connections = new ArrayList();
    }
    
    public String getID(){
        return id;
    }
    
    public ArrayList<MazeNode> getConnections(){
        return connections;
    }
    
    public boolean addConnection(MazeNode connection){
        return connections.add(connection);
    }
    
    public boolean isConnectedTo(String id){
        for(int i = 0; i < connections.size(); i++){
            if(connections.get(i).id == id)
                return true;
        }
        return false;
    }
    
    public int numberOfConnections(){
        return connections.size();
    }
}
