package battleship;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import networkUtil.Board;
import networkUtil.NetworkConnection;

/**
 *
 * @author Ahmed Nafis Fuad
 */
public class Battleship extends Application {
    
    static ships []shps = new ships[5];
    static GridPane shipsport=new GridPane();
    @Override
    public void start(Stage primaryStage) throws Exception{
        
         Group root=new Group();
         GridPane board=new GridPane();
         GridPane txt=new GridPane();
         
         shipsport.setHgap(2);
         
        
         myrectangle [][]cell=new myrectangle[10][10];
         
         
         Label ln=new Label("DEPLOY YOUR SHIPS HERE");
         ln.setFont(new Font(25));
         
         txt.getChildren().add(ln);
        
         
         for(int i=0;i<10;i++)
         {
             for(int j=0;j<10;j++)
             {
              cell[i][j]=new myrectangle();
              cell[i][j].setWidth(30);
              cell[i][j].setHeight(30);
              cell[i][j].setFill(Color.GREY);
              cell[i][j].setStroke(Color.BLACK);
              board.add(cell[i][j], j, i);
              cell[i][j].setOnMouseClicked(new deployship());
              
            }

         }
        
        
         
         
         Button btn=new Button("Ready");
         
         btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 try {
                     int[][] data=new int[10][10];
                     for(int i=0;i<10;i++)
                     {
                         for(int j=0;j<10;j++)
                         {
                             myrectangle r=myrectangle.getrect(board.getChildren(), i, j);
                             data[i][j]=r.state;
                         }
                     }
                     NetworkConnection server=new NetworkConnection("localhost",12345);
                     Board b=new Board(data);
                     server.write(b);
                     gamescreen gscrn=new gamescreen(primaryStage, board,server);
                 } catch (IOException ex) {
                     Logger.getLogger(Battleship.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
             }

            
         });
         
       
         
         HBox hb=new HBox();
         hb.getChildren().addAll(board);
         hb.getChildren().addAll(shipsport);
         hb.setSpacing(10);
         
         
         VBox vb=new VBox();
         vb.getChildren().addAll(txt,hb,btn);
         vb.setSpacing(10);
         vb.setTranslateX(30);
         vb.setTranslateY(10);
         
         
        
         
         
         
         for(int i=0;i<5;i++)
        {
             shps[i]=new ships(30,i);
             shipsport.add(shps[i], i, 0);
             GridPane.setValignment(shps[i], VPos.BOTTOM);
        }
         
         
         
         root.getChildren().add(vb);
         Scene scene =new Scene(root, 800, 500);
      
      
         root.setOnMouseClicked(e->
         {
             System.out.println("");
             for(int i=0;i<10;i++)
             {
                 for(int j=0;j<10;j++)
                 {
                     myrectangle temp=myrectangle.getrect(board.getChildren(), i, j);
                     System.out.print("("+temp.row+","+temp.col+")");
                 }
                 System.out.println("");
             }
         });
        
          
  
         
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
