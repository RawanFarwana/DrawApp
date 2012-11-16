package drawapp;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class MainWindow extends Application
{
    private Scene scene;
    private ImagePanel imagePanel;
    private TextArea errorMessage;
    
    Canvas canvas;
    Reader reader;
    Parser parser;
    
    private int width; 
    private int height;
    
    BorderPane borderPane = new BorderPane();
    
    private void buildGUI()  
    {
        scene = new Scene(borderPane, 1000, 600);
       
        canvas = new Canvas(500,300);
        
        ScrollPane canvasSP = new ScrollPane();
        canvasSP.setContent(canvas);
        
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
        
        //Image camera = new Image(getClass().getResourceAsStream("camera.png"));
        Button takeSnapshot = new Button("Camera");
        //takeSnapshot.setGraphic(new ImageView(camera));
        takeSnapshot.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
               
                /*WritableImage writableImage = new WritableImage(width, height);
                writableImage = canvas.snapshot(null, writableImage);*/
        
            }
        });
        
        HBox hbox = new HBox(2); 
        hbox.setAlignment(Pos.CENTER);
          hbox.getChildren().addAll(quitBtn, runAllBtn, runStepByStepBtn);
        
          FlowPane flow = new FlowPane();
          flow.setMaxHeight(150);
          flow.setAlignment(Pos.CENTER);
          flow.setVgap(8);
          flow.setHgap(4);
          //flow.setPrefWrapLength(500); 
          flow.setOrientation(Orientation.VERTICAL);
          flow.getChildren().addAll(errorMessage, hbox);
          
        
        borderPane.setCenter(canvasSP);   
        borderPane.setBottom(flow);
        
        //BorderPane.setMargin(canvas, new Insets(50));
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