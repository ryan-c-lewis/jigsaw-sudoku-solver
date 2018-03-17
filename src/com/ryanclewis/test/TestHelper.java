package com.ryanclewis.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

class TestHelper {
    static String loadResource(String fileName) throws IOException {
        ClassLoader classLoader = TestHelper.class.getClassLoader();
        URL resource = classLoader.getResource("com/ryanclewis/test/resources/" + fileName);
        if (resource == null)
            throw new IOException("Resource not found");

        File file = new File(resource.getFile());
        StringBuilder fileContents = new StringBuilder((int)file.length());
        String lineSeparator = System.getProperty("line.separator");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append(lineSeparator);
            }
            return fileContents.toString();
        }
    }
}
