package ListPerformanceEvaluation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformanceEvaluation {
	
	private static void fillOutList(List<Integer> list, int amountOfElements) {
        for (int i = 0; i < amountOfElements; i++) {
            list.add(i);
        }
    }

    public static void addElementsToBeginning(List<Integer> list, int numberOfElementsToAdd) {
        for (int i = 0; i < numberOfElementsToAdd; i++) {
            list.add(0, Integer.MAX_VALUE);
        }
    }

    public static void addElementsToMiddle(List<Integer> list, int numberOfElementsToAdd) {
        int middleIndex = list.size() / 2;
        for (int i = 0; i < numberOfElementsToAdd; i++) {
            list.add(middleIndex, Integer.MAX_VALUE);
        }
    }

    public static void addElementsToEnd(List<Integer> list, int numberOfElementsToAdd) {
        for (int i = 0; i < numberOfElementsToAdd; i++) {
            list.add(Integer.MAX_VALUE);
        }
    }

    public static void removeElementsFromBeginning(List<Integer> list, int numberOfElementsToRemove) {
        for (int i = 0; i < numberOfElementsToRemove && !list.isEmpty(); i++) {
            list.remove(0);
        }
    }

    public static void removeElementsFromMiddle(List<Integer> list, int numberOfElementsToRemove) {
        for (int i = 0; i < numberOfElementsToRemove && !list.isEmpty(); i++) {
            int middleIndex = list.size() / 2;
            list.remove(middleIndex);
        }
    }

    public static void removeElementsFromEnd(List<Integer> list, int numberOfElementsToRemove) {
        for (int i = 0; i < numberOfElementsToRemove && !list.isEmpty(); i++) {
            list.remove(list.size() - 1);
        }
    }

    private static void measurePerformance(String operationName, List<Integer> list, Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        long duration = end - start;
        System.out.printf("%-35s : %10.3f ms%n", operationName, duration / 1_000_000.0);
    }

    public static void main(String[] args) {
        int initialSize = 1_000_000;
        int numberOfElements = 10_000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        fillOutList(arrayList, initialSize);
        fillOutList(linkedList, initialSize);

        System.out.println("=== ArrayList Performance ===");
        measurePerformance("Add to beginning", new ArrayList<>(arrayList),
                () -> addElementsToBeginning(new ArrayList<>(arrayList), numberOfElements));
        measurePerformance("Add to middle", new ArrayList<>(arrayList),
                () -> addElementsToMiddle(new ArrayList<>(arrayList), numberOfElements));
        measurePerformance("Add to end", new ArrayList<>(arrayList),
                () -> addElementsToEnd(new ArrayList<>(arrayList), numberOfElements));

        measurePerformance("Remove from beginning", new ArrayList<>(arrayList),
                () -> removeElementsFromBeginning(new ArrayList<>(arrayList), numberOfElements));
        measurePerformance("Remove from middle", new ArrayList<>(arrayList),
                () -> removeElementsFromMiddle(new ArrayList<>(arrayList), numberOfElements));
        measurePerformance("Remove from end", new ArrayList<>(arrayList),
                () -> removeElementsFromEnd(new ArrayList<>(arrayList), numberOfElements));

        System.out.println("\n=== LinkedList Performance ===");
        measurePerformance("Add to beginning", new LinkedList<>(linkedList),
                () -> addElementsToBeginning(new LinkedList<>(linkedList), numberOfElements));
        measurePerformance("Add to middle", new LinkedList<>(linkedList),
                () -> addElementsToMiddle(new LinkedList<>(linkedList), numberOfElements));
        measurePerformance("Add to end", new LinkedList<>(linkedList),
                () -> addElementsToEnd(new LinkedList<>(linkedList), numberOfElements));

        measurePerformance("Remove from beginning", new LinkedList<>(linkedList),
                () -> removeElementsFromBeginning(new LinkedList<>(linkedList), numberOfElements));
        measurePerformance("Remove from middle", new LinkedList<>(linkedList),
                () -> removeElementsFromMiddle(new LinkedList<>(linkedList), numberOfElements));
        measurePerformance("Remove from end", new LinkedList<>(linkedList),
                () -> removeElementsFromEnd(new LinkedList<>(linkedList), numberOfElements));
    }
	

}
