package collections;

import java.util.PriorityQueue;
import java.util.Queue;

public class Queues {
    public static void main(String[] args) {
        //PriorityQueue, ArrayBlockingQueue and LinkedList are the implementations that are used most frequently.
        Queue<String> queue=new PriorityQueue<>();
        queue.add("test1");
        queue.add("test2");
        queue.add("test3");
        queue.poll();
        Object[] object=queue.toArray();
        for(Object o:object){
            System.out.print(o+" ");
        }
    }
}
