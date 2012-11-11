package drawapptest;

import java.io.InputStreamReader;
import java.io.Reader;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class MainWindow extends Application
{ 
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 300;
    
    private int width; 
    private int height; 
    
    private ImagePanel imagePanel;
    private TextArea messageView;
    private Button quitButton;
    
    //Group root = new Group();
    //Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    HBox hbox;// spacing = 20
     Scene scene;
    
    private void buildGUI()
    {
        hbox = new HBox(5);
        scene = new Scene(hbox, 500, 300);
        hbox.setTranslateX(10);
        hbox.setTranslateY(6);
        
        SplitPane textAreaSP = new SplitPane();
        textAreaSP.setOrientation(Orientation.VERTICAL);
        textAreaSP.setPrefSize(DEFAULT_WIDTH, 30);
        textAreaSP.setLayoutX(0);
        textAreaSP.setLayoutY(DEFAULT_HEIGHT-60);
        
        SplitPane splitPane1 = new SplitPane();
        splitPane1.setOrientation(Orientation.VERTICAL);
        splitPane1.setPrefSize(DEFAULT_WIDTH, 30);
        splitPane1.setLayoutX(0);
        splitPane1.setLayoutY(DEFAULT_HEIGHT-30);
        
        messageView = new TextArea();
        quitButton = new Button("Quit Button");
        splitPane1.getItems().addAll(quitButton);
        textAreaSP.getItems().addAll(messageView);
        hbox.getChildren().addAll(splitPane1, textAreaSP);
 
        
        //
        

       /* for (int i = 0; i < 20; i++)
        {
            tile.getChildren().add(new ImageView(...));
        }*/
 
       // 
        
        /*stage.setScene(scene);
        */
        
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

              
          /*  ImagePanel imagePanel = getImagePanel();
            Reader reader = new InputStreamReader(System.in);
            Parser parser = new Parser(reader, imagePanel, this);
            parser.parse();*/
            
            primaryStage.show();
            //stage.setVisible(true);
	}
    
    public static void main(String[] args) {
		launch(args);
	}
}
    
    
