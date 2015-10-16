package com.varun.linux;

import java.io.IOException;
import java.util.Scanner;

import com.varun.linux.service.DirectoryService;
import com.varun.linux.service.exception.DeleteFileException;
import com.varun.linux.service.exception.InvalidArgumentException;
import com.varun.linux.service.exception.InvalidFileException;
import com.varun.linux.service.exception.ThisIsNotDirectoryException;

public class Application {

    public static void main(String... args) {

        Application linuxApplication = new Application();
        linuxApplication.showBiggerMenu();
        do {
            try {
                linuxApplication.processUserInput(linuxApplication.getInputFromUser());
            } catch (InvalidArgumentException | IOException | ThisIsNotDirectoryException
                    | InvalidFileException | DeleteFileException e) {
                System.out.println("Error while doing operation");
            }
            linuxApplication.showSmallMenu();
        } while (true);
    }

    private void showBiggerMenu() {
        System.out.println("==========Welcome to linux Operating System=================");
        System.out.println("Following options are avaialble for navigation");
        System.out.println("ls for list of current Directories / files");
        System.out.println("cd for changing directory");
        System.out.println("cp for copying Directory / file");
        System.out.println("mv for moving Directory / file");
        System.out.println("rename for renaming Directory / file");
        System.out.println("vi to read File");
        System.out.println("find to read File");
    }

    private void showSmallMenu() {
        System.out.println(
            "================================================================================================================================================");
        System.out.println(
            "ls for list; cd for changing; cp for copying file; mv for moving file; rename for renaming file; vi to read File; rm to remove; find to find file");
    }

    private String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void processUserInput(String inputFromUser) throws InvalidArgumentException, IOException,
            ThisIsNotDirectoryException, InvalidFileException, DeleteFileException {
        String process = inputFromUser.split(" ")[0];
        String fileName = "";
        String target = "";
        switch (process) {
            case "ls":
                DirectoryService.showAllDirectoryAndFiles();
                break;
            case "cd":
                String directoryName = inputFromUser.split(" ")[1];
                DirectoryService.changeDirectory(directoryName);
                break;
            case "cp":
                checkValidArgumentsForCopyorMoveorRename(inputFromUser);
                fileName = inputFromUser.split(" ")[1];
                target = inputFromUser.split(" ")[2];
                isValidFile(fileName);
                DirectoryService.copyFile(fileName, target);
                break;
            case "mv":
                checkValidArgumentsForCopyorMoveorRename(inputFromUser);
                fileName = inputFromUser.split(" ")[1];
                target = inputFromUser.split(" ")[2];
                isValidFile(fileName);
                DirectoryService.moveFile(fileName, target);
                break;
            case "rename":
                checkValidArgumentsForCopyorMoveorRename(inputFromUser);
                fileName = inputFromUser.split(" ")[1];
                target = inputFromUser.split(" ")[2];
                DirectoryService.renameFile(fileName, target);
                break;
            case "vi":
                checkValidArgumentsForVieworRemoveorFind(inputFromUser);
                fileName = inputFromUser.split(" ")[1];
                DirectoryService.viewFile(fileName);
                break;
            case "rm":
                checkValidArgumentsForVieworRemoveorFind(inputFromUser);
                fileName = inputFromUser.split(" ")[1];
                DirectoryService.removeFile(fileName);
                break;
            case "find":
                checkValidArgumentsForVieworRemoveorFind(inputFromUser);
                fileName = inputFromUser.split(" ")[1];
                DirectoryService.find(fileName);
                break;
            default:
                System.out.println("Exiting from linux. Good Bye.");
                System.exit(0);
        }

    }

    private void isValidFile(String fileName) throws InvalidFileException {
        if (!DirectoryService.isFile(fileName)) {
            throw new InvalidFileException("Not a valid argument");
        }
    }

    private void checkValidArgumentsForVieworRemoveorFind(String inputFromUser)
            throws InvalidArgumentException {
        int arguments = inputFromUser.split(" ").length;
        if (arguments != 2) {
            throw new InvalidArgumentException("Not a valid argument");
        }
    }

    private void checkValidArgumentsForCopyorMoveorRename(String inputFromUser)
            throws InvalidArgumentException {
        int arguments = inputFromUser.split(" ").length;
        if (arguments != 3) {
            throw new InvalidArgumentException("Not a valid argument");
        }
    }
}
