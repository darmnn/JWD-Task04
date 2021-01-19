package by.tc.task04.server;

import by.tc.task04.entity.Text;
import by.tc.task04.server.parse.impl.FileParser;
import by.tc.task04.server.parse.impl.TextParser;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocketServer
{
    public static void main(String[] args)
    {
        FileParser fileParser = new FileParser("src\\main\\resources\\C++ guide.txt");
        Text text = fileParser.parseFileToText();

        TextParser textParser = new TextParser(text);
        Text textWithRepetitiveWords = textParser.getSentencesWithRepetitiveWords();

        Socket socket = null;
        try
        {
            ServerSocket server = new ServerSocket(8030);
            socket = server.accept();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(textWithRepetitiveWords);
            objectOutputStream.close();

            if(socket != null) socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
