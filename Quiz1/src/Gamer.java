public class Gamer {
    private String name;
    private int score;

    public Gamer(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Gamer gamer = (Gamer) obj;
        return name.equals(gamer.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
