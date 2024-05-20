package org.wgh.handshop.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageUtil {
    public static String trimImage(String url) throws IOException {
        Path filePath = Paths.get("src/main/resources/static", url);
        byte[] fileContent = Files.readAllBytes(filePath);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }
}
