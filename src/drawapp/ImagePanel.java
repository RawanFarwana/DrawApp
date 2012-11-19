package drawapp;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class ImagePanel
{
    Canvas canvas; 
    GraphicsContext gc;
    private TurtleMode turtleMode;
    
    public ImagePanel(Canvas c)
    {
        this.canvas = c;
        gc = c.getGraphicsContext2D();
        turtleMode = new TurtleMode(gc);
    }
    
     /*
    public void setSize(int width, int height)
    {
        canvas.setWidth(width);
        canvas.setHeight(height);
    }*/
    
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
    
    public void setColourGradient(Color colour1, Color colour2)
    {
        RadialGradient rg = new RadialGradient(0,0,0,0,1,true, CycleMethod.NO_CYCLE, new Stop[]{
             new Stop(0,colour1),
             new Stop(1,colour2)
         });
        gc.setFill(rg);
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
    
    public void drawString(int x, int y, String s)
    {
        gc.strokeText(s, x, y);
    }
    
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    {
        gc.strokeArc(x, y, width, height, startAngle, arcAngle, ArcType.OPEN);
    }
    
    public void drawOval(int x, int y, int width, int height)
    {
        gc.strokeOval(x, y, width, height);
    }
    
    public void drawImage(String pathWay, int x, int y, int width, int height)
    {  
       Image image = new Image(pathWay);
       gc.drawImage(image,x,y,width,height); 
    }
    
    public void fillOval(double x, double y, double width, double height)
    {
       gc.fillOval(x, y, width, height);
    }

    public TurtleMode getTurtleMode()
    {
        return turtleMode;
    }
}