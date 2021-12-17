package com.astontech.oop.console;

import org.apache.log4j.Logger;

import com.astontech.oop.dao.DirectoryDAO;
import com.astontech.oop.dao.FileDAO;
import com.astontech.oop.objects.Directory;
import mysql.DirectoryDAOImpl;
import mysql.FileDAOImpl;

import com.astontech.oop.objects.File;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        ConsoleApp();

    }

//    private static void LessonDAO() {
//        //region CREATE MENU
//        FileDAO fileDAO = new FileDAOImpl();
//        List<File> fileList = fileDAO.getFileList();
//
//        System.out.println("=====================");
//        for (File file : fileList) {
//            System.out.println(file.getFileId() + ") " + file.getFileName() + ", " + file.getFileType() + ", " + file.getFileSize());
//        }
//        System.out.println("=====================");
//
//        //endregion
//
//        //region PROMPT USER
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Please select a File from list: ");
//        String fileId = reader.nextLine();
//
//        //endregion
//
//        //region GET USER DETAILS
//        File fileDetail = fileDAO.getFileById(Integer.parseInt(fileId));
//
//        System.out.println(fileDetail);
//
//        System.out.println("----File Details------");
//        System.out.println("File name: " + fileDetail.getFileName());
//        System.out.println("File Size: " + fileDetail.getFileSize());
//        System.out.println("File Type: " + fileDetail.getFileType());
//
//        //endregion
//
//        //region CREATE MENU
//        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
//        List<Directory> directoryList = directoryDAO.getDirectoryList();
//
//        System.out.println("=====================");
//        for (Directory directory : directoryList) {
//            System.out.println(directory.getDirectoryId() + ") " + directory.getDirectoryName());
//        }
//        System.out.println("=====================");
//
//        //endregion
//
//        //region PROMPT USER
////        Scanner reader = new Scanner(System.in);
////        System.out.println("Please select a Directory from list: ");
////        String dirId = reader.nextLine();
//
//        //endregion
//
//        //region GET USER DETAILS
////        Directory directoryDetail = directoryDAO.getDirectoryById(Integer.parseInt(dirId));
////
////        System.out.println(directoryDetail);
////
////        System.out.println("----Directory Details------");
////        System.out.println("Directory name: " + directoryDetail.getDirectoryName());
////        System.out.println("Directory Size: " + directoryDetail.getDirectorySize());
////        System.out.println("Number of Files: " + directoryDetail.getNumberOfFiles());
////        System.out.println("Path: " + directoryDetail.getPath());
//
//        //endregion
//    }

