/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;



/**
 *
 * @author Ahmed Nafis Fuad
 */
public class myrectangle extends Rectangle {
    
    int row;
    int col;
    int state=0;

    public myrectangle() {
        this.row = -1;
        this.col = -1;
        
    }
    
    
    static myrectangle getrect(ObservableList<Node> oln,int r,int c)
    {
        
        for(Node x:oln)
        {
            if(GridPane.getRowIndex(x)==r && GridPane.getColumnIndex(x)==c )
                return (myrectangle)x;
        }
        return null;
            
    }
    

    
}
