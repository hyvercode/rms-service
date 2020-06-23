package id.alfaz.rms.helper.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class BlobUtil {
    private BlobUtil() {
    }

    public static String blobToString(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }

    public static String blobToString(byte[] data, Charset charset) {
        return new String(data, charset);
    }

    public static byte[] stringToBlob(String data) {
        return stringToBlob(data, StandardCharsets.UTF_8);
    }

    public static byte[] stringToBlob(String data, Charset charset) {
        return data.getBytes(charset);
    }

}