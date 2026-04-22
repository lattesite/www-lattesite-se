package site.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class FileUtil {

    public static void writeFile(String file, String data) throws Exception {
        System.out.println("Writing file \"" + file + "\".");
        FileUtils.writeStringToFile(new File(file), data, StandardCharsets.UTF_8);
    }

    public static String readFileAsString(String file) throws Exception {
        return readFileAsString(new File(file));
    }

    public static String readFileAsString(File file) throws Exception {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
    }

    public static List<String> listFiles(String folder, String extension) {

        List<String> files = new ArrayList<>();

        String[] extensions = new String[]{extension};

        Collection<File> foundFiles = FileUtils.listFiles(new File(folder), extensions, true);

        for (File foundFile : foundFiles) {
            files.add(foundFile.toString());
        }

        return files;

    }
}
