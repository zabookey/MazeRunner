/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI.Search;

import Maze.Graph.Connection;
import Maze.Graph.MazeNode;
import java.util.ArrayList;

/**
 *
 * @author Zach
 */
public interface SearchInterface {
    
    // The assumption is that these array lists come in empty and will be added to as the search occurs.
    public void search(ArrayList<MazeNode> nodes, ArrayList<Connection> path);
    
}
