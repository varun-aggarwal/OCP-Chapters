package com.varun.linux.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.varun.linux.service.exception.DeleteFileException;
import com.varun.linux.service.exception.ThisIsNotDirectoryException;
import com.varun.linux.util.MyPathMatcher;

public class DirectoryService {

    private static Path source = Paths.get("D:/");

    /**
     * @return the dir
     */
    public static Path getDir() {
        return source;
    }

    /**
     * @param dir the dir to set
     */
    public static void setDir(Path dir) {
        DirectoryService.source = dir;
    }

    public static void showAllDirectoryAndFiles() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(source)) {
            for (Path path : stream) {
                BasicFileAttributes basicFileAttributes =
                    Files.readAttributes(path, BasicFileAttributes.class);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
                FileTime creationTime = basicFileAttributes.creationTime();
                System.out.println(
                    dateFormat.format(new Date(creationTime.toMillis())) + "\t" + path.getFileName());
            }
        }
    }

    public static void changeDirectory(String inputFromUser) throws ThisIsNotDirectoryException {
        if (isDirectory(inputFromUser)) {
            source = Paths.get(source.toString(), inputFromUser);
        } else {
            throw new ThisIsNotDirectoryException("The directory mentioned is not an actual directory");
        }
    }

    public static boolean isDirectory(String directoryName) {
        File file = new File(source.toString() + File.separator + directoryName);
        return file.isDirectory();
    }

    public static boolean isFile(String fileName) {
        File file = new File(source.toString() + File.separator + fileName);
        return file.isFile();
    }

    public static void copyFile(String fileName, String target) throws IOException {
        Path sourceFile = Paths.get(source + File.separator + fileName);
        Path targetFile = Paths.get(source + File.separator + target + File.separator + fileName);
        Files.copy(sourceFile, targetFile);
    }

    public static void moveFile(String fileName, String target) throws IOException {
        Path sourceFile = Paths.get(source + File.separator + fileName);
        Path targetFile = Paths.get(source + File.separator + target + File.separator + fileName);
        Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void renameFile(String fileName, String targetFileName) throws IOException {
        Path sourceFile = Paths.get(source + File.separator + fileName);
        Path targetFile = Paths.get(source + File.separator + targetFileName);
        Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void viewFile(String fileName) throws IOException {
        Path targetFile = Paths.get(source + File.separator + fileName);
        FileReader fileReader = new FileReader(targetFile.toFile());
        BufferedReader bufferReader = new BufferedReader(fileReader);
        String fileLine;
        while ((fileLine = bufferReader.readLine()) != null) {
            System.out.println(fileLine);
        }
        bufferReader.close();
    }

    public static void removeFile(String fileName) throws DeleteFileException {
        Path sourceFile = Paths.get(source + File.separator + fileName);
        try {
            Files.delete(sourceFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new DeleteFileException(e.getMessage());
        }
    }

    public static void find(String fileName) throws IOException {
        MyPathMatcher dirs = new MyPathMatcher(fileName);
        Files.walkFileTree(source, dirs);
    }
}
