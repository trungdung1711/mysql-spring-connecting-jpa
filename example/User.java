import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class User 
{
    private int id;
    private String name;

    // Constructors, getters, and setters
    public User(int id, String name) 
    {
        this.id = id;
        this.name = name;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public interface UserDao //Data access object
{
    User getUserById(int id);
    void saveUser(User user);
    void deleteUser(int id);
}


public class UserDaoImpl implements UserDao 
{
    private DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) 
    {
        this.dataSource = dataSource;
    }

    @Override
    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        String query = "INSERT INTO users (name) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
