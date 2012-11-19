package drawapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;
import javafx.scene.paint.Color;

public class Parser 
{
    private BufferedReader reader; 
    private ImagePanel image;
    private MainWindow frame;
    
    public Parser(Reader reader, ImagePanel image, MainWindow frame)
    {
        this.reader = new BufferedReader(reader);
        this.image = image;
        this.frame = frame;
    }
    
    public void parse() //Called if runAllBtn is pressed
    {
        try
        {
            String line = reader.readLine();
            
            while(line!=null)
            {
                parseLine(line);
                line = reader.readLine();
            }
        }
        
        catch(IOException e)
        {
            frame.postMessage("Parse failed.");
            return;
        }
        
        catch(ParseException e)
        {
            frame.postMessage("Parse Exception: " + e.getMessage());
            return;
        } 
        
        frame.postMessage("(Run Everything) Drawing completed");
    }
    
    public int parseStepByStep() //Called if runStepByStepBtn is pressed
    {
        try
        {
            String line = reader.readLine();
            
            if (line == null)
            {
                System.out.println("No more lines to execute");
                return -1;  
            }
            parseLine(line);
        }
        
        catch(IOException e)
        {
            frame.postMessage("Parse failed.");
            return 0;
        }
        
        catch(ParseException e)
        {
            frame.postMessage("Parse Exception: " + e.getMessage());
            return 0;
        } 
        frame.postMessage("(StepByStep) Drawing completed");
        return 0;
    }
    
    private void parseLine(String line) throws ParseException
    {
        if (line.length() < 2) return; 
        
        String command = line.substring(0, 2);
        
        /** Essential Features **/
        if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
        if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
        if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
        if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
        if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
        if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
        if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
        if (command.equals("CG")) { setColourGradient(line.substring(3, line.length())); return; }
        if(command.equals("DI")) { drawImage(line.substring(2, line.length())); return; }
        if(command.equals("SS")) { setSize(line.substring(2, line.length())); return; }
        
        /** TurtleGraphics Features **/
        if(command.equals("TP")) { setPosition(line.substring(2, line.length())); return; }
        if(command.equals("TM")) { moveForward(line.substring(2, line.length())); return; }
        if(command.equals("TL")) { turnLeft(line.substring(2, line.length())); return; }
        if(command.equals("TR")) { turnRight(line.substring(2, line.length())); return; }
      
        /** Extra Features **/
        if(command.equals("FO")) { fillOval(line.substring(2, line.length())); return; }
        
        throw new ParseException("Drawing Command was not recognised."); 
    }
    
    private boolean setSize(String args) throws ParseException
    {
        int width = 0; 
        int height = 0; 
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
      
        System.out.println(width + " " + height);
        frame.setWidth(width);
        frame.setHeight(height);
        //image.setSize(width, height);
        
        return true; 
    }
    
    private void drawLine(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        
        image.drawLine(x1,y1,x2,y2);
    }
    
    private void drawRect(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        
        image.drawRect(x1,y1,x2,y2);
    }
    
    private void fillRect(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
       
        image.fillRect(x1, y1, x2, y2);
    }
    
    private void drawArc(String args) throws ParseException
    {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        int startAngle = 0;
        int arcAngle = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        startAngle = getInteger(tokenizer);
        arcAngle = getInteger(tokenizer);
        
        image.drawArc(x, y, width, height, startAngle, arcAngle);
    }
    
    private void drawOval(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int width = 0;
        int height = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        
        image.drawOval(x1, y1, width, height);
    }
    
    private void drawString(String args) throws ParseException
    {
        int x = 0;
        int y = 0 ;
        
        String s = "";
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        
        int position = args.indexOf("@");
        
        if (position == -1) throw new ParseException("DrawString string is missing");
        
        s = args.substring(position+1,args.length());
   
        image.drawString(x,y,s);
    }
    
    private void drawImage(String args) throws ParseException 
    {
        int x = 0; 
        int y = 0; 
        int width = 0; 
        int height = 0; 
        
        String pathWay = "";
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x = getInteger(tokenizer);
        y = getInteger(tokenizer);
        width = getInteger(tokenizer);
        height = getInteger(tokenizer);
        
        int position = args.indexOf('@');
        if (position == -1) throw new ParseException("Image path is missing");
        pathWay = args.substring(position + 1);

        image.drawImage(pathWay, x, y, width, height);
    }
    
    private void setColour(String colourName) throws ParseException
    {
        image.setColour(getColour(colourName));
        
        throw new ParseException("Invalid colour name");
    }
  
    private Color getColour(String colourName) throws ParseException 
    {
        switch (colourName) { 
            case "black": return Color.BLACK;
            case "blue": return Color.BLUE;
            case "cyan": return Color.CYAN;
            case "darkgray": return Color.DARKGRAY;
            case "gray": return Color.GRAY;
            case "green": return Color.GREEN;
            case "lightgray": return Color.GREEN;
            case "magenta": return Color.MAGENTA;
            case "orange": return Color.ORANGE;
            case "pink": return Color.PINK;
            case "red": return Color.RED;
            case "white": return Color.WHITE;
            case "yellow": return Color.YELLOW;
                
            default: throw new ParseException("Invalid color name");
        }
    }
    
    private void setColourGradient(String colours) throws ParseException
    {
        String colour1 = ""; 
        String colour2 = ""; 
        
        StringTokenizer tokenizer = new StringTokenizer(colours);
        
        colour1 = getString(tokenizer);
        colour2 = getString(tokenizer);
        
        image.setColourGradient(getColour(colour1), getColour(colour2));
    }
    
    /** Extra Features **/
    private void fillOval(String args) throws ParseException 
    {
        double x = 0.0; 
        double y = 0.0; 
        double width = 0.0; 
        double height = 0.0; 
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        x = getDouble(tokenizer);
        y = getDouble(tokenizer);
        width = getDouble(tokenizer);
        height = getDouble(tokenizer);
        
        image.fillOval(x, y, width, height);
    }

    /** TurtleGraphics Features **/
    private void setPosition(String args) throws ParseException
    {
       int x = 0; 
       int y = 0; 
       
       StringTokenizer tokenizer = new StringTokenizer(args);
       
       x = getInteger(tokenizer);
       y = getInteger(tokenizer);
      
       image.getTurtleMode().setPosition(x, y);
    }

    private void moveForward(String args) throws ParseException
    {
        int distance = 0; 
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        distance = getInteger(tokenizer);
        
        image.getTurtleMode().moveForward(distance);        
    }

    private void turnLeft(String args) throws ParseException
    {
        int angle = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        angle = getInteger(tokenizer);
        
        image.getTurtleMode().turnLeft(angle);    
    }

    private void turnRight(String args) throws ParseException
    {
        int angle = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        angle = getInteger(tokenizer);
        
        image.getTurtleMode().turnRight(angle);    
    }
    
    /** StringTokenizer Features **/
    private int getInteger(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens()) 
            return Integer.parseInt(tokenizer.nextToken());
        else 
            throw new ParseException("Missing Integer value");
    }
    
    private String getString(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens()) 
            return tokenizer.nextToken();
        else 
            throw new ParseException("Missing String value");
    }

    private double getDouble(StringTokenizer tokenizer) 
    {
        if(tokenizer.hasMoreTokens()) 
            return Double.parseDouble(tokenizer.nextToken());
        else
            throw new UnsupportedOperationException("Missing Double Value");
    }
}
