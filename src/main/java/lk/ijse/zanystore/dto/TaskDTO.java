/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class TaskDTO {
    private int task_id;
    private String task_name;
    private String task_date;

    public TaskDTO(int task_id, String task_name, String task_date) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_date = task_date;
    }

    public TaskDTO(String task_name, String task_date) {
        this.task_name = task_name;
        this.task_date = task_date;
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

    public String getTask_date() {
        return task_date;
    }

    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }

    @Override
    public String toString() {
        return "TaskDTO{" + "task_id=" + task_id + ", task_name=" + task_name + ", task_date=" + task_date + '}';
    }
    
    
}
