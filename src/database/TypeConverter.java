package database;

import java.math.BigDecimal;

public class TypeConverter {
    public static Double parseBigDecToDouble(BigDecimal number) {
        return (number == null) ? null : number.doubleValue();
    }
}
