package itacatemy.com.nikolaichik.run;

public class SimpleLinkedList<E> {

  private int size = 0;

  private Node<E> first = null;

  private Node<E> last = null;

  public SimpleLinkedList() {
  }

  public boolean add(E e) {
    addLast(e);
    return true;
  }

  public void remove(int index) {
    checkIndex(index);
    Node<E> nodeToRemove = getNode(index);
    Node<E> nextNode = nodeToRemove.next;
    Node<E> previousNode = nodeToRemove.previous;
    previousNode.next = nextNode;
    previousNode.previous = previousNode;
    size--;
  }

  public boolean add(int index, E e) {
    checkIndex(index);
    if (index < size) {
      addInto(index, e);
    }
    return true;
  }

  private void addInto(int index, E e) {
    Node<E> next = getNode(index);
    Node<E> previous = next.previous;
    Node<E> newNode = new Node<>(previous, e, next);
    next.previous = newNode;
    previous.next = newNode;
    size++;
  }

  private void addLast(E e) {
    Node<E> temp = last;
    this.last = new Node(temp, e, null);
    if (first == null) {
      first = last;
    }
    else {
      temp.next = last;
    }
    size++;
  }

  public E get(int index) {
    int count = 0;
    Node<E> node = first;
    while (count != index) {
      node = node.next;
      count++;
    }
    return node.e;
  }

  public Node<E> getNode(int index) {
    Node<E> node = first;
    while (index != 0) {
      node = node.next;
      index--;
    }
    return node;
  }

  private class Node<E> {
    E e;

    Node<E> previous;

    Node<E> next;

    public Node(Node<E> previousE, E e, Node<E> next) {
      this.previous = previousE;
      this.e = e;
      this.next = next;
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private void checkIndex(int index) {
    if (index > size) {
      throw new IndexOutOfBoundsException();
    }
  }
}
