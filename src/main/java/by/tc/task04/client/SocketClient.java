package by.tc.task04.client;

import by.tc.task04.client.print.impl.TextPrinter;
import by.tc.task04.entity.Text;
import by.tc.task04.exception.ClientException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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
                switch(i)
                {
                    case 4:
                    {
                        objectOutputStream.writeObject(i);
                        int wordLength = 5;
                        objectOutputStream.writeObject(wordLength);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();

                        break;
                    }
                    case 9:
                    {
                        objectOutputStream.writeObject(i);
                        char letter = 'а';
                        objectOutputStream.writeObject(letter);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();

                        break;
                    }
                    case 10:
                    {
                        objectOutputStream.writeObject(i);
                        ArrayList<String> words = new ArrayList<String>();
                        words.add("C++");
                        words.add("язык");
                        words.add("программист");
                        objectOutputStream.writeObject(words);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();

                        break;
                    }
                    case 11:
                    {
                        objectOutputStream.writeObject(i);
                        String firstWord = "Цель";
                        String lastWord = "дней";
                        objectOutputStream.writeObject(firstWord);
                        objectOutputStream.writeObject(lastWord);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();

                        break;
                    }
                    case 12:
                    {
                        objectOutputStream.writeObject(i);
                        int wordLength = 8;
                        objectOutputStream.writeObject(wordLength);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();

                        break;
                    }
                    case 13:
                    {
                        objectOutputStream.writeObject(i);
                        char letter = 'о';
                        objectOutputStream.writeObject(letter);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();

                        break;
                    }
                    case 15:
                    {
                        objectOutputStream.writeObject(i);
                        int wordLength = 6;
                        objectOutputStream.writeObject(wordLength);
                        String substringToChange = "замененная подстрока";
                        objectOutputStream.writeObject(substringToChange);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();

                        break;
                    }
                    default:
                    {
                        objectOutputStream.writeObject(i);

                        Text text = (Text)objectInputStream.readObject();
                        textPrinter.setTextToPrint(text);
                        System.out.println("Result of operation number " + i + ":");
                        textPrinter.print();
                    }
                }
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
