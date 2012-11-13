package drawapptesting;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MainWindow extends Application
{ 
    
Group drawing=new Group();   

   private Scene scene;
    private TextArea messageView;
  
    private Canvas image;
    private GraphicsContext gc;
    Stage primarStage; 
  
    
     private void init(Stage primaryStage ){

        primaryStage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        scene = new Scene(borderPane, 500, 300);
        
        borderPane.setTop(drawing);
        
        messageView = new TextArea();     
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(messageView);
        Button quitButton = new Button("Close Window");
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(quitButton);
        BorderPane.setAlignment(quitButton, Pos.CENTER);
        BorderPane.setMargin(drawing, new Insets(100));
        
        
      image=new Canvas(500,500);
        drawing.getChildren().add(image);
         gc =image.getGraphicsContext2D();
    
         gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
    
        primaryStage.setScene(scene);
        primaryStage.setTitle("DrawApp");
      
    }
       
        
    
    
    public void postMessage(final String s)
    {
        messageView.setText(s);
    }
    
    protected void paintComponent(GraphicsContext gc)
    {
        gc.fillRect(0, 0, image.getWidth(), image.getHeight());   
    }
    
    public void setBackgroundColour(Color colour)
    {
       gc.fillRect(0, 0, image.getWidth(), image.getHeight());   
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
     
     public static void main(String[] args) {
		launch(args);
	}
    
 
	public void start(Stage primaryStage) throws Exception {
       init(primaryStage);                  
       Reader reader = new InputStreamReader(System.in);
       Parser parser = new Parser(reader,this);
       parser.parse();
       primaryStage.show();
           
	}
    
   
}
    
    
