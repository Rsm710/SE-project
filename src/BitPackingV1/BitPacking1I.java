package BitPackingV1;

import java.util.BitSet;

public class BitPacking1I {
    protected BitSet bits;
    protected int k;
    protected int[] compressedArr;

    protected BitPacking1I(int[] arr) {
        findK(arr);
        bits = new BitSet(arr.length*k);
        int bitIndex = 0;
        for (int value : arr) {
            for(int i=0; i<k;i++){
                if(((value>>i) & 1)==1){
                    bits.set(bitIndex + i);
                }
            }
            bitIndex = bitIndex + k;
        }

        long[] compressedArrLong = bits.toLongArray();

        compressedArr = new int[compressedArrLong.length*2];

        for(int i=0; i<compressedArrLong.length; i++){
            compressedArr[i * 2] = (int)(compressedArrLong[i] & 0xFFFFFFFFL);
            compressedArr[2 * i + 1] = (int)(compressedArrLong[i] >>> 32);
        }
    }

    private void findK(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        k = minBit(max);
    }

    private int minBit(int n){
        if(n==0) return 1;
        int count = 0;
        while(n>0){
            count++;
            n >>=1;
        }
        return count;
    }
}
