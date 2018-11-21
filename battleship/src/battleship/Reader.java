/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import networkUtil.Data;
import networkUtil.NetworkConnection;

/**
 *
 * @author Ahmed Nafis Fuad
 */
public class Reader extends Thread
{

    NetworkConnection server;
    
    Reader(NetworkConnection server,Stage stage)
    {
        this.server = server;
    }

    public void run()
    {
        server.read();
        System.out.println("hi");
        
        
        Platform.runLater(() ->
                
        {
        gamescreen.lb.setText("");
    
        });
        
        while (true)
        {
            Object obj;
            obj = server.read();
            if (obj instanceof Boolean)
            {
                gamescreen.turn = true;
                Data d = (Data) server.read();
                if (d.hit)
                {
                    Platform.runLater(()
                            ->
                    {
                        gamescreen.cell[d.row][d.col].setFill(Color.RED);
                    });
                } else
                {
                    Platform.runLater(()
                            ->
                    {
                        gamescreen.cell[d.row][d.col].setFill(Color.BLANCHEDALMOND);
                    });
                }
            } 
            else
            {
                Data d = (Data) obj;
                if (d.row != -1)
                {
                    myrectangle r = myrectangle.getrect(gamescreen.player.getChildren(), d.row, d.col);
                    if (d.hit)
                    {
                        Platform.runLater(()
                                ->
                        {
                            r.setFill(Color.RED);
                        });
                    } else
                    {
                        Platform.runLater(()
                                ->
                        {
                            r.setFill(Color.BLANCHEDALMOND);
                        });
                    }
                } else
                {
                    if (d.hit)
                    {
                        System.out.println("you win");
                       
                        Platform.runLater(()->
                          {
                         
                              gamescreen.endlbl1.setText("You Win");
                          });
                        
                        
                      
                    } else
                    {
                        System.out.println("you loose");
                        
                            Platform.runLater(()->
                          {
                              
                           gamescreen.endlbl2.setText("You Lose");
                              
                          });
                        
                        
                  
                    }
                    break;
                }
            }
        }        
    }
}
