public class Category {
    private int id;
    private String nameCategory;

    // Construtor vazio
    public Category() {
        this.id = 0;
        this.nameCategory = "";
    }

    // Construtor completo
    public Category(int id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    // Construtor de cópia
    public Category(Category c) {
        this.id = c.getId();
        this.nameCategory = c.getNameCategory();
    }

    // Getters
    public int getId() { return this.id; }
    public String getNameCategory() { return this.nameCategory; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNameCategory(String nameCategory) { this.nameCategory = nameCategory; }

    // toString — representação textual do objeto
    @Override
    public String toString() {
        return "Id: " + id +
               "\nCategory Name: " + nameCategory;
    }

}