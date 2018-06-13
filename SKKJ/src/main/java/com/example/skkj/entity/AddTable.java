package com.example.skkj.entity;

public class AddTable {

    private String id;

    private String tableName;

    private String time;

    private String endTime;//结束时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "AddTable{" +
                "id='" + id + '\'' +
                ", tableName='" + tableName + '\'' +
                ", time='" + time + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
