package com.core.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LionMotor {
    private DcMotor motor;
    private double position;
    private double power;
    private boolean hasEncoder;

    private LionMotor(HardwareMap hardwareMap, String name, boolean hasEncoder) {
        this.motor = hardwareMap.get(DcMotor.class, name);
        this.position = 0;
        this.power = 0;
        this.hasEncoder = hasEncoder;
    }

    public static LionMotor withoutEncoder(HardwareMap hardwareMap, String name) {
        return new LionMotor(hardwareMap, name, false);
    }

    public static LionMotor withEncoder(HardwareMap hardwareMap, String name) {
        return new LionMotor(hardwareMap, name, true);
    }

    public void setPower(double power) {
        this.power = power;
        this.motor.setPower(power);
    }

    public double getPower() {
        return this.power;
    }

    public double getPosit
}
