import java.util.PriorityQueue;
import static java.util.Comparator.reverseOrder;

/**
 * This class implements Priority Queues as both min-heaps and max-heaps. The min-heap will store larger half of values and the max-heap will store the lower values.
 * We use these Heaps to find the median value of everything passed into them.
 * @author Brandon Murry
 */
public class StreamMedian {
    PriorityQueue<Integer> bigger;
    PriorityQueue<Integer> smaller;

    /**
     * Default constructor for StreamMedian
     */
    public StreamMedian(){
        bigger = new PriorityQueue<>();
        smaller = new PriorityQueue<>(1, reverseOrder());
    }

    /**
     * 2 param constructor for StreamMedian. It is here to allow use of your own Priority Queues.
     * @param bigger The Priority queue for larger values.
     * @param smaller The priority queue for lower values.
     */
    public StreamMedian(PriorityQueue<Integer> bigger, PriorityQueue<Integer> smaller){
        this.smaller = smaller;
        this.bigger = bigger;
    }

    /**
     * Insert takes in any Integer value, then adds it to the correct heap depending on previously inserted values.
     * Then it corrects for size differential, if needed.
     * Smaller will always contain the odd-numbered digit, otherwise they will be of equal size.
     * @param i Integer to be inserted
     */
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

    /**
     * Returns the median value of heaps.
     * @return The median value of the heaps.
     */
    public double getMedian(){
        // If smaller is empty, so is bigger
        if(smaller.isEmpty()){
            return 0;
        }
        //If there is an even number of elements, get avg of bigger and smaller .peek() methods.
        if((smaller.size()) == bigger.size()){
            return ((bigger.peek()+smaller.peek())/2.0);
        }
        // We know there is an odd number of elements, so return smaller .peek since that has the odd element.
        return smaller.peek();
    }
}