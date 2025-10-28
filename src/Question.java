public class Question {    
    int id;
    String questionText;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String correctOption;
    int category;
    int level;

    public Question(){
        this.id = 0;
        this.questionText = "";
        this.optionA = "";
        this.optionB = "";
        this.optionC = "";
        this.optionD = "";
        this.correctOption = "";
        this.category = 0;
        this.level = 0;
    }

     public Question(int id, String questionText, String optionA, String optionB, 
                    String optionC, String optionD, String correctOption, 
                    int category, int level) {
        this.id = id;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        this.category = category;
        this.level = level;
    }

    public Question(Question q) {
        this.id = q.getId();
        this.questionText = q.getQuestionText();
        this.optionA = q.getOptionA();
        this.optionB = q.getOptionB();
        this.optionC = q.getOptionC();
        this.optionD = q.getOptionD();
        this.correctOption = q.getCorrectOption();
        this.category = q.getCategory();
        this.level = q.getLevel();
    }

    // Getters
    public int getId() { return this.id; }
    public String getQuestionText() { return this.questionText; }
    public String getOptionA() { return this.optionA; }
    public String getOptionB() { return this.optionB; }
    public String getOptionC() { return this.optionC; }
    public String getOptionD() { return this.optionD; }
    public String getCorrectOption() { return this.correctOption; }
    public int getCategory() { return this.category; }
    public int getLevel() { return this.level; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public void setOptionA(String optionA) { this.optionA = optionA; }
    public void setOptionB(String optionB) { this.optionB = optionB; }
    public void setOptionC(String optionC) { this.optionC = optionC; }
    public void setOptionD(String optionD) { this.optionD = optionD; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }
    public void setCategory(int category) { this.category = category; }
    public void setLevel(int level) { this.level = level; }

    @Override
    public String toString() {
        return "Id: " + id +
           "\nQuestion: " + questionText +
           "\nA: " + optionA +
           "\nB: " + optionB +
           "\nC: " + optionC +
           "\nD: " + optionD +
           "\nCorrect Option: " + correctOption +
           "\nCategory: " + category +
           "\nLevel: " + level;
    }

}
