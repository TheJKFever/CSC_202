package infix;

@SuppressWarnings("serial")
public class InFixException extends RuntimeException
{
  public InFixException()
  {
    super();
  }

  public InFixException(String message)
  {
    super(message);
  }
}