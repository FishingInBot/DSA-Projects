import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This program finds the shortest path between two words in a dictionary.
 * It contains a breadth first search algorithm to find the path.
 * It uses a HashMap to store the words and their neighbors.
 * It uses a WordNode class to represent the nodes in the graph.
 * The program reads in a file of words of the same length as the start word.
 * It then searches the graph for the shortest path between the start and end words.
 * @author Brandon Murry
 */
public class WordLadder {
    public static void main(String[] args) throws Throwable {
        // the words on which the ladder is based
        String start, end;
        // the words in the file, WordNode is class to represent nodes in our graph
        HashMap<String, WordNode> wordlist = new HashMap<String, WordNode>();

        //Time to get some input from the user
        Scanner in = new Scanner(System.in);
        // Read in the two words
        System.out.println("Enter the beginning word");
        start = in.next();
        System.out.println("Enter the ending word");
        end = in.next();

        // Check length of the words, if they are not the same length, exit
        int length = start.length();
        if (length != end.length()) {
            System.err.println("ERROR! Words not of the same length.");
            System.exit(1);
        }

        // Read in the appropriate file of words based on the length of start
        readFile(wordlist, start);
        // Search the graph
        breadthFirstSearch(wordlist, start, end);
    }

    /**
     * This is my WordNode, it contains the word and an array of its neighbors in the graph.
     * It is used to represent the nodes in the graph.
     * @author Brandon Murry
     */
    public static class WordNode {
        public String word;
        public WordNode[] neighbors;
        public ArrayList<WordNode> path = new ArrayList<WordNode>();
        public WordNode(String word) {
            this.word = word;
        }

        public void setNeighbors(WordNode[] neighbors) {
            this.neighbors = neighbors;
        }
    }

    /**
     * This method reads in the file of words of the same length as start and adds them to the wordlist.
     * It then searches the graph for the shortest path between the start and end words.
     * @param wordlist The HashMap of words and their neighbors
     * @param start The starting word
     * @author Brandon Murry
     */
    public static void readFile(HashMap<String, WordNode> wordlist, String start) {
        //declare our file
        File file = null;

        //Pick what file we are going to use based on the length of start
        switch (start.length()) {
            case 3 -> file = new File("words.3");
            case 4 -> file = new File("words.4");
            case 5 -> file = new File("words.5");
            case 6 -> file = new File("words.6");
            case 7 -> file = new File("words.7");
            case 8 -> file = new File("words.8");
            case 9 -> file = new File("words.9");
            default -> {
                System.err.println("ERROR! Word length not supported.");
                System.exit(1);
            }
        }

        //Read in the file and add the words to the wordlist
        try {
            //TODO make dictionary of words.
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                String word = in.next();
                WordNode node = new WordNode(word);
                wordlist.put(word, node);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR! File not found.");
            System.exit(1);
        }

        //Check if file is read and compare size to word count.
        System.out.println("File read in successfully.: " + wordlist.size() + " words");
    }

    /**
     * This method searches the graph for the shortest path between the start and end words.
     * It uses a breadth first search algorithm to find the path.
     * @param wordlist The HashMap of words and their neighbors.
     * @param start The starting word.
     * @param end The word to find.
     * @author Brandon Murry
     */
    public static void breadthFirstSearch(HashMap<String, WordNode> wordlist, String start, String end) {
        //Check if start and end words are in the dictionary. If not, exit.
        if (!wordlist.containsKey(start) || !wordlist.containsKey(end)) {
            System.err.println("ERROR! Start or end word not in dictionary.");
            System.exit(1);
        }

        //Set the neighbors of the start word
        findNeighbors(wordlist, start);

        //search neighbors of current word for end word. If it is not there, check for neighbors of neighbors.
        Queue<WordNode> queue = new LinkedList<WordNode>();
        queue.add(wordlist.get(start));
        List<String> visited = new ArrayList<String>();
        visited.add(start);

        while(!queue.isEmpty()){
            WordNode current = queue.remove();
            if(current.word.equals(end)){
                System.out.println("Path: ");
                for(WordNode node : current.path){
                    System.out.print(node.word + "->");
                }
                System.out.println(current.word);
                System.exit(0);
            }
            for(WordNode neighbor : current.neighbors){
                if(neighbor.path.isEmpty()){
                    neighbor.path.addAll(current.path);
                    neighbor.path.add(current);
                } else if (neighbor.path.size() > current.path.size() + 1){
                    neighbor.path.clear();
                    neighbor.path.addAll(current.path);
                    neighbor.path.add(current);
                }
                if(!visited.contains(neighbor.word)){
                    queue.add(neighbor);
                    visited.add(neighbor.word);
                }
                findNeighbors(wordlist, neighbor.word);
            }
        }
        System.out.println("No path found.");
    }

    /**
     * This method finds the neighbors of the given word and sets them as the neighbors of the WordNode for that word.
     * It does this by changing each letter of the word to a different letter and checking if it is in the dictionary.
     * @param wordlist The HashMap of words and their neighbors.
     * @param str The word to find neighbors for.
     * @author Brandon Murry
     */
    public static void findNeighbors(HashMap<String, WordNode> wordlist, String str) {
        // make a list of neighbors
        ArrayList<WordNode> neighbors = new ArrayList<WordNode>();

        // for each letter in the word, change it to a different letter
        for(int i = 0; i < str.length(); i++) {
            for(int j = 0; j < 26; j++) {
                String word = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
                // if the word is in the dictionary, add it to the list of neighbors
                if(wordlist.containsKey(word) && !word.equals(str)) {
                    neighbors.add(wordlist.get(word));
                }
            }
        }

        // convert the list of neighbors to an array and set the neighbors of the start word
        WordNode[] neighborArray = new WordNode[neighbors.size()];
        for(int i = 0; i < neighbors.size(); i++) {
            neighborArray[i] = neighbors.get(i);
        }

        // set the neighbors of the start word
        wordlist.get(str).setNeighbors(neighborArray);
    }
}
