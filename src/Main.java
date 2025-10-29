import benchmark.Benchmark;

public class Main {
    public static void main(String[] args) {
        int[] input = generateTestArray(100000, 0, 5000); // tableau large
        int testIndex = 12345;

        Benchmark.runBenchmark("split", input, testIndex);
        Benchmark.runBenchmark("overlap", input, testIndex);
        Benchmark.runBenchmark("overflow", input, testIndex);
    }

    private static int[] generateTestArray(int size, int min, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = min + (int)(Math.random() * (max - min + 1));
        }
        return array;
    }
}