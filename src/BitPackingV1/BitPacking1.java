package BitPackingV1;

public class BitPacking1 {
    BitPacking1I impl;

    public BitPacking1() {
    }

    public int[] compressed(int[] arr){
        impl = new BitPacking1I(arr);
        return impl.compressedArr;
    }

    public int getMaxBit(){
        return impl.k;
    }
}
