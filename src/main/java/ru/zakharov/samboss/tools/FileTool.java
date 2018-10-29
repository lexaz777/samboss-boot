package ru.zakharov.samboss.tools;

import org.springframework.stereotype.Component;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileTool {
    public FileTool() {
    }

    public List<String> findFilesInDirectory(String folder, String extension) {
        List<String> filesList = new ArrayList<>();
        Path dir = Paths.get(folder);

        if (Files.exists(dir) && Files.isDirectory(dir)) {
            try (DirectoryStream<Path> dirStream =
                         Files.newDirectoryStream(dir, extension)) {
                for (Path path : dirStream) {
                    System.out.println(path.getFileName());
                    filesList.add(path.getFileName().toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filesList;
    }

    public void copyFile(String srcFolder, String fileName, String dstFolder) {
        Path pathToDstFolder = Paths.get(dstFolder);

        if (!Files.exists(pathToDstFolder)) {
            try {
                Files.createDirectory(pathToDstFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path srcFilePath = Paths.get(srcFolder, fileName);
        Path dstFilepath = Paths.get(dstFolder, fileName);

        try {
            Files.copy(srcFilePath, dstFilepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteFile(String folder, String fileName) {
        Path path = Paths.get(folder, fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
