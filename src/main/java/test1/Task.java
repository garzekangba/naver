package test1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjihe
 *
 * @date 2020-04-11
 */
public class Task {

    /**
     * task name
     */
    private String name;

    /**
     * is this task has been executed
     */
    private boolean executed;

    /**
     * the count of tasks that need be done before this task execute
     */
    private int dependencyCount;

    /**
     * the tasks which depend on this task
     */
    private List<Task> targets;

    public Task(String name, List<Task> targets,int dependencyCount){
        this.executed=false;
        this.name=name;
        this.dependencyCount=dependencyCount;
        this.targets=targets;
    }

    private void execute(){
        executed=true;
        System.out.println("Task "+name);
        targets.forEach(item->item.dependencyCount--);
    }

    public static List<Task> executeTasks(List<Task> inputs){
        List<Task> result=new ArrayList<>();
        int numOfExecuted=0;
        while (numOfExecuted<inputs.size()){
            for(Task task:inputs){
                if(!task.executed && task.dependencyCount==0){
                    task.execute();
                    result.add(task);
                    numOfExecuted++;
                }
            }
        }
        return result;
    }
}