//    private static void Insert() {
//        Directory directory = new Directory ();
//        directory.setDirectoryName("Java test directory");
//        directory.setDirectorySize("3KB");
//        directory.setNumberOfFiles("5");
//        directory.setPath("test");
//
//        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
//
//        int id = directoryDAO.insertDirectory(directory);
//
//        logger.info("New directory inserted. ID = " + id);
//
//    }
//
//    private static void Update() {
//        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
//
//        Directory directory = directoryDAO.getDirectoryById(4);
//        directory.setNumberOfFiles("6");
//
//        if (directoryDAO.updateDirectory(directory))
//            logger.info("Directory Updated Successfully");
//        else
//            logger.info("Directory Update Failed");
//    }
//
//    private static void Delete() {
//        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
//
//        if (directoryDAO.deleteDirectory(4))
//            logger.info("Directory Deleted Successfully");
//        else
//            logger.info("Delete Failed");
//    }

    private static void ConsoleApp() {

        //Prompt the user to enter a starting directory:
        Scanner directoryPrompt = new Scanner(System.in);
        System.out.println("Please enter a computer directory (enter 'none' to skip this step): ");
        String userDefinedDirectory = directoryPrompt.nextLine();
        System.out.println(userDefinedDirectory);

        //Application traverses the directory and recursively collects file and directory information:
        /*
            i.      File Name
            ii.     File Type
            iii.    File Size
            iv.     File Path
            v.      Directory Name
            vi.     Directory Size (in MB)
            vii.    Number of Files in Directory
            viii.   Directory Path
         */
        switch (userDefinedDirectory) {
            case "none" : DisplayMenu();
            break;
        }
            GetFilesAndDirectories(new java.io.File(userDefinedDirectory));

            //Display a menu with the following options:
        /*
            i.      Display directory with most files
            ii.     Display directory largest in size
            iii.    Display 5 largest files in size
            iv.     Display all files of a certain type
            v.      Clear the db and start over
            vi.     Exit
         */

    }

    public static void DisplayMenu () {
        System.out.println(     "What would you like to do?:             \n" +
                "=========================================\n" +
                "i.     Display directory with most files\n" +
                "ii.    Display directory largest in size\n" +
                "iii.   Display 5 largest files in size\n" +
                "iv.    Display all files of a certain type\n" +
                "v.     Clear the db and start over\n" +
                "vi.    Exit\n" +
                "=======================================\n"
        );
        Scanner userPrompt = new Scanner(System.in);
        String userInput = userPrompt.nextLine();
        switch (userInput)
        {
            case "Display directory with most files" : DisplayDirectoryMostFiles();
                break;
            case "Display directory largest in size" : DisplayLargestDirectory();
                break;
            case "Display 5 largest files in size" : DisplayLargestFiles();
                break;
            case "Display all files of a certain type" : DisplayFilesOfType();
                break;
            case "Clear the db and start over": ClearDB();
                break;
            case "Exit" : ;
                break;
        }
    }

    public static void GetFilesAndDirectories (java.io.File dir) {
        Helper helper = new Helper();
        java.io.File[] files = dir.listFiles();
        for (java.io.File file : files) {
            if(file.isDirectory()) {
                //recursion happens here
                logger.info("directory name: " + file.getName());
                ;
                //long dirBytes = file.length();
//                long dirKilobytes = (dirBytes / 1024);
//                long dirMegabytes = (dirKilobytes / 1024);
//                int size = (int) dirMegabytes;
//                logger.info("Directory size: " + size + "MB");
//                logger.info("Number of files: " + file.list().length);
//                logger.info("Path: " + file.getPath());
                    Directory directory = new Directory ();
                    directory.setDirectoryName(file.getName());
                    directory.setDirectorySize(helper.bytesToMegabytes(file.length()));
                    directory.setNumberOfFiles(Integer.toString(file.list().length));
                    directory.setPath(file.getPath());
                    DirectoryDAO directoryDAO = new DirectoryDAOImpl();
                    int id = directoryDAO.insertDirectory(directory);
                    logger.info("New directory inserted. ID = " + id);
                GetFilesAndDirectories(file);
            } else {
//                logger.info("  file name: " + file.getName());
//                logger.info("file type: " + helper.getExtensionByStringHandling(file.getAbsolutePath()));
//                long fileBytes = file.length();
//                long fileKilobytes = (fileBytes / 1024);
//                long fileMegabytes = (fileKilobytes / 1024);
//                int size = (int) fileMegabytes;
//                logger.info("file size: " + size + "MB");
//                logger.info("Directory: " + file.getParent());
                    File file1 = new File ();
                    file1.setFileName(file.getName());
                    file1.setFileType(helper.getExtensionByStringHandling(file.getAbsolutePath()));
                    file1.setFileSize(helper.bytesToMegabytes(file.length()));
//                    file1.setDirectoryId();
                    FileDAO fileDAO = new FileDAOImpl();
                    int id = fileDAO.insertFile(file1);
                    logger.info("New file inserted. ID = " + id);
            }
        }
    }

    public static void DisplayDirectoryMostFiles() {
        logger.info("This will eventually display the directory with the most files");
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        List<Email> emailList = emailDAO.getEmailList();


        //region PROMPT USER
        Scanner reader = new Scanner(System.in);
        System.out.println("Please select an Email from list: ");
        String emailId = reader.nextLine();

        //endregion

        //region GET USER DETAILS
        Email emailDetail = emailDAO.getEmailById(Integer.parseInt(emailId));

        System.out.println(emailDetail);

        System.out.println("----Email Details------");
        System.out.println("Email ID: " + emailDetail.getEmailId());
        System.out.println("Email Address: " + emailDetail.getEmailAddress());
    }

    public static void DisplayLargestDirectory() {
        logger.info("This will eventually display the largest directory");
        DisplayMenu();
    }

    public static void DisplayLargestFiles() {
        logger.info("This will eventually display the five largest files");
        DisplayMenu();
    }

    public static void DisplayFilesOfType() {
        logger.info("This will eventually display all files of a certain type");
        DisplayMenu();
    }

    public static void ClearDB () {
        FileDAO fileDAO = new FileDAOImpl();
        if (fileDAO.deleteAllFile(0))
            logger.info("File Deleted Successfully");
        else
            logger.info("Delete Failed");

        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        if (directoryDAO.deleteAllDirectory(0))
            logger.info("Directory Deleted Successfully");
        else
            logger.info("Delete Failed");
        ConsoleApp();
    }


}
