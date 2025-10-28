public class User{
    private int id;
    private String username;
    private String password;
    private int answers;
    private int correct;
    private int incorrect;
    private int victory;

    public User(){
        this.id = 0;
        this.username = "";
        this.password = "";
        this.answers = 0;
        this.correct = 0;
        this.incorrect = 0;
        this.victory = 0;
    }

    public User(int id,String username,String password,int answers,int correct,int incorrect,int victory){
        this.id = id;
        this.username = username;
        this.password = password;
        this.answers = answers;
        this.correct = correct;
        this.incorrect = incorrect;
        this.victory = victory;
    }
    
    public User(String username, String password, int answers, int correct, int incorrect, int victory) {
    this.id = 0; 
    this.username = username;
    this.password = password;
    this.answers = answers;
    this.correct = correct;
    this.incorrect = incorrect;
    this.victory = victory;
    }
    
    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.answers = user.getAnswers();
        this.correct = user.getCorrect();
        this.incorrect = user.getIncorrect();
        this.victory = user.getVictory();
   }

    public int getId() {return this.id; }
    public String getUsername() {return this.username; }
    public String getPassword() {return this.password; }
    public int getAnswers() {return this.answers; }
    public int getCorrect() {return this.correct; }    
    public int getIncorrect() {return this.incorrect; } 
    public int getVictory() {return this.victory; } 

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) {this.username = username; }
    public void setPassword(String password) {this.password = password; }
    public void setAnswers(int answers) {this.answers = answers; }
    public void setCorrect(int correct) { this.correct = correct; }
    public void setIncorrect(int incorrect) { this.incorrect = incorrect; }
    public void setVictory(int victory) { this.victory = victory; }

    @Override
    public String toString() {
        return "Id: " + id +
           "\nUsername: " + username +
           "\nPassword: " + password +
           "\nAnswers: " + answers +
           "\nCorrect: " + correct +
           "\nIncorrect: " + incorrect +
           "\nVictory: " + victory;
    }
 
    public void incrementAnswers() { this.answers += 1; }
    public void incrementCorrect() { this.correct += 1; }
    public void incrementIncorrect() { this.incorrect += 1; }
    public void incrementVictory() { this.victory += 1; }

}
