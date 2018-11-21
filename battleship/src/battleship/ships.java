package battleship;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Ahmed Nafis Fuad
 */
public class ships  extends Rectangle {
    
    
    int shipno;
    static  int x=-1;   //this for not changing the colour of the second ship 
    boolean allocation=true;
    public ships(int edge,int shipno) {
        
        this.shipno=shipno;
        
        this.setHeight((shipno+1)*edge);
        this.setWidth(edge);                         //here edge is used for length of the ship
        this.setFill(Color.GREEN);
        
        this.setOnMouseClicked(new EventHandler<MouseEvent> (){
            @Override
            public void handle(MouseEvent event) {
              
                
              if(x!=-1)
                  reset();
                
              ships rect=(ships) event.getSource();
              x=rect.shipno;
              rect.setFill(Color.AQUA);
 
            
            }
                
                
        });
        
    }
        static void reset()
        {
            Battleship.shps[x].setFill(Color.GREEN);
            x=-1;
        }

        
        
 }
    
    
    
    
    

