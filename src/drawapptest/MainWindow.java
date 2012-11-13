package drawapptest;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


Random randomgenerator=new Random();
private ImagePanel imagePanel;
private Scene scene;
private TextArea messageView;
Canvas canvas;


Reader reader;
Parser parser;

BorderPane borderPane = new BorderPane();

private void buildGUI()  
{ 
     
     
    scene = new Scene(borderPane, 1000, 600);
    
    canvas = new Canvas(500, 300);
    
    messageView = new TextArea(); 
    messageView.setPrefRowCount(6);
    messageView.setEditable(false);
    
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(messageView);
    Button quitButton = new Button("Close Window");
  
    quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();   
            }
        });
    
    Button everything = new Button("RunEverything");
    
   //Every time this button is called, parse is called 
    everything.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event)
        {
            
            parser.parse();
        }
    });
    
    Button stepByStep = new Button("stepByStep");
    
   //Every time this button is called, parse is called 
    stepByStep.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event)
        {
            parser.parseStepByStep();
        }
    });

    borderPane.setTop(canvas);
    borderPane.setCenter(scrollPane);
    borderPane.setLeft(stepByStep);
    borderPane.setBottom(quitButton);
    borderPane.setRight(everything);
    
    
    BorderPane.setAlignment(quitButton, Pos.BOTTOM_CENTER);
    BorderPane.setAlignment(stepByStep, Pos.BOTTOM_RIGHT);
    BorderPane.setAlignment(everything, Pos.BOTTOM_LEFT);
    
    BorderPane.setMargin(canvas, new Insets(50));
}

public ImagePanel getImagePanel()
{
return imagePanel;
}

public void postMessage(final String s)
{
messageView.setText(s);
}

@Override
public void start(Stage primaryStage) throws Exception {

buildGUI();
primaryStage.setResizable(false);
primaryStage.setScene(scene);
primaryStage.setTitle("DrawApp");


primaryStage.show();
reader = new InputStreamReader(System.in);
imagePanel = new ImagePanel(canvas);
parser = new Parser(reader, imagePanel, this);
//parser.parse();

//stage.setVisible(true);
}

public static void main(String[] args) {
launch(args);
}
}