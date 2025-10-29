package compression;

public class BitPackingSplit implements BitPacking {
    private int[] compressed;
    private int bitSize;
    private int originalLength;

    public void setBitSize(int bitSize) {
        this.bitSize = bitSize;
    }

    public int getBitSize() {
        return bitSize;
    }

    @Override
    public int[] compress(int[] input) {
        originalLength = input.length;
        if (bitSize == 0) {
            bitSize = calculateBitSize(input);
        }

        int valuesPerInt = 32 / bitSize;
        int compressedLength = (int) Math.ceil((double) input.length / valuesPerInt);
        compressed = new int[compressedLength];

        for (int i = 0; i < input.length; i++) {
            int compressedIndex = i / valuesPerInt;
            int bitOffset = (i % valuesPerInt) * bitSize;
            compressed[compressedIndex] |= (input[i] & ((1 << bitSize) - 1)) << bitOffset;
        }

        return compressed;
    }

    @Override
    public void decompress(int[] compressedInput, int[] output) {
        int valuesPerInt = 32 / bitSize;
        for (int i = 0; i < output.length; i++) {
            int compressedIndex = i / valuesPerInt;
            int bitOffset = (i % valuesPerInt) * bitSize;
            output[i] = (compressedInput[compressedIndex] >> bitOffset) & ((1 << bitSize) - 1);
        }
    }

    @Override
    public int get(int i) {
        int valuesPerInt = 32 / bitSize;
        int compressedIndex = i / valuesPerInt;
        int bitOffset = (i % valuesPerInt) * bitSize;
        return (compressed[compressedIndex] >> bitOffset) & ((1 << bitSize) - 1);
    }

    private int calculateBitSize(int[] input) {
        int max = 0;
        for (int val : input) {
            max = Math.max(max, val);
        }
        return Integer.toBinaryString(max).length();
    }
}