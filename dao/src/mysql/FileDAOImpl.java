package mysql;

import com.astontech.oop.dao.FileDAO;
import com.astontech.oop.objects.Directory;
import com.astontech.oop.objects.File;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl extends MySQL implements FileDAO {
    @Override
    public File getFileById(int fileId) {
        Connect();
        File file = null;
        try {
            String storedProc = "{call GetFile(?,?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setInt(1, GET_BY_ID);
            callStmt.setInt(2, fileId);
            ResultSet rs = callStmt.executeQuery();

            if (rs.next()) {
                file = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return file;
    }

    @Override
    public List<File> getFileList() {
        Connect();
        List<File> fileList = new ArrayList<>();

        try {
            String storedProc = "{call GetFile(?,?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setInt(1, GET_COLLECTION);
            callStmt.setInt(2, 0);
            ResultSet rs = callStmt.executeQuery();

            while (rs.next()) {
                fileList.add(HydrateObject(rs));
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return fileList;
    }

    @Override
    public File getByType(String fileType) {
        Connect();
        File file = null;
        try {
            String storedProc = "{call GetFileByType(?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setString(1, fileType);
            ResultSet rs = callStmt.executeQuery();

            if (rs.next()) {
                file = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return file;
    }

    @Override
    public File getLargest(int fileId) {
        Connect();
        File file = null;
        try {
            String storedProc = "{call GetLargest(?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setInt(1, 70);
            ResultSet rs = callStmt.executeQuery();

            while (rs.next()) {
                file = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return file;
    }

    @Override
    public int insertFile(File file) {
        Connect();
        int id = 0;

        try {
            String storedProc = "{call ExecFile(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(storedProc);

            cstmt.setInt(1, INSERT);
            cstmt.setInt(2, 0);
            cstmt.setString(3, file.getFileName());
            cstmt.setString(4, file.getFileType());
            cstmt.setDouble(5, file.getFileSize());
            cstmt.setString(6, null);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException sqlex) {
            logger.error(sqlex);
        }

        return id;
    }

    @Override
    public boolean updateFile(File file) {
        Connect();
        int id = 0;

        try {
            String sp = "{call ExecFile(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(sp);

            cstmt.setInt(1, UPDATE);
            cstmt.setInt(2, file.getFileId());
            cstmt.setString(3, file.getFileName());
            cstmt.setString(4, file.getFileType());
            cstmt.setDouble(5, file.getFileSize());
            cstmt.setInt(6, file.getDirectoryId());

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException sqlex) {
            logger.error(sqlex);
        }

        return id > 0;
    }

    @Override
    public boolean deleteFile(int fileId) {
        Connect();
        int id = 0;

        try {
            String sp = "{call ExecFile(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(sp);

            cstmt.setInt(1, DELETE);
            cstmt.setInt(2, fileId);
            cstmt.setString(3, "");
            cstmt.setString(4, "" );
            cstmt.setDouble(5, 0);
            cstmt.setInt(6, 0);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException sqlex) {
            logger.error(sqlex);
        }

        return id > 0;
    }

    @Override
    public boolean deleteAllFile(int fileId) {
        Connect();
        int id = 0;

        try {
            String sp = "{call ExecFile(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(sp);

            cstmt.setInt(1, DELETE_ALL);
            cstmt.setInt(2, 0);
            cstmt.setString(3, "");
            cstmt.setString(4, "" );
            cstmt.setDouble(5, 0);
            cstmt.setInt(6, 0);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException sqlex) {
            logger.error(sqlex);
        }

        return id > 0;
    }

    private static File HydrateObject (ResultSet rs) throws SQLException {
        File file = new File();
        file.setFileId(rs.getInt(1));
        file.setFileName(rs.getString(2));
        file.setFileType(rs.getString(3));
        file.setFileSize(rs.getDouble(4));
        file.setDirectoryId(rs.getInt(5));

        return file;
    }

}
