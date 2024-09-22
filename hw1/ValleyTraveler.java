/**
 * ValleyTraveler class represents a magical map that can identify and modify
 * valley points in the landscape of Numerica.
 * 
 * @author <Alex Rzomp>
 */
public class ValleyTraveler {

    // Create instance variables here.

    private int[] landscape;
    private int size; 
    private boolean[] isValley;

    /**
     * Constructor to initialize the magical map with the given landscape of
     * Numerica.
     * 
     * @param landscape An array of distinct integers representing the landscape.
     */
    public ValleyTraveler(int[] landscape) {
        this.landscape = new int[landscape.length];
        for (int i = 0; i < landscape.length; i++) {
            this.landscape[i] = landscape[i];
        }
        this.size = landscape.length;
        this.isValley = new boolean[landscape.length];
    }

    /**
     * Checks if the entire landscape is excavated (i.e., there are no landforms
     * left).
     * 
     * @return true if the landscape is empty, false otherwise.
     */
    public boolean isEmpty() {
        
        return size == 0;
    }

    /**
     * Locates the first valley point in the landscape of Numerica.
     * 
     * @return The first valley point in the landscape.
     */
    public int getFirst() {
        for (int i = 0; i < size; i++) {
            if (isValley(i)) {
                return landscape[i];
            }
        }
        return -1;
    }

    /**
     * Excavates the first valley point, removing it from the landscape of Numerica.
     * 
     * @return The excavated valley point.
     */
    public int remove() {
        int valleyIndex = findFirstValleyIndex();
        int valley = landscape[valleyIndex];

        // Shift elements left to remove the valley element
        for (int i = valleyIndex; i < size - 1; i++) {
            landscape[i] = landscape[i + 1];
        }

        size--; // Decrease the size
        updateNeighborsAfterRemoval(valleyIndex);
        return valley;
    
    }

    /**
     * Creates a new landform at the first valley point.
     * 
     * @param num The height of the new landform.
     */
    public void insert(int height) {
        int valleyIndex = findFirstValleyIndex();

        // Shift elements right to make space for the new element
        for (int i = size; i > valleyIndex; i--) {
            landscape[i] = landscape[i - 1];
        }

        landscape[valleyIndex] = height;
        size++; // Increase the size

        // Update the valleys around the inserted element
        updateNeighborsAfterInsertion(valleyIndex);
    }




    //helper methods
    private boolean isValley(int i) {
        if (i == 0 && size == 1) {
            return true; // Only one element
        } else if (i == 0) {
            return landscape[i] < landscape[i + 1];
        } else if (i == size - 1) {
            return landscape[i] < landscape[i - 1];
        } else {
            return landscape[i] < landscape[i - 1] && landscape[i] < landscape[i + 1];
        }
    }

    private int findFirstValleyIndex() {
        for (int i = 0; i < size; i++) {
            if (isValley(i)) {
                return i;
            }
        }
        return -1; // This should never happen
    }

    private void computeValleys() {
        for (int i = 0; i < size; i++) {
            isValley[i] = checkValley(i);
        }
    }

    private boolean checkValley(int i) {
        if (size == 1) return true; // Single element is always a valley
        if (i == 0) return landscape[i] < landscape[i + 1];
        if (i == size - 1) return landscape[i] < landscape[i - 1];
        return landscape[i] < landscape[i - 1] && landscape[i] < landscape[i + 1];
    }

    private void updateNeighborsAfterRemoval(int index) {
        if (index > 0) {
            isValley[index - 1] = checkValley(index - 1);
        }
        if (index < size) { // Since size is now reduced
            isValley[index] = checkValley(index);
        }
    }

    private void updateNeighborsAfterInsertion(int index) {
        if (index > 0) {
            isValley[index - 1] = checkValley(index - 1);
        }
        if (index < size - 1) {
            isValley[index + 1] = checkValley(index + 1);
        }
        isValley[index] = checkValley(index); // New element itself might be a valley
    }




}
