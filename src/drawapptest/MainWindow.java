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
Random randomgenerator=new Random();
private ImagePanel imagePanel;
private Scene scene;
private TextArea messageView;

BorderPane borderPane = new BorderPane();

private void buildGUI()
{ 

scene = new Scene(borderPane, 500, 300);
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

borderPane.setTop(drawing);
borderPane.setCenter(scrollPane);
borderPane.setBottom(quitButton);
BorderPane.setAlignment(quitButton, Pos.CENTER);
BorderPane.setMargin(drawing, new Insets(50));
}

public ImagePanel getImagePanel()
{
return imagePanel;
}

public void postMessage(final String s)
{
messageView.setText(s);
}


private Node petals() {

double R=randomgenerator.nextDouble();
double B=randomgenerator.nextDouble();
double G=randomgenerator.nextDouble();

Group petals = new Group();

int r=0;
for(int i=0; i<10;i++){
Ellipse petal = new Ellipse(150,30,10, 20);
petal.setStroke(Color.ORANGE);
petal.setFill(Color.color(R, G, B, 0.5D));
petal.getTransforms().add(new Rotate(r, 150, 50));

r+=35;

petals.getChildren().add(petal);
}
return petals;
}

private Node whorl() {

Circle whorl = new Circle(150,50,10);
whorl.setFill(Color.YELLOW);

return whorl;
} 

private Group Flower(){
Group root = new Group();

Node petals_node = petals();
Node whorl_node=whorl();

root.getChildren().add(petals_node);
root.getChildren().add(whorl_node);

return root;
}

@Override
public void start(Stage primaryStage) throws Exception {

buildGUI();
primaryStage.setResizable(false);
primaryStage.setScene(scene);
primaryStage.setTitle("DrawApp");

Group Fl=Flower();
Group Fl2=Flower();
Group Fl3=Flower();
Fl2.setTranslateX(150);
Fl3.setTranslateX(300);
drawing.getChildren().addAll(Fl,Fl2,Fl3);


/* Reader reader = new InputStreamReader(System.in);
Parser parser = new Parser(reader, imagePanel, this);
parser.parse();
*/
primaryStage.show();
//stage.setVisible(true);
}

public static void main(String[] args) {
launch(args);
}
}