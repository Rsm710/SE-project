package compression;

public class BitPackingOverlap implements BitPacking {
    private int[] compressed;
    private int bitSize;
    private int originalLength;

    @Override
    public int[] compress(int[] input) {
        originalLength = input.length;
        bitSize = calculateBitSize(input);
        int totalBits = input.length * bitSize;
        int compressedLength = (int) Math.ceil((double) totalBits / 32);
        compressed = new int[compressedLength];

        int bitPos = 0;
        for (int val : input) {
            int index = bitPos / 32;
            int offset = bitPos % 32;

            compressed[index] |= (val << offset);
            if (offset + bitSize > 32) {
                compressed[index + 1] |= (val >>> (32 - offset));
            }
            bitPos += bitSize;
        }
        return compressed;
    }

    @Override
    public void decompress(int[] compressedInput, int[] output) {
        int bitPos = 0;
        for (int i = 0; i < originalLength; i++) {
            int index = bitPos / 32;
            int offset = bitPos % 32;

            int val = (compressedInput[index] >>> offset);
            if (offset + bitSize > 32) {
                val |= (compressedInput[index + 1] << (32 - offset));
            }
            output[i] = val & ((1 << bitSize) - 1);
            bitPos += bitSize;
        }
    }

    @Override
    public int get(int i) {
        int bitPos = i * bitSize;
        int index = bitPos / 32;
        int offset = bitPos % 32;

        int val = (compressed[index] >>> offset);
        if (offset + bitSize > 32) {
            val |= (compressed[index + 1] << (32 - offset));
        }
        return val & ((1 << bitSize) - 1);
    }

    private int calculateBitSize(int[] input) {
        int max = 0;
        for (int val : input) {
            max = Math.max(max, val);
        }
        return Integer.toBinaryString(max).length();
    }
}