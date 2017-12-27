package model;

import javax.persistence.*;

@Entity
@Table
public class UserInfoResult {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int uid;

    @Column(nullable = false, unique = true)
    private int rid;

    public UserInfoResult() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
