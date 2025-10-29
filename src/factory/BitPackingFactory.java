package factory;

import compression.*;

public class BitPackingFactory {
    public static BitPacking create(String type) {
        return switch (type.toLowerCase()) {
            case "split" -> new BitPackingSplit();
            case "overlap" -> new BitPackingOverlap();
            case "overflow" -> new BitPackingOverflow();
            default -> throw new IllegalArgumentException("Unknown compression type: " + type);
        };
    }
}