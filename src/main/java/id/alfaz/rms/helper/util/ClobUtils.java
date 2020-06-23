package id.alfaz.rms.helper.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.ClobProxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class ClobUtils {
    private static final Logger log = LogManager.getLogger(ClobUtils.class);

    private ClobUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getAsString(Clob clob) {
        try {
            return (String)(new BufferedReader(new InputStreamReader(clob.getAsciiStream()))).lines().collect(Collectors.joining());
        } catch (SQLException var2) {
            log.error(var2);
            return null;
        }
    }

    public static Clob getClob(String s) {
        return ClobProxy.generateProxy(s);
    }
}