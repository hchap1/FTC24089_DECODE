package com.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parallel extends TaskBase {

    private List<TaskBase> tasks;

    public Parallel(TaskBase... tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(Arrays.asList(tasks));
    }

    @Override
    public void init() { for (TaskBase task : this.tasks) task.init(); }

    @Override
    public void run() {
        for (TaskBase task : this.tasks) {
            task.run();
            if (task.finished()) {
                task.end(false);
            }
        }
    }

    @Override
    public boolean finished() {
        for (TaskBase task : this.tasks) {
            if (!task.finished()) return false;
        }
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            for (TaskBase task : this.tasks) {
                task.end(true);
            }
        }
    }
}
