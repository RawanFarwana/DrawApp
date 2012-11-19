package drawapp;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.imageio.ImageIO;

public class MainWindow extends Application
{
    private Canvas canvas;
    private Reader reader;
    private Parser parser;
    
    private Scene scene;
    private ImagePanel imagePanel;
    private TextArea errorMessage;
    
    private int width = 500; 
    private int height = 300;
    
    BorderPane borderPane = new BorderPane();
    ScrollPane canvasSP;

    private int counter = 0; //Initiliases counter for the image file name

    public void setWidth(int width)
    {
        if(width > 100)
        { 
            //this.width = width;
            canvas.setWidth(width);
        }
        else errorMessage.setText("Your width is too small");
    }
    
    public void setHeight(int height)
    {
        if(height > 100)
        {
            //this.height = height;
            canvas.setHeight(height);
        }
        else errorMessage.setText("Your height is too small");
    }
    
    private void buildGUI()  
    {
        scene = new Scene(borderPane, 1000, 700);
        
        //canvas = new Canvas(width,height); //Default Canvas size
        
        canvas = new Canvas(500,300); //Default Canvas size
        
        canvasSP = new ScrollPane();
        //ScrollPane canvasSP = new ScrollPane();
        canvasSP.setContent(canvas); //Adds the Canvas to ScrollPane (for when canvas is enlarged)
        
        errorMessage = new TextArea(); 
        errorMessage.setPrefRowCount(6);
        errorMessage.setEditable(false);
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(errorMessage);
        
        //Creates a button that closes the window 
        Button quitBtn = new Button("Close Window");
        quitBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Platform.exit();   
            }
        });
        
        //Create a button that when pressed executes all of the drawing
        Button runAllBtn = new Button("Run All");
        runAllBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                parser.parse();
            }
        });
        
        //Creates a button that when pressed executes the drawing step by step
        Button runStepByStepBtn = new Button("Run Step by Step");
        runStepByStepBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                parser.parseStepByStep();
            }
        });
        
        //Creates a button that when pressed takes a snapshot of the canvas and saves it to CCode folder
        Image camera = new Image(getClass().getResourceAsStream("camera.png"));
        Button takeSnapshotBtn = new Button();
        takeSnapshotBtn.setGraphic(new ImageView(camera));
        takeSnapshotBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try 
                {
                    ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(null, null), null),
                            "png",new File("Image"+counter));
                    counter++; //Increments counter...so next time btn pressed it has a different count
                    errorMessage.setText("Your drawing was successfully saved.");
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                    errorMessage.setText("Your drawing could not be saved.");
                }
            }
        });
        
        HBox hbox = new HBox(4);
        hbox.setAlignment(Pos.CENTER);
        //Add all the buttons to this HBox
        hbox.getChildren().addAll(takeSnapshotBtn, runAllBtn, runStepByStepBtn, quitBtn);
        
        FlowPane flow = new FlowPane();
        flow.setMaxHeight(200);
        flow.setAlignment(Pos.CENTER);
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setOrientation(Orientation.VERTICAL);
        flow.getChildren().addAll(scrollPane, hbox); //Add both buttons and errorMessage scrollPane to FlowPane
     
        borderPane.setCenter(canvasSP);   
        borderPane.setBottom(flow);
    }
    
    public ImagePanel getImagePanel()
    {
        return imagePanel;
    }
    
    public void postMessage(final String s)
    {
        errorMessage.setText(s);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        buildGUI();
        primaryStage.setTitle("DrawApp");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        reader = new InputStreamReader(System.in);
        imagePanel = new ImagePanel(canvas);
        parser = new Parser(reader, imagePanel, this);
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}