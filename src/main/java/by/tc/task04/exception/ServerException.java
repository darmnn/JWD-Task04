package by.tc.task04.exception;

public class ServerException extends Exception
{
    public ServerException()
    {

    }

    public ServerException(Exception ex)
    {
        super(ex);
    }

    public ServerException(String message)
    {
        super(message);
    }
}
