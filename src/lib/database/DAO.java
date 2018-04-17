package lib.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static lib.database.QueryConstants.*;
import static lib.database.SettingTemplates.*;

public class DAO {

    private Connection con;
    private PreparedStatement getFiles;

    public DAO() {

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            getFiles = con.prepareStatement(GET_FILES);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String> getFiles() {

        try {
            ResultSet resultSet = getFiles.executeQuery();
            String fileName;
            ArrayList<String> files = new ArrayList<>();
            while (resultSet.next()){
                fileName = resultSet.getString("fileName");
                files.add(fileName);
            }
            resultSet.close();
            return files;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
