package itacatemy.com.nikolaichik.run;

import java.util.ListIterator;

public class MainApp {

  public static void main(String[] args) {
    SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<Integer>();
    simpleLinkedList.add(5);
    simpleLinkedList.add(9);
    simpleLinkedList.add(20);
    printList(simpleLinkedList);
    ListIterator iterator = simpleLinkedList.listIterator();
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
      if (iterator.hasPrevious()) {
        iterator.remove();
      }
    }
    System.out.println("\n================");
  }

  public static <E> void printList(SimpleLinkedList<E> simpleLinkedList) {
    for (int i = 0; i < simpleLinkedList.size(); i++) {
      System.out.print(simpleLinkedList.get(i) + " ");
    }
    System.out.println("\n============");
  }
}
