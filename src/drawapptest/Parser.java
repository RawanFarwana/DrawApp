package drawapptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

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
    
    public void parse()
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
        
        frame.postMessage("Drawing completed");
    }
    
    public int parseStepByStep()
    {
        try
        {
            String line = reader.readLine();
            
            if (line == null)
            {
                System.out.println("End of input reached");
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
        frame.postMessage("Step completed");
        return 0;
    }
    
    private void parseLine(String line) throws ParseException
    {
        if (line.length() < 2) return; 
        
        String command = line.substring(0, 2);
        
        if (command.equals("DL"))
        {
            System.out.println(line);
            drawLine(line.substring(2,line.length())); 
            return; 
        }
        
        if (command.equals("DR"))
        {
            System.out.println(line);
            drawRect(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("FR"))
        {
            System.out.println(line);
            fillRect(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("SC"))
        {
            System.out.println(line);
            setColour(line.substring(3, line.length()));
            return;
        }
        
        if (command.equals("DS"))
        {
            System.out.println(line);
            drawString(line.substring(3, line.length()));
            return;
        }
        
        if (command.equals("DA"))
        {
            System.out.println(line);
            drawArc(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("DO"))
        {
            System.out.println(line);
            drawOval(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("CG"))
        {
            System.out.println(line);
            setColourGradient(line.substring(3, line.length()));
            return;
        }
        
        if(command.equals("DI"))
        {
            System.out.println(line);
            drawImage(line.substring(2, line.length()));
            return;
        }
        
        if(command.equals("FO"))
        {
            System.out.println(line);
            fillOval(line.substring(2, line.length()));
            return;
        }
        
        throw new ParseException("Unknown drawing command"); 
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
        
        System.out.println("DL" + x1 + "" + y1 + "" + x2 + "" + y2);
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
        
        System.out.println("DR" + x1 + "" + y1 + "" + x2 + "" + y2);
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
        
        System.out.println("FR" + x1 + "" + y1 + "" + x2 + "" + y2);
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
        
        System.out.println("DA" + x + "" + y + "" + width + "" + height + "" + startAngle + "" + arcAngle);
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
        
        System.out.println("DO" + x1 + "" + y1 + "" + width + "" + height);
        image.drawOval(x1, y1, width, height);
    }
    
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
        
        System.out.println("FO" + x + "" + y + "" + width + "" + height);
        image.fillOval(x, y, width, height);
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
    
        System.out.println("DS" + x + "" + y + "" +s);
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
        pathWay = getString(tokenizer);
        
        int position = args.indexOf('@');
        if (position == -1) throw new ParseException("Image path is missing");
        pathWay = args.substring(position + 1);

        System.out.println("DI" + pathWay + "" + x + "" + y + "" +width+ "" + height);
        image.drawImage(pathWay, x, y, width, height);
    }
    
 
    
    private void setColour(String colourName) throws ParseException
    {
        System.out.println("SC" + colourName);
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
        
        System.out.println("CG" + colour1 + "" + colour2);
        image.setColourGradient(getColour(colour1), getColour(colour2));
    }
    
    private int getInteger(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens()) return Integer.parseInt(tokenizer.nextToken());
        else throw new ParseException("Missing Integer value");
    }
    
    private String getString(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens()) return tokenizer.nextToken();
        else throw new ParseException("Missing String value");
    }

    private double getDouble(StringTokenizer tokenizer) {
        if(tokenizer.hasMoreTokens()) return Double.parseDouble(tokenizer.nextToken());
        throw new UnsupportedOperationException("Missing Double Value");
    }
}
