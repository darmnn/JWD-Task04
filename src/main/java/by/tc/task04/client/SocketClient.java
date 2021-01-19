package by.tc.task04.client;

import by.tc.task04.entity.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketClient
{
    public static void main(String[] args)
    {
        Socket socket = null;

        try
        {
            socket = new Socket("127.0.0.1", 8030);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Text text = (Text)objectInputStream.readObject();

            System.out.println(text.toString());
            socket.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
