package itacatemy.com.nikolaichik.run;

public class MainApp {

    public static void main(String[] args) {
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<Integer>();
        simpleLinkedList.add(5);
        simpleLinkedList.add(9);
        simpleLinkedList.add(20);
        simpleLinkedList.add(2, 4);
        printList(simpleLinkedList);
        simpleLinkedList.remove(2);
        printList(simpleLinkedList);
    }
    
    public static <E> void printList(SimpleLinkedList<E> simpleLinkedList) {
      for (int i = 0; i < simpleLinkedList.size(); i++) {
        System.out.print(simpleLinkedList.get(i) + " ");
      }
      System.out.println("\n============");
    }
}
