/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

import java.awt.Color;
import java.awt.Graphics2D;
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
    
    public Maze(int sizex, int sizey){
        Xdim = sizex; Ydim = sizey;
        walls = new MazeTile[Xdim][Ydim];
        for(int i = 0; i < sizex; i++){
            for(int j = 0; j < sizey; j++){
                walls[i][j] = MazeTile.EMPTY;
            }
        }
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
        for(int i = 0; i < Ydim; i++){
            String line = scan.nextLine();
            for(int j = 0; j < Xdim; j++){
                if(line.charAt(j) == ' ') walls[j][i] = MazeTile.EMPTY;
                else if(line.charAt(j) == 'S') walls[j][i] = MazeTile.START;
                else if(line.charAt(j) == 'E') walls[j][i] = MazeTile.END;
                else walls[j][i] = MazeTile.WALL;
            }
        }
    }
    
    public int getXdim(){return Xdim;}
    public int getYdim(){return Ydim;}
    public MazeTile[][] getWalls(){return walls;}
    
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
