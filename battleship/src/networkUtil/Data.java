/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkUtil;

import java.io.Serializable;

/**
 *
 * @author Ahmed Nafis Fuad
 */
public class Data implements Serializable{
   public int row;
   public int col;
   public boolean hit=true;

    public Data(int row, int col) {
        this.row = row;
        this.col = col;
    }
   
   
}
