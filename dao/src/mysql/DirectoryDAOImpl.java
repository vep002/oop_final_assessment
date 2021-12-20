package mysql;

import com.astontech.oop.dao.DirectoryDAO;
import com.astontech.oop.objects.Directory;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {
    @Override
    public Directory getDirectoryById(int directoryId) {
        Connect();
        Directory directory = null;
        try {
            String storedProc = "{call GetDirectory(?,?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setInt(1, GET_BY_ID);
            callStmt.setInt(2, directoryId);
            ResultSet rs = callStmt.executeQuery();

            if (rs.next()) {
                directory = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return directory;
    }

    @Override
    public List<Directory> getDirectoryList() {
        Connect();
        List<Directory> directoryList = new ArrayList<>();

        try {
            String storedProc = "{call GetDirectory(?,?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setInt(1, GET_COLLECTION);
            callStmt.setInt(2, 0);
            ResultSet rs = callStmt.executeQuery();

            while (rs.next()) {
                directoryList.add(HydrateObject(rs));
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return directoryList;
    }

    @Override
    public Directory getLargestDirectory () {
        Connect();
        Directory directory = null;
        try {
            String storedProc = "{call GetLargest(?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setInt(1, 50);
            ResultSet rs = callStmt.executeQuery();

            if (rs.next()) {
                directory = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return directory;
    }

    @Override
    public Directory getDirectoryMostFiles() {
        Connect();
        Directory directory = null;
        try {
            String storedProc = "{call GetLargest(?)}";
            CallableStatement callStmt = connection.prepareCall(storedProc);

            callStmt.setInt(1, 60);
            ResultSet rs = callStmt.executeQuery();

            if (rs.next()) {
                directory = HydrateObject(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return directory;
    }

    @Override
    public int insertDirectory(Directory directory) {
        Connect();
        int id = 0;

        try {
            String storedProc = "{call ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(storedProc);

            cstmt.setInt(1, INSERT);
            cstmt.setInt(2, 0);
            cstmt.setString(3, directory.getDirectoryName());
            cstmt.setDouble(4, directory.getDirectorySize());
            cstmt.setString(5, directory.getNumberOfFiles());
            cstmt.setString(6, directory.getPath());

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
    public boolean updateDirectory(Directory directory) {
        Connect();
        int id = 0;

        try {
            String storedProc = "{call ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(storedProc);

            cstmt.setInt(1, UPDATE);
            cstmt.setInt(2, directory.getDirectoryId());
            cstmt.setString(3, directory.getDirectoryName());
            cstmt.setDouble(4, directory.getDirectorySize());
            cstmt.setString(5, directory.getNumberOfFiles());
            cstmt.setString(6, directory.getPath());

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
    public boolean deleteDirectory(int directoryId) {
        Connect();
        int id = 0;

        try {
            String storedProc = "{call ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(storedProc);

            cstmt.setInt(1, DELETE);
            cstmt.setInt(2, directoryId);
            cstmt.setString(3, "");
            cstmt.setDouble(4, 0);
            cstmt.setString(5, "");
            cstmt.setString(6, "");

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
    public boolean deleteAllDirectory(int directoryId) {
        Connect();
        int id = 0;

        try {
            String storedProc = "{call ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = connection.prepareCall(storedProc);

            cstmt.setInt(1, DELETE_ALL);
            cstmt.setInt(2, 0);
            cstmt.setString(3, "");
            cstmt.setDouble(4, 0);
            cstmt.setString(5, "");
            cstmt.setString(6, "");

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException sqlex) {
            logger.error(sqlex);
        }

        return id > 0;
    }

    private static Directory HydrateObject (ResultSet rs) throws SQLException {
        Directory directory = new Directory();
        directory.setDirectoryId(rs.getInt(1));
        directory.setDirectoryName(rs.getString(2));
        directory.setDirectorySize(rs.getDouble(3));
        directory.setNumberOfFiles(rs.getString(4));
        directory.setPath(rs.getString(5));

        return directory;
    }
}
