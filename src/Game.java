import java.util.ArrayList;
import java.util.List;

public class Game {

    private User player;                           
    final private List<Question> currentLevelQuestions;   
    private int currentLevel;                       
    private int currentIndex;                       
    private boolean gameOver;                      
    private Question currentQuestion;              
    final private QuestionManager questionM;              
    final private UserManager userM;                     

    public Game(User player) {
        this.player = player;
        this.currentLevel = 1;                      
        this.currentIndex = 0;
        this.gameOver = false;
        this.currentLevelQuestions = new ArrayList<>();
        this.questionM = new QuestionManager();    
        this.userM = new UserManager();
        this.currentQuestion = null;
    }

    public Game(){
        this.player = new User();
        this.currentLevel = 1;                  
        this.currentIndex = 0;
        this.gameOver = false;
        this.currentLevelQuestions = new ArrayList<>();
        this.questionM = new QuestionManager();   
        this.userM = new UserManager();
        this.currentQuestion = null;
    }

    public List<Question> getCurrentLevelQuestions() {
        return currentLevelQuestions;
    }

    public User getPlayer() {
        return player;
    }

    public int getCurrentLevel(){
        return this.currentLevel;
    }

    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void levelUp(){
        this.currentLevel +=1;
    } 

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void loadQuestions(int category) {
        currentLevelQuestions.clear();

        Question first = questionM.getRandomQuestion(getCurrentLevel(), category);
        System.out.println(first.getQuestionText());
        
        currentLevelQuestions.add(first);
        List<Question> others = questionM.getQuestionsByLevel(currentLevel, first.getId());
        if (others != null && !others.isEmpty()) {
            System.out.println(others.get(0).getQuestionText());
            System.out.println(others.get(1).getQuestionText());
            currentLevelQuestions.addAll(others);
        }

        currentIndex = 0;
        currentQuestion = currentLevelQuestions.get(currentIndex);
    }

    public Question nextQuestion() {
        if(isGameOver()){
            return null;
        }
        
        currentIndex+=1;
        if(currentIndex < currentLevelQuestions.size()){
            currentQuestion = currentLevelQuestions.get(currentIndex);
            return currentQuestion;
        }
        else{
            if(currentLevel == 3){
                setGameOver(true);
                currentQuestion = null;
                player.incrementVictory();
                endGame();
                return null;
            }
            else{
                levelUp();
                currentLevelQuestions.clear();
                currentQuestion = null;
                currentIndex = 0;
                return null;
            }
        }    
    }

    public boolean checkAnswer(String s){
        if (currentQuestion == null) return false;
        boolean correct =currentQuestion.getCorrectOption().equalsIgnoreCase(s);
        player.incrementAnswers();
        if(correct){ 
            player.incrementCorrect();
            return true;
        }   
        else{
            player.incrementIncorrect();
            setGameOver(true);
            endGame();
            return false;
        }
    }

    public void startGame(User player) {
        this.player = player;
        this.gameOver = false;
        this.currentLevel = 1;
        this.currentIndex = -1;
    }

    public void endGame(){
        this.gameOver = true;
        userM.updateUserStats(player);
    }

}
