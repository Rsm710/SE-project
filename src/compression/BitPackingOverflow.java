package compression;

import java.util.ArrayList;
import java.util.List;

public class BitPackingOverflow implements BitPacking {
    private int[] compressed;
    private List<Integer> overflow = new ArrayList<>();
    private int bitSize;
    private int signalValue;
    private int originalLength;
    private int[] packedArray;

    @Override
    public int[] compress(int[] input) {
        originalLength = input.length;

        bitSize = findOptimalBitSize(input);
        signalValue = (1 << bitSize) - 1;

        List<Integer> packed = new ArrayList<>();

        for (int val : input) {
            if (val < signalValue) {
                packed.add(val);
            } else {
                packed.add(signalValue);
                packed.add(overflow.size());
                overflow.add(val);
            }
        }

        packedArray = packed.stream().mapToInt(i -> i).toArray();

        int maxPackedValue = signalValue;
        if (!overflow.isEmpty()) {
            int maxIndex = overflow.size() - 1;
            int indexBitSize = Integer.toBinaryString(maxIndex).length();
            bitSize = Math.max(bitSize, indexBitSize);
        }

        BitPackingSplit base = new BitPackingSplit();
        base.setBitSize(bitSize);
        compressed = base.compress(packedArray);

        return compressed;
    }

    @Override
    public void decompress(int[] compressedInput, int[] output) {
        BitPackingSplit base = new BitPackingSplit();
        base.setBitSize(bitSize);
        int[] temp = new int[packedArray.length];
        base.decompress(compressedInput, temp);

        int outIndex = 0;
        for (int i = 0; i < temp.length && outIndex < originalLength; i++) {
            if (temp[i] == signalValue) {
                i++;
                output[outIndex++] = overflow.get(temp[i]);
            } else {
                output[outIndex++] = temp[i];
            }
        }
    }

    @Override
    public int get(int i) {
        BitPackingSplit base = new BitPackingSplit();
        base.setBitSize(bitSize);
        int[] temp = new int[packedArray.length];
        base.decompress(compressed, temp);

        int outIndex = 0;
        for (int j = 0; j < temp.length && outIndex <= i; j++) {
            if (temp[j] == signalValue) {
                j++;
                if (outIndex == i) return overflow.get(temp[j]);
                outIndex++;
            } else {
                if (outIndex == i) return temp[j];
                outIndex++;
            }
        }
        throw new IndexOutOfBoundsException("Index " + i + " out of bounds");
    }

    private int findOptimalBitSize(int[] input) {
        int max = 0;
        for (int val : input) {
            max = Math.max(max, val);
        }
        return Integer.toBinaryString(max).length();
    }
}