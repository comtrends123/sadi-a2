package model;

import javax.persistence.*;

@Entity
@Table
public class QuestionAnswer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int qid;

    @Column(nullable = false, unique = true)
    private int aid;

    public QuestionAnswer() {
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
}
