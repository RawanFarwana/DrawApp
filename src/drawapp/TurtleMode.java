package drawapp;

import javafx.scene.canvas.GraphicsContext;

public class TurtleMode {
    
    private int x = 0; 
    private int y = 0; 
    private int angle = 0; 
    private GraphicsContext gc;
    
    public TurtleMode(GraphicsContext gc)
    {
        this.gc = gc;
    }
    
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void moveForward(int distance)
    {
        int newX = (int) (x + (distance * Math.cos(angle)));
        int newY = (int) (y - (distance * Math.sin(angle)));
        
        gc.strokeLine(x, y, newX, newY);
        
        x = newX;
        y = newY;
    }
   
    public void turnLeft(int angle)
    {
        this.angle -= angle; 
        
    }
    
    public void turnRight(int angle)
    {
        this.angle += angle;
    }
}
