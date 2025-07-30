package com.core.commands;

public class Task extends TaskBase {
    private TaskBase task;
    private boolean done;

    public Task(TaskBase task) {
        this.task = task;
        this.done = false;
    }

    @Override
    public void init() {
        this.task.init();
        this.done = false;
    }

    @Override
    public void run() {
        if (!done) {
            this.task.run();
        }
    }

    @Override
    public boolean finished() {
        if (this.done) return true;
        return this.task.finished();
    }

    @Override
    public void end(boolean interrupted) {
        this.done = true;
        this.task.end(interrupted);
    }

    public Task then(Task that) {
        return new Series(this, that).task();
    }

    public Task with(Task that) {
        return new Parallel(this, that).task();
    }
}
