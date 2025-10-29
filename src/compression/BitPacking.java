package compression;

public interface BitPacking {
    int[] compress(int[] input);
    void decompress(int[] compressed, int[] output);
    int get(int i);
}
