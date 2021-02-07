package by.tc.task04.client;

import by.tc.task04.client.print.impl.TextPrinter;
import by.tc.task04.entity.Text;
import by.tc.task04.exception.ClientException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient
{
    private static final String HOST = "127.0.0.1";
    private static int PORT = 8030;
    private static int NUMBER_OF_OPERATIONS = 15;

    public static void main(String[] args)
    {
        try
        {
            runClient();
        }
        catch (ClientException e)
        {
            e.printStackTrace();
        }
    }

    private static void runClient() throws ClientException
    {
        Socket socket = null;
        TextPrinter textPrinter = new TextPrinter(null);

        try
        {
            socket = new Socket(HOST, PORT);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            for(int i = 1; i <= NUMBER_OF_OPERATIONS; i++)
            {
                Text text = (Text)objectInputStream.readObject();

                textPrinter.setTextToPrint(text);
                System.out.println("Result of operation number " + i + ":");
                textPrinter.print();
            }

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        }
        catch(IOException e)
        {
            throw new ClientException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new ClientException(e);
        }
        finally
        {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
