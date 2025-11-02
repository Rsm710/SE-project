package benchmark;

import compression.BitPacking;
import factory.BitPackingFactory;

public class Benchmark {

    public static void runBenchmark(String type, int[] input, int testIndex) {
        System.out.println("=== Benchmark for: " + type.toUpperCase() + " ===");

        BitPacking compressor = BitPackingFactory.create(type);

        long startCompress = System.nanoTime();
        int[] compressed = compressor.compress(input);
        long endCompress = System.nanoTime();
        long compressTime = endCompress - startCompress;

        int[] decompressed = new int[input.length];
        long startDecompress = System.nanoTime();
        compressor.decompress(compressed, decompressed);
        long endDecompress = System.nanoTime();
        long decompressTime = endDecompress - startDecompress;

        long startGet = System.nanoTime();
        int value = compressor.get(testIndex);
        long endGet = System.nanoTime();
        long getTime = endGet - startGet;

        System.out.println("Compression time: " + compressTime + " ns");
        System.out.println("Decompression time: " + decompressTime + " ns");
        System.out.println("get(" + testIndex + ") = " + value + " in " + getTime + " ns");

        int originalSizeBits = input.length * 32;
        int compressedSizeBits = compressed.length * 32;
        double ratio = (double) compressedSizeBits / originalSizeBits;
        System.out.println("Compression ratio: " + ratio);

        long transmissionTimeUncompressed = originalSizeBits;
        long transmissionTimeCompressed = compressedSizeBits + compressTime + decompressTime;
        long latencyThreshold = transmissionTimeUncompressed - transmissionTimeCompressed;
        System.out.println("Latency threshold (ns): " + latencyThreshold);
        System.out.println();
    }
}