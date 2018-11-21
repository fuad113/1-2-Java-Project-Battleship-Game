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
public class Board implements Serializable{
    
    int [][]arr =new int[10][10];
    int blockcount=15;
    
    public Board(int[][]arr)
    {
     this.arr=arr;
     
    }
    
    public boolean isboardclear()
   {
       if(blockcount>0)
           return false;
        return true;
  
   }
    
    public boolean isshipthere(int row,int col)
    {
        if(arr[row][col] > 0)
           return true;
        else
            return false;
      
    }
            
    
}
