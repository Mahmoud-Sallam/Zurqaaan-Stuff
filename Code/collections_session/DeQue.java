package collections_session;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DeQue {


    public static void main(String[] args) {

//        Deque<Integer> deque = new LinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>();

//        deque.push(10);
//        deque.push(20);
//        deque.push(30);

        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);

        System.out.println("deque.getLast() = " + deque.getLast());

        deque.removeIf(x->x.equals(10));

        System.out.println("deque.poll() = " + deque.poll());
        System.out.println("deque.poll() = " + deque.poll());
        System.out.println("deque.poll() = " + deque.poll());
        System.out.println("deque.poll() = " + deque.poll());


        Deque<Character> chars = new LinkedList<>();
        for (char c : "(())()())".toCharArray()) {
            if( c == '('){
                chars.push(c);
            }else{
                //c is ')'
                Character poll = chars.poll();
                System.out.print("poll = " + poll);
                System.out.println(" c is "+c);
                if(poll !='('){
                    throw new RuntimeException("Not valid");
                }
            }

        }
//
//         ")(())"
//                .codePoints()
//                .mapToObj(c -> (char) c)
//                .forEach(c->{
//
//                });
////                .peek(c -> {
////                    if (c == '(') chars.push((char)((int)c+1));
////                })
////                .filter(c -> c == ')')
////                .anyMatch(c -> c != chars.poll()); not this
////        .allMatch(c->c==chars.pop())
//
////        System.out.println("Valid: "+(!b));
    }
}
