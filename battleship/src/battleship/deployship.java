/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Ahmed Nafis Fuad
 */
public class deployship implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {

        myrectangle myrect = (myrectangle) event.getSource();

        int row = GridPane.getRowIndex(myrect);
        int col = GridPane.getColumnIndex(myrect);
        GridPane gp = (GridPane) myrect.getParent();
        int times = myrect.state;
        int tr = myrect.row;
        int tc = myrect.col;
        
        if (ships.x != -1) {

            if (isplacable(gp, row, col, ships.x + 1)) {

                Battleship.shps[ships.x].allocation = true;

                for (int i = 0; i < ships.x + 1; i++) {
                    myrect = myrectangle.getrect(gp.getChildren(), row + i, col);
                    myrect.setFill(Color.GREEN);
                    myrect.state = ships.x + 1;
                    myrect.row = row;
                    myrect.col = col;

                }

                removefromport(ships.x);
                ships.reset();
            }
        } else if (myrect.state > 0) {
            if (event.getButton().equals(MouseButton.SECONDARY)) {

                if (Battleship.shps[times - 1].allocation == true) {
                    for (int j = 0; j < times; j++) {
                        myrectangle r2 = myrectangle.getrect(gp.getChildren(), tr + j, tc);
                        r2.state = 0;
                        r2.row = -1;
                        r2.col = -1;
                        r2.setFill(Color.GRAY);

                    }

                } else {

                    for (int j = 0; j < times; j++) {
                        myrectangle r2 = myrectangle.getrect(gp.getChildren(), tr, tc + j);
                        r2.state = 0;
                        r2.row = -1;
                        r2.col = -1;
                        r2.setFill(Color.GRAY);

                    }

                }

                returntobase(times - 1);

            } else {

                if (col + times <= 10 && isrotatable(gp, tr, tc, times)) {

                    for (int j = 1; j < times; j++) {

                        myrectangle rect = myrectangle.getrect(gp.getChildren(), tr, tc + j);
                        myrectangle rect2 = myrectangle.getrect(gp.getChildren(), tr + j, tc);
                        gp.getChildren().remove(rect);
                        gp.getChildren().remove(rect2);
                        gp.add(rect, tc, tr + j);
                        gp.add(rect2, tc + j, tr);

                    }

                    Battleship.shps[times - 1].allocation = !Battleship.shps[times - 1].allocation;

                }

            }

        }
    }

    boolean isplacable(GridPane gp, int r, int col, int size) {

        if ((r + size) > 10) {
            return false;

        }

        for (int i = 0; i < size; i++) {
            myrectangle myrect = myrectangle.getrect(gp.getChildren(), r + i, col);
            if (myrect.state != 0 && myrect.state != size) {
                return false;
            }

        }
        return true;

    }
    
           
                 
                              
    
  
    void removefromport(int x) {

        ships ship = Battleship.shps[x];
        GridPane gp = (GridPane) ship.getParent();
        gp.getChildren().remove(ship);
    }

    void returntobase(int x) {                //this is for type 1(when ships are verticle)
        ships ship = new ships(30, x);
        Battleship.shps[x] = ship;
        Battleship.shipsport.add(ship, x, 0);
        GridPane.setValignment(ship, VPos.BOTTOM);

    }

    boolean isrotatable(GridPane gp, int r, int col, int size) {

        if ((col + size) > 10) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            myrectangle myrect = myrectangle.getrect(gp.getChildren(), r , col+i);
            
            if (myrect.state != 0 && myrect.state != size) {
                return false;
            }

        }
        return true;
    

    }

}
