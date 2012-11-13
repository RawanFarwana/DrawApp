package drawapptest;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ImagePanel
{
    private Canvas canvas; 
    private GraphicsContext gc;
    
    public ImagePanel(Canvas c)
    {
      this.canvas = c; 
      gc = c.getGraphicsContext2D();
    }
    
    protected void paintComponent(GraphicsContext gc)
    {
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());   
    }
    
    public void setBackgroundColour(Color colour)
    {
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());   
    }
    
    public void clear(Color colour)
    {
        setBackgroundColour(colour);
    }
    
    public void setColour(Color colour)
    {
        gc.setFill(colour);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2)
    {
        gc.strokeLine(x1, y1, x2, y2);
    }
    
    public void drawRect(int x1, int y1, int x2, int y2)
    {
        gc.strokeRect(x1, y1, x2, y2);
    }
    
    public void fillRect(int x1, int y1, int x2, int y2)
    {
        gc.fillRect(x1, y1, x2, y2);
    }
    
    public void strokeText(int x, int y, String s)
    {
        gc.strokeText(s, x, y);
    }
    
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    {
        gc.arc(x, y, width, height, startAngle, arcAngle);
    }
    
    public void drawOval(int x, int y, int width, int height)
    {
        gc.strokeOval(x, y, width, height);
    }
}