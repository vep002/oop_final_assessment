package com.astontech.oop.objects;

public class File {

    //region  PROPERTIES

        private int FileId;

        private String FileName;

        private String FileType;

        private Double FileSize;

        private int DirectoryId;


    //endregion

    //region  CONSTRUCTORS

    public File () {};

    public File (int fileId) {
        this.setFileId(fileId);
    };

    public File (int fileId, String fileName) {
        this.setFileId(fileId);
        this.setFileName(fileName);
    };

    public File (String fileName) {
        this.setFileName(fileName);
    };

    //endregion

    //region  GETTERS / SETTERS

    public int getFileId() {
        return FileId;
    }

    public void setFileId(int fileId) {
        FileId = fileId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public double getFileSize() {
        return FileSize;
    }

    public void setFileSize(double fileSize) {
        FileSize = fileSize;
    }

    public int getDirectoryId() {
        return DirectoryId;
    }

    public void setDirectoryId(int directoryId) {
        DirectoryId = directoryId;
    }


    //endregion

    //region  CUSTOM METHODS

    //endregion

}
