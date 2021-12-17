package com.astontech.oop.dao;

import com.astontech.oop.objects.File;
import java.util.List;

public interface FileDAO {

    //GET METHODS
    public File getFileById (int fileId);

    public List<File> getFileList ();

    public File getByType (String fileType);

    public File getLargest (int fileId);

    //EXECUTE METHODS
    public int insertFile (File file);
    public boolean updateFile (File file);
    public boolean deleteFile (int fileId);
    public boolean deleteAllFile (int fileId);

}
