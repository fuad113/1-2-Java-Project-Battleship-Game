/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkConnection {
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Board b;
    
    public NetworkConnection(Socket sock) throws IOException{
        socket=sock;
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
    }
    
    public NetworkConnection(String ip,int port) throws IOException{
        socket=new Socket(ip, port);
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
    }
    
    public void write(Object obj){
        try {
            oos.writeObject(obj);
        } catch (IOException ex) {
            System.out.println("Failed to write");
            //throw ex;
        }
    }
    
    public Object read(){
        Object obj;
        try {
            obj = ois.readObject();
        } catch (Exception ex) {
            System.out.println("Failed to read");
            return null;
        }
        return obj;
    }
    
    public void getboard()
    {
       b=(Board)read();  
        
    }
    
    public boolean isboardclear()
    {
        return b.isboardclear();
        
    }
         
    
    public boolean isfound(int row,int col)
    {
        
        return b.isshipthere(row, col);
        
        
    }
    public void decrement()
    {
        b.blockcount--;
    }
}
