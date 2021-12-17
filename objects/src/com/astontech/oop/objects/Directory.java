package com.astontech.oop.objects;

public class Directory {

    //region  PROPERTIES

    private int DirectoryId;

    private String DirectoryName;

    private Double DirectorySize;

    private String NumberOfFiles;

    private String Path;

    //endregion

    //region  CONSTRUCTORS

    public Directory () {};

    public Directory (int directoryId) {
        this.setDirectoryId(directoryId);
    };

    public Directory (String directoryName) {
        this.setDirectoryName(directoryName);
    };

    public Directory (int directoryId, String directoryName) {
        this.setDirectoryId(directoryId);
        this.setDirectoryName(directoryName);
    };

    //endregion

    //region  GETTERS / SETTERS


    public int getDirectoryId() {
        return DirectoryId;
    }

    public void setDirectoryId(int directoryId) {
        DirectoryId = directoryId;
    }

    public String getDirectoryName() {
        return DirectoryName;
    }

    public void setDirectoryName(String directoryName) {
        DirectoryName = directoryName;
    }

    public double getDirectorySize() {
        return DirectorySize;
    }

    public void setDirectorySize(double directorySize) {
        DirectorySize = directorySize;
    }

    public String getNumberOfFiles() {
        return NumberOfFiles;
    }

    public void setNumberOfFiles(String numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    //endregion

    //region  CUSTOM METHODS

    //endregion

}
