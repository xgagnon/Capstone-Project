package models;

public class Counter {
    private String id;
    private int seq;


    public int getSeq() {
        return seq;
    }

    public String getId() {
        return id;
    }

    public void incrementSeq() {
        this.seq++;
    }

}
