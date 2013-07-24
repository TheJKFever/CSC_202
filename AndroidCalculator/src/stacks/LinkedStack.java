package stacks;

public class LinkedStack<T> implements UnboundedStackInterface<T>
{
  protected LLNode<T> top;   // reference to the top of this stack
  protected int length;
  
  public LinkedStack()
  {
    top = null;
    length = 0;
  }

  public void push(T element)
  // Places element at the top of this stack.
  { 
    LLNode<T> newNode = new LLNode<T>(element);
    newNode.setLink(top);
    top = newNode;
    length+=1;
  }     

  public void pop()
  // Throws StackUnderflowException if this stack is empty,
  // otherwise removes top element from this stack.
  {                  
    if (!isEmpty())
    {
      top = top.getLink();
      length-=1;
    }
    else
      throw new StackUnderflowException("Pop attempted on an empty stack.");
  }

  public T top()
  // Throws StackUnderflowException if this stack is empty,
  // otherwise returns top element from this stack.
  {                 
    if (!isEmpty())
      return top.getInfo();
    else
      throw new StackUnderflowException("Top attempted on an empty stack.");
  }

  public boolean isEmpty()
  // Returns true if this stack is empty, otherwise returns false.
  {              
    if (top == null) 
      return true;
    else
      return false;
  }
  
  public int getLength(){
	  return length;
  }
  
	public String toString(){
		String result="";
		int printlength = length;
		for (int i=0;i<printlength;i++){
			result=" "+top()+result;
			pop();
		}	
		return result;
	}
}

