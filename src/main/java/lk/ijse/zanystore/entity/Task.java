package lk.ijse.zanystore.entity;

public class Task {
    private int task_id;
    private String task_name;
    private String date;

    public Task(){}

    public Task(String task_name, String date){
        this.task_name = task_name;
        this.date = date;
    }

    public  Task(int task_id, String task_name, String date) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.date = date;
    }
    public int getTask_id() {
        return task_id;
    }
    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
    public String getTask_name() {
        return task_name;
    }
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
