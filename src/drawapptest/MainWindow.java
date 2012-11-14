package drawapptest;

import java.io.InputStreamReader;
import java.io.Reader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MainWindow extends Application
{
    private Scene scene;
    private ImagePanel imagePanel;
    private TextArea errorMessage;
    
    private int width = 500; 
    private int height = 300; 
    
    Canvas canvas;
    Reader reader;
    Parser parser;
    
    BorderPane borderPane = new BorderPane();
    
    private void buildGUI()  
    {
        scene = new Scene(borderPane, 1000, 600);
        canvas = new Canvas(width, height);
        
        errorMessage = new TextArea();
        errorMessage.setPrefRowCount(6);
        errorMessage.setEditable(false);
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(errorMessage);
        
        Button quitBtn = new Button("Close Window");
        quitBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Platform.exit();   
            }
        });
        
        Button runAllBtn = new Button("Run All");
        runAllBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                parser.parse();
            }
        });
        
        Button runStepByStepBtn = new Button("Run Step by Step");
        runStepByStepBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                parser.parseStepByStep();
            }
        });
        
        borderPane.setTop(canvas);
        borderPane.setCenter(scrollPane);
        borderPane.setLeft(runStepByStepBtn);
        borderPane.setBottom(quitBtn);
        borderPane.setRight(runAllBtn);
        
        BorderPane.setAlignment(quitBtn, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(runStepByStepBtn, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(runAllBtn, Pos.BOTTOM_LEFT);
        
        BorderPane.setMargin(canvas, new Insets(50));
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