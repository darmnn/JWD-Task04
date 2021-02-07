package by.tc.task04.server;

import by.tc.task04.entity.Text;
import by.tc.task04.exception.ServerException;
import by.tc.task04.server.parse.impl.FileParser;
import by.tc.task04.server.service.TextOperatorService;
import by.tc.task04.server.text_operation.TextOperator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer
{
    private static final int PORT = 8030;

    public static void main(String[] args)
    {
        try
        {
            runServer();
        }
        catch (ServerException ex)
        {
            ex.printStackTrace();
        }
    }

    private static void runServer() throws ServerException
    {
        FileParser fileParser = new FileParser();

        try
        {
            Text text = fileParser.parseFileToText();
            if(text.getContent().isEmpty())
                throw new ServerException("Error : file is empty!");

            TextOperator textOperator = new TextOperator(text);
            TextOperatorService textOperatorService= new TextOperatorService(textOperator);
            ArrayList<Text> processedTexts = textOperatorService.getProcessedTexts();

            Socket socket = null;

            try
            {
                ServerSocket server = new ServerSocket(PORT);
                socket = server.accept();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                for(Text processedText : processedTexts)
                    objectOutputStream.writeObject(processedText);

                objectOutputStream.close();
                if(socket != null) socket.close();
            }
            catch (IOException e)
            {
                throw new ServerException(e);
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
        catch (FileNotFoundException ex)
        {
            throw new ServerException(ex);
        }
        catch (IOException ex)
        {
            throw new ServerException(ex);
        }
    }
}
