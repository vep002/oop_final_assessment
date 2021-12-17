package com.astontech.oop.dao;

import com.astontech.oop.objects.Directory;
import java.util.List;

public interface DirectoryDAO {

    //GET METHODS
    public Directory getDirectoryById (int directoryId);

    public List<Directory> getDirectoryList ();

    public Directory getLargestDirectory (int directoryId);

    public Directory getDirectoryMostFiles (int directoryId);

    //EXECUTE METHODS
    public int insertDirectory (Directory directory);
    public boolean updateDirectory (Directory directory);
    public boolean deleteDirectory (int directoryId);
    public boolean deleteAllDirectory (int directoryId);

}
