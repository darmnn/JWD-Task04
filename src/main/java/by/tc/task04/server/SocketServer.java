package by.tc.task04.server;

import by.tc.task04.entity.Text;
import by.tc.task04.exception.ServerException;
import by.tc.task04.server.parse.impl.FileParser;
import by.tc.task04.server.service.TextOperatorService;
import by.tc.task04.server.text_operation.TextOperator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer
{
    private static final int PORT = 8030;
    private static final int NUMBER_OF_OPERATIONS = 15;

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
            Text[] processedTexts = textOperatorService.getProcessedTexts();

            Socket socket = null;

            try
            {
                ServerSocket server = new ServerSocket(PORT);
                socket = server.accept();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                for(int i = 0; i < NUMBER_OF_OPERATIONS; i++)
                {
                    int numberOfOperation = (Integer)objectInputStream.readObject();
                    Text resultOfOperation;

                    switch (numberOfOperation)
                    {
                        case 4:
                        {
                            int wordLength = (Integer)objectInputStream.readObject();

                            resultOfOperation = textOperator.getWordsFromQuestions(wordLength);
                            objectOutputStream.writeObject(resultOfOperation);
                            break;
                        }
                        case 9:
                        {
                            char letter = (Character)objectInputStream.readObject();

                            resultOfOperation = textOperator.sortByLetterRatio(letter);
                            objectOutputStream.writeObject(resultOfOperation);
                            break;
                        }
                        case 10:
                        {
                            ArrayList<String> words = (ArrayList<String>)objectInputStream.readObject();

                            resultOfOperation = textOperator.sortWordsByOccurrence(words);
                            objectOutputStream.writeObject(resultOfOperation);
                            break;
                        }
                        case 11:
                        {
                            String firstWord = (String)objectInputStream.readObject();
                            String lastWord = (String)objectInputStream.readObject();

                            resultOfOperation = textOperator.deleteSubstring(firstWord, lastWord);
                            objectOutputStream.writeObject(resultOfOperation);
                            break;
                        }
                        case 12:
                        {
                            int wordLength = (Integer)objectInputStream.readObject();

                            resultOfOperation = textOperator.deleteWordsStartingWithConsonants(wordLength);
                            objectOutputStream.writeObject(resultOfOperation);
                            break;
                        }
                        case 13:
                        {
                            char letter = (Character)objectInputStream.readObject();

                            resultOfOperation = textOperator.sortByLetterRatio1(letter);
                            objectOutputStream.writeObject(resultOfOperation);
                            break;
                        }
                        case 15:
                        {
                            int wordLength = (Integer)objectInputStream.readObject();
                            String substringToChange = (String)objectInputStream.readObject();

                            resultOfOperation = textOperator.changeWordToSubstring(wordLength, substringToChange);
                            objectOutputStream.writeObject(resultOfOperation);
                            break;
                        }
                        default:
                        {
                            objectOutputStream.writeObject(processedTexts[i]);
                        }
                    }
                }

                objectInputStream.close();
                objectOutputStream.close();
                if(socket != null) socket.close();
            }
            catch (IOException e)
            {
                throw new ServerException(e);
            }
            catch (ClassNotFoundException e)
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
