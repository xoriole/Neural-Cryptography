package neuralcryptography.net;

/**
 *
 * @author phoenix
 */
public class InputVector {

    private int[] data;
    private int k;
    private int n;
    /* methods of input vector */

    public void setRandom(int k, int n) {
        this.k = k;
        this.n = n;
        data = new int[k * n];
        for (int i = 0; i < k * n; i++) {
            data[i] = getRandomBit();
        }
    }

    public int[] getData() {
        return data;
    }

    public String getBitString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < k * n - 1; i++) {
            builder.append(data[i] + ",");
        }
        builder.append(data[data.length - 1]);
        return builder.toString().trim();
    }

    public int[] convertToVector(String v) {
        int[] dat = new int[k * n];
        String[] words = v.split(",");
        System.out.println("sixe of words:" + words.length);
        for (int i = 0; i < k * n; i++) {
            dat[i] = Integer.parseInt(words[i].trim());
        }
        return data;
    }
    /* helper methods */

    public static int getRandomBit() {
        double a = Math.random() * 2;
        return (a > 1) ? 1 : -1;
    }
}
