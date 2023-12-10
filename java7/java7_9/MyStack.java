package java7_9;
import java.util.Vector;

class MyStack<T> implements IStack<T> {
  private Vector<T> v;
  MyStack() { v = new Vector<T>();}
  
  @Override
  public T pop() {
    if (v.size() == 0) return null;
    else return v.remove(0);
  }

  @Override
  public boolean push(T ob) {
    v.add(0,ob);
    return true;
  } 
}