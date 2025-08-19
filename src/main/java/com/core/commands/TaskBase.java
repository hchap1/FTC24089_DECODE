package com.core.commands;

public abstract class TaskBase {
    void init() {}
    void run() {}
    boolean finished() { return false; }
    void end(boolean interrupted) { }
}