package org.dbentity.utils.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static void createFile(String path, String content) throws IOException {
        File file = new File(path);

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
        }
    }
}
