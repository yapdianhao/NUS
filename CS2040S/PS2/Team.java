class Team {

    int id;
    int score;
    int penalty;

    public Team(int id, int score, int penalty) {
        this.id = id;
        this.score = score;
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "" + this.id;
    }
}