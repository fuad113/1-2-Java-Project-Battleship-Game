/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import networkUtil.Data;
import networkUtil.NetworkConnection;

/**
 *
 * @author Ahmed Nafis Fuad
 */
public class gamescreen {

    int[][] arr = new int[10][10];
    static boolean turn = false;
    boolean[][] isBombed = new boolean[10][10];
    static NetworkConnection server;
    static GridPane player;
    static  myrectangle[][] cell = new myrectangle[10][10];
    static Label lb=new Label("Waiting for 2nd player.....");
    static Label endlbl1=new Label("");
    static Label endlbl2=new Label("");
    
        public gamescreen(Stage stage, GridPane gp,NetworkConnection server) {
        gamestart(stage, gp);
        int count = 0;
        this.server=server;
        player=gp;
        new Reader(server,stage).start();
        while (count < 5) {
            int row = (int) (Math.random() * 10);
            int col = (int) (Math.random() * 10);
            int orient = (int) (Math.random() * 10);
            if (orient > 5) {
                orient = 1;
            } else {
                orient = 0;
            }
            if (isPlacable(row, col, count + 1, orient)) {
                place(row, col, count + 1, orient);
                count++;
            }
        }

    }

    void place(int row, int col, int size, int orient) {
        for (int i = 0; i < size; i++) {
            if (orient == 0) {
                arr[row + i][col] = size;
            } 
            
            else {
                arr[row][col + i] = size;
            }
        }
    }

    boolean isPlacable(int row, int col, int size, int orient) {
        if (orient == 0) {
            if (row + size > 10) {
                return false;
            }
        } 
        
        else {
            if (col + size > 10) {
                return false;
            }
        }

        int var;
        for (int i = 0; i < size; i++) {
            if (orient == 0) {
                var = arr[row + i][col];
            } 
            
            else {
                var = arr[row][col + i];
            }
            if (var > 0) {
                return false;
            }
        }
        return true;
    }

    void gamestart(Stage stage, GridPane player) {

        GridPane opponent = new GridPane();
        Group root = new Group();

       

        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                cell[i][j] = new myrectangle();
                cell[i][j].setWidth(30);
                cell[i][j].setHeight(30);
                cell[i][j].setFill(Color.GREY);
                cell[i][j].setStroke(Color.BLACK);
                opponent.add(cell[i][j], j, i);
                myrectangle rect = myrectangle.getrect(player.getChildren(), i, j);

                rect.setOnMouseClicked(null);

                cell[i][j].setOnMouseClicked(e-> {
                   myrectangle r = (myrectangle) e.getSource();
                    int row = GridPane.getRowIndex(r);
                    int col = GridPane.getColumnIndex(r);
                    Data data=new Data(row, col);
                    if (turn) 
                    {                      
                        if(!isBombed[row][col])
                        {
                            server.write(data);
                            isBombed[row][col]=true;
                            turn=false;
                        }
                    }
                });

            }

        }
         
        
        lb.setFont(Font.font("Cambria", 35));
        lb.setLayoutX(0);
        lb.setLayoutY(0);
        root.getChildren().add(lb);
        
        endlbl1.setFont(Font.font("Cambria", 35));
        endlbl1.setLayoutX(375);
        endlbl1.setLayoutY(0);
        root.getChildren().add(endlbl1);
        
        
        endlbl2.setFont(Font.font("Cambria", 35));
        endlbl2.setLayoutX(375);
        endlbl2.setLayoutY(0);
        root.getChildren().add(endlbl2);
        
        
        HBox hb = new HBox();
        hb.getChildren().addAll(player, opponent);
        hb.setSpacing(30);
        
        hb.setTranslateX(80);
        hb.setTranslateY(100);
        

        root.getChildren().add(hb);

        Scene scene = new Scene(root, 800, 500);

        stage.setScene(scene);

    }

}
