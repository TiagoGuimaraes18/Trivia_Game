import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager{

    public User login(String username, String password) {
        User user = null;
        String sql = "SELECT id,username, password,answers,correct,incorrect,victory FROM users WHERE username = ? AND password = ?";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Cria objeto User com os dados do banco
                    int uid = rs.getInt("id");
                    String uname = rs.getString("username");
                    String upass = rs.getString("password");
                    int uanswers = rs.getInt("answers");
                    int ucorrect = rs.getInt("correct");
                    int uincorrect = rs.getInt("incorrect");
                    int uvictory = rs.getInt("victory");
                    user = new User(uid, uname, upass, uanswers,ucorrect,uincorrect,uvictory);
                    System.out.println("Login successfully: " + uname);
                } else {
                    System.out.println("Login unsuccessfully");
                }
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return user;
    }
    
    public boolean register(User user){
        boolean flag = false;
        String sqlcheck = "SELECT username FROM users WHERE username = ?";
        String sqlInsert = "INSERT INTO users (username, password, answers,correct,incorrect,victory) VALUES (?, ?, 0,0,0,0)";

        try(Connection conn = Database.getConnection()){
            PreparedStatement stmtCheck = conn.prepareStatement(sqlcheck);
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
            
            stmtCheck.setString(1, user.getUsername());
            
            ResultSet rs1 = stmtCheck.executeQuery();
            if((rs1.next())){
                System.out.println("Account already exists");
            }
            else{
                stmtInsert.setString(1, user.getUsername());
                stmtInsert.setString(2, user.getPassword());
                stmtInsert.executeUpdate();
                flag = true;
                System.out.println("Registered successfully");
            }
        } 
            
        catch (SQLException e) { 
            System.err.println("Error: " + e.getMessage()); 
        }    
            return flag;
    }
    
    public List<User> getAllUsers(){
        String sql = "Select*From users ORDER BY victory DESC,correct DESC";
        List<User> list = new ArrayList<>();
        try(Connection conn = Database.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int uid = rs.getInt("id");
                String uname = rs.getString("username");
                String upass = rs.getString("password");
                int uanswers = rs.getInt("answers");
                int ucorrect = rs.getInt("correct");
                int uincorrect = rs.getInt("incorrect");
                int uvictory = rs.getInt("victory");
                User u = new User(uid, uname, upass, uanswers,ucorrect,uincorrect,uvictory);
                list.add(u);
            }

            }catch (SQLException e) { 
                System.err.println("Error: " + e.getMessage());
            }    

            return list;
    }

    public void updateUserStats(User player){
        String sql = "UPDATE users SET answers = ?,correct = ?, incorrect = ?, victory = ? WHERE id = ?";
        
         try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1,player.getAnswers());
                stmt.setInt(2,player.getCorrect());
                stmt.setInt(3,player.getIncorrect());
                stmt.setInt(4,player.getVictory());
                stmt.setInt(5,player.getId());
                stmt.executeUpdate();

            }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

