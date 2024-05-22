package ArrayRandom;

public class Random10 {
    private int[] array;

    public Random10() {
        this.array = populateArrayRandomly();
    }

    public int[] getArray() {
        return array;
    }

    private int[] populateArrayRandomly() {
        int[] randomArray = new int[5];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = ((int) (Math.random() * 10) + 1);
        }
        return randomArray;
    }

    public void replaceArrayI(int i, int newNumber) {
        this.array[i] = newNumber;
    }
}
