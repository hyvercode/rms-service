package com.hyvercode.rms.helper.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ImageConverter {
    private ImageConverter() {
    }

    public static String encodeToString(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String encodeBufferedImageToString(BufferedImage input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(input, "png", baos);
        byte[] bytes = baos.toByteArray();
        baos.flush();
        baos.close();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static BufferedImage convertToImage(String inputBase64) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(inputBase64);
        InputStream in = new ByteArrayInputStream(bytes);
        return ImageIO.read(in);
    }
}
