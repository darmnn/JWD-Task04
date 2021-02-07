package by.tc.task04.exception;

public class ClientException extends Exception
{
    public ClientException()
    {

    }

    public ClientException(Exception ex)
    {
        super(ex);
    }

    public ClientException(String message)
    {
        super(message);
    }
}
