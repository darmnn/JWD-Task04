package by.tc.task04.client;

import by.tc.task04.client.print.impl.TextPrinter;
import by.tc.task04.entity.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketClient
{
    private static final String HOST = "127.0.0.1";
    private static int PORT = 8030;
    private static int NUMBER_OF_OPERATIONS = 7;

    public static void main(String[] args)
    {
        Socket socket = null;
        TextPrinter textPrinter = new TextPrinter(null);

        try
        {
            socket = new Socket(HOST, PORT);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            for(int i = 1; i <= NUMBER_OF_OPERATIONS; i++)
            {
                Text text = (Text)objectInputStream.readObject();

                textPrinter.setTextToPrint(text);
                System.out.println("Result of operation number " + i + ":");
                textPrinter.print();
            }

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
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
