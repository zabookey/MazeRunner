/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI.Heuristic;

import Maze.Graph.MazeNode;
import java.awt.Point;

/**
 *
 * @author Zach
 */
public class MinDistanceToFinish implements Heuristic{
    
    private MazeNode start;
    private MazeNode finish;
    
    public MinDistanceToFinish(MazeNode start, MazeNode finish){
        this.start = start;
        this.finish = finish;
    }

    @Override
    public double heuristic(MazeNode parent, MazeNode child) {
        Point p = child.getCoordinate();
        return Math.abs(p.x - finish.getCoordinate().x) + Math.abs(p.y - finish.getCoordinate().y);
    }
    
}
