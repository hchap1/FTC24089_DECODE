package com.core.tasks;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.List;

public abstract class TaskOpMode extends LinearOpMode {
    private TaskBase task;
    private List<LynxModule> hubs;

    abstract void spawn();

    /**
     * Empty by default, allows users to override with something they want to happen once all commands in this iteration have been executed.
     * This could be used for something like bulk reading, updating a counter, ect.
     */
    void mainloop() {

    }

    @Override
    public void runOpMode() {

        this.spawn();

        // Bulk hardware operations
        this.hubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : this.hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
            hub.clearBulkCache();
        }

        if (this.isStopRequested()) return;
        this.waitForStart();

        boolean running = true;
        task.init();

        while (running) {

            // Fully update all sensor values, motor positions, ect, once per loop cycle.
            for (LynxModule hub : this.hubs) {
                hub.clearBulkCache();
            }

            this.task.run();
            if (this.task.finished()) {
                running = false;
                task.end(false);
            }
            if (!this.opModeIsActive()) {
                running = false;
                task.end(true);
            }

            this.mainloop();
        }
    }
}
