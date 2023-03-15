import java.util.PriorityQueue;
import static java.util.Comparator.reverseOrder;

public class StreamMedian {
    PriorityQueue<Integer> bigger;
    PriorityQueue<Integer> smaller;

    public StreamMedian(){
        bigger = new PriorityQueue<>();
        smaller = new PriorityQueue<>(1, reverseOrder());
    }

    public void insert(Integer i){
        // First we put the element into the correct section.
        if(smaller.isEmpty() || i < smaller.peek()){
            smaller.add(i);
        }
        else{
            bigger.add(i);
        }
         // Then we adjust for the new sizes.
        if(bigger.size() > smaller.size()){
            smaller.add(bigger.remove());
        }
        if(smaller.size() > bigger.size()+1){
            bigger.add(smaller.remove());
        }
    }

    public double getMedian(){
        // If smaller is empty, so is bigger
        if(smaller.isEmpty()){
            return 0;
        }

        // We know that if bigger is empty, then there is only 1 value.
        if(bigger.isEmpty()){
            return smaller.peek();
        }
        //If there is an even number of elements, get avg of bigger and smaller .peek() methods.
        if((bigger.size()+smaller.size())%2 == 0){
            return ((bigger.peek()+smaller.peek())/2.0);
        }
        // We know there is an odd number of elements, so return smaller .peek since that has the odd element.
        return smaller.peek();
    }
}
