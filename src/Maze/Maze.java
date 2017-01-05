/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author zbookey
 */
public class Maze {
    
    private MazeTile walls[][];
    private int Xdim, Ydim;
    private Point start, end;
    
    public Maze(int sizex, int sizey){
        Xdim = sizex; Ydim = sizey;
        walls = new MazeTile[Xdim][Ydim];
        for(int i = 0; i < sizex; i++){
            for(int j = 0; j < sizey; j++){
                walls[i][j] = MazeTile.EMPTY;
            }
        }
        start = new Point(-1,-1);
        end = new Point(-1,-1);
    }
    
    public Maze(String filename){
        // TODO Read in a maze from a file.
        File f = new File(filename);
        Scanner scan;
        try{
            scan = new Scanner(f);
            buildFromFile(scan);
            scan.close();
        } catch(FileNotFoundException fnfe){
            System.err.println("The requested file could not be opened...");
            Xdim = 1; Ydim = 1;
            start = new Point(0,0);
            end = new Point(0,0);
            walls = new MazeTile[Xdim][Ydim];
            walls[0][0] = MazeTile.EMPTY;
        }
    }
    
    private void buildFromFile(Scanner scan){
        // First line is:
        // <xdim> <ydim>
        Xdim = scan.nextInt(); Ydim = scan.nextInt();
        walls = new MazeTile[Xdim][Ydim];
        // Throw out the \n from the previous line...
        scan.nextLine();
        // Since we're assuming the dimensions are correct we can run for loops here
        start = null;
        end = null;
        for(int i = 0; i < Ydim; i++){
            String line = scan.nextLine();
            for(int j = 0; j < Xdim; j++){
                if(line.charAt(j) == ' ') walls[j][i] = MazeTile.EMPTY;
                else if(line.charAt(j) == 'S'){
                    walls[j][i] = MazeTile.START;
                    start = new Point(j,i);
                }
                else if(line.charAt(j) == 'E'){
                    walls[j][i] = MazeTile.END;
                    end = new Point(j,i);
                }
                else walls[j][i] = MazeTile.WALL;
            }
        }
        if(start == null){
            System.out.println("Start position not found...");
            start = new Point(-1,-1);
        }
        if(end == null){
            System.out.println("End position not found...");
            end = new Point(-1,-1);
        }
        
    }
    
    public int getXdim(){return Xdim;}
    public int getYdim(){return Ydim;}
    public Point getStart(){return start;}
    public Point getEnd(){return end;}
    public MazeTile[][] getWalls(){return walls;}
    
    public boolean isPath(Point p){
        // Point is not in the maze...
        if(p.x<0||p.x>=Xdim||p.y<0||p.y>=Ydim) return false;
        // Point is in the maze. return true if not a wall.
        return walls[p.x][p.y]==MazeTile.EMPTY;
    }
    
    //TODO Write this.
    public boolean criticalPoint(Point p){
        int x = p.x;
        int y = p.y;
        // The point is outside of the maze or it is on the edge.
        if(x<=0 || x>=Xdim || y<=0 || y>=Ydim) return false;
        // The point is a wall
        if(walls[x][y]==MazeTile.WALL) return false;
        // If this is the start point
        if(p.equals(start)) return true;
        // If this is the end point
        if(p.equals(end)) return true;
        int sum = 0;
        // Check north
        if(walls[x][y+1]==MazeTile.EMPTY) sum++;
        // Check south
        if(walls[x][y-1]==MazeTile.EMPTY) sum++;
        // Check east
        if(walls[x+1][y]==MazeTile.EMPTY) sum++;
        // Check west
        if(walls[x-1][y]==MazeTile.EMPTY) sum++;
        if(sum >2) return true;
        else return false;
    }
    //TODO Depth could be used to ensure we never infinite loop here
    public Point nextCriticalAlongPath(Point start, int dx, int dy){
        // If the point is a critical Point
        if(criticalPoint(start)) return start;
        else{
            int x = start.x;
            int y = start.y;
            // If the spot in the desired direction is available move to it.
            if(walls[x+dx][y+dy] != MazeTile.WALL) return nextCriticalAlongPath(new Point(x+dx,y+dy), dx, dy);
            // Else try a new direction.
            else{
                //North
                if(!(dx==0&&dy==1)&&!(dx==0&&dy==-1)&&walls[x][y+1]!=MazeTile.WALL)
                    return nextCriticalAlongPath(new Point(x,y+1),0,1);
                //East
                else if(!(dx==1&&dy==0)&&!(dx==-1&&dy==0)&&walls[x+1][y]!=MazeTile.WALL)
                    return nextCriticalAlongPath(new Point(x+1,y),1,0);
                //South
                else if(!(dx==0&&dy==-1)&&!(dx==0&&dy==1)&&walls[x][y-1]!=MazeTile.WALL)
                    return nextCriticalAlongPath(new Point(x,y-1),0,-1);
                //West
                else if(!(dx==-1&&dy==0)&&!(dx==1&&dy==0)&&walls[x-1][y]!=MazeTile.WALL)
                    return nextCriticalAlongPath(new Point(x-1,y),-1,0);
                else{
                    //This is bad... Fix it later.
                    return new Point(-1,-1);
                }
            }
        }
    }
    // Method for drawing to a screen with a given screen size.
    public void draw(Graphics2D g, int width, int height){
        g.setColor(Color.black);
        int wallSizeX = width/Xdim;
        int wallSizeY = height/Ydim;
        for(int i = 0; i < Xdim; i++){
            for(int j = 0; j < Ydim; j++){
                if(walls[i][j] == MazeTile.WALL)
                    g.fillRect(i*wallSizeX, j*wallSizeY, wallSizeX, wallSizeY);
            }
        }
    }
    public void print(PrintStream out){
        out.println("Start Position: (" + start.x + " " + start.y + ")");
        out.println("End Position:   (" + end.x + " " + end.y + ")");
        for(int i = 0; i < Ydim; i++){
            for(int j = 0; j < Xdim; j++){
                if(walls[j][i] == MazeTile.WALL)
                    out.print("X");
                else
                    out.print(" ");
            }
            out.println();
        }
    }
}
