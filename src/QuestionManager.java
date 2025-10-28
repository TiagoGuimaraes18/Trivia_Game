import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionManager{
    public List<Question> getQuestionsByLevel(int l,int i){
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE levels_id = ? and id != ? ORDER BY RAND() LIMIT 2";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,l);
            stmt.setInt(2,i);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Cria objeto User com os dados do banco
                    int id = rs.getInt("id");
                    String questionText = rs.getString("questionText");
                    String optionA = rs.getString("option_a");
                    String optionB = rs.getString("option_b");
                    String optionC = rs.getString("option_c");
                    String optionD = rs.getString("option_d");
                    String correctOption = rs.getString("correct_option");
                    int category = rs.getInt("category_id");
                    int level = rs.getInt("levels_id");
                    
                    Question q = new Question(id,questionText,optionA,optionB,optionC,optionD,correctOption,category,level);
                    list.add(q);
                }
            } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list;
    } 
    
    public List<Category> getRandomCategories(){
        List<Category> list = new ArrayList<>();
        String sql = "SELECT DISTINCT id,category FROM categories ORDER BY RAND() LIMIT 3";
        try(Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String cat = rs.getString("category");
                Category category = new Category(id,cat);
                list.add(category);
            }
            
        }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return list; 
    }         
    
    public Question getRandomQuestion(int level, int categoryId){
        Question q = null;
        String sql = "SELECT * FROM questions WHERE category_id = ? and levels_id = ? ORDER BY RAND() LIMIT 1";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, categoryId);
                stmt.setInt(2,level);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                int id = rs.getInt("id");
                    String questionText = rs.getString("questionText");
                    String optionA = rs.getString("option_a");
                    String optionB = rs.getString("option_b");
                    String optionC = rs.getString("option_c");
                    String optionD = rs.getString("option_d");
                    String correctOption = rs.getString("correct_option");
                    int cat = rs.getInt("category_id");
                    int l = rs.getInt("levels_id");
                    
                    q = new Question(id,questionText,optionA,optionB,optionC,optionD,correctOption,cat,l);
            }

            }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return q;
    }  
}