package itacatemy.com.nikolaichik.run;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

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

  private Node<E> getNode(int index) {
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

  public ListIterator listIterator() {
    rangeCheckForAdd(0);
    return listIterator(0);
  }

  public ListIterator listIterator(final int index) {
    rangeCheckForAdd(index);
    return new ListIterator(index);
  }

  private void rangeCheckForAdd(int index) {
    if (index < 0 || index > size())
      throw new IndexOutOfBoundsException();
  }

  private class ListIterator implements java.util.ListIterator<E> {

    private int cursor;

    private int actualSize;

    public ListIterator(int position) {
      this.cursor = position;
      this.actualSize = size();
    }

    @Override
    public boolean hasNext() {
      return cursor < size();
    }

    @Override
    public E next() {
      checkModification();
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return get(cursor++);
    }

    @Override
    public boolean hasPrevious() {
      return cursor > 0;
    }

    @Override
    public E previous() {
      checkModification();
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return get(--cursor);
    }

    @Override
    public int nextIndex() {
      return cursor + 1;
    }

    @Override
    public int previousIndex() {
      return cursor - 1;
    }

    @Override
    public void remove() {
      checkModification();
      SimpleLinkedList.this.remove(cursor);
    }

    @Override
    public void set(E e) {
      checkModification();
      getNode(cursor).e = e;
    }

    @Override
    public void add(E e) {
      checkModification();
      SimpleLinkedList.this.add(cursor, e);
    }

    private void checkModification() {
      if (actualSize != size()) {
        throw new ConcurrentModificationException();
      }
    }
  }
}
