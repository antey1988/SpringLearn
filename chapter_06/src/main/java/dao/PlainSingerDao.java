package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import entities.Singer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao implements  SingerDao{
    private static final String DB_URL = "jdbc:h2:" +
            "/home/oleg/IdeaProjects/Spring/chapter_06/src/main/resources/database/MUSIC";

    private static Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("Problem loading DB Driver!", ex);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,"dba", "sql");
    }

    private void closeConnection(Connection connection) {
        if (connection == null)
            return;

        try {
            connection.close();
        } catch (SQLException ex) {
            logger.error("Problem closing connection to the database!", ex);
        }
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from singer");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Singer singer = new Singer();
                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
                result.add(singer);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem when executing SELECT!", ex);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {
        Connection connection = null;
        try {
            connection= getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into Singer " +
                    "(first_name, last_name, birth_date)" +
                    "values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.execute();
            ResultSet generetrdKeys = statement.getGeneratedKeys();
            if (generetrdKeys.next()) {
                singer.setId(generetrdKeys.getLong(1));
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing INSERT!", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {
        Connection connection = null;
        try {
            connection= getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from Singer where id=?");
            statement.setLong(1, singerId);
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing DELETE!", ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Singer> findAllWithDetail() {
        return null;
    }

    @Override
    public void insertWithDetail(Singer singer) {

    }
}
