/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import networkUtil.Data;
import networkUtil.NetworkConnection;

/**
 *
 * @author Ahmed Nafis Fuad
 */
public class ServerMain
{

    static NetworkConnection[] p = new NetworkConnection[2];
    static boolean turn = true;

    public static void main(String[] args) throws IOException
    {

        ServerSocket serversocket = new ServerSocket(12345);
        int count = 0;

        while (count < 2)
        {
            Socket socket = serversocket.accept();
            p[count] = new NetworkConnection(socket);
            p[count].getboard();
            count++;
        }
        NetworkConnection p1 = p[0], p2 = p[1];
        Boolean value = true;
        p1.write(value);
        p2.write(value);
        while (!p1.isboardclear() && !p2.isboardclear())
        {
            if (turn)
            {
                Boolean player = true;
                p1.write(player);
                Object obj = p1.read();
                Data d = (Data) obj;
                if (p2.isfound(d.row, d.col))
                {
                    d.hit = true;
                    p2.decrement();
                } else
                {
                    d.hit = false;
                    turn = false;
                }
                p1.write(d);
                p2.write(d);
            } else
            {
                Boolean player = true;
                p2.write(player);
                Data d = (Data) p2.read();
                if (p1.isfound(d.row, d.col))
                {
                    d.hit = true;
                    p1.decrement();
                } else
                {
                    d.hit = false;
                    turn = true;
                }
                p2.write(d);
                p1.write(d);
            }

        }

        Data won = new Data(-1, -1);
        won.hit = true;

        Data lost = new Data(-1, -1);
        lost.hit = false;

        if (p1.isboardclear())
        {
            p2.write(won);
            p1.write(lost);

        } else
        {
            p1.write(won);
            p2.write(lost);
        }

    }

}
