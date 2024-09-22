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
        int valley = getFirst();
        int[] newLandscape = new int[size - 1];
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (landscape[i] != valley) {
                newLandscape[index++] = landscape[i];
            }
        }
        landscape = newLandscape;
        size--;
        return valley;
    }

    /**
     * Creates a new landform at the first valley point.
     * 
     * @param num The height of the new landform.
     */
    public void insert(int height) {
        int valleyIndex = findFirstValleyIndex();
        int[] newLandscape = new int[size + 1];
        for (int i = 0; i < valleyIndex; i++) {
            newLandscape[i] = landscape[i];
        }
        newLandscape[valleyIndex] = height;
        for (int i = valleyIndex; i < size; i++) {
            newLandscape[i + 1] = landscape[i];
        }
        landscape = newLandscape;
        size++;
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

}
