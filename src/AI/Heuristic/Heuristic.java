/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI.Heuristic;

import Maze.Graph.MazeNode;

/**
 *
 * @author Zach
 */
public interface Heuristic {
    
    // int?
    // The heuristic function that returns a value to describe how good of a choice the next child is.
    public double heuristic(MazeNode parent, MazeNode child);
    
}
