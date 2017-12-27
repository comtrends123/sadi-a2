package model;

import javax.persistence.*;

@Entity
@Table
public class Answer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private int aid;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private boolean correctcheck;

    public Answer() {
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrectcheck() {
        return correctcheck;
    }

    public void setCorrectcheck(boolean correctcheck) {
        this.correctcheck = correctcheck;
    }
}
