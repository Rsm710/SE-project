import BitPackingV1.BitPacking1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BitPacking1 test = new BitPacking1();
        int[] original = {5, 7, 3, 200, 15, 2};
        System.out.println("Original : " + Arrays.toString(original));
        int[] retour=test.compressed(original);
        System.out.println("retour : " + test.getMaxBit());
        System.out.println("retour : " + Arrays.toString(retour));

    }
}
