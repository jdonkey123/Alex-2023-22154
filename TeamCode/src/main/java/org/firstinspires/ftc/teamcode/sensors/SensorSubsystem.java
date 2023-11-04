package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SensorSubsystem {

    private RevColorSensorV3 colorFront, colorRear;

    public SensorSubsystem(HardwareMap hardwareMap) {
        colorFront = hardwareMap.get(RevColorSensorV3.class, "colorFront");
        colorRear = hardwareMap.get(RevColorSensorV3.class, "colorRear");
    }

    public int[] getFront() {
        int[] colors = new int[3];
        colors[0] = colorFront.red();
        colors[1] = colorFront.green();
        colors[2] = colorFront.blue();
        return colors;
    }

    public int[] getRear() {
        int[] colors = new int[3];
        colors[0] = colorRear.red();
        colors[1] = colorRear.green();
        colors[2] = colorRear.blue();
        return colors;
    }

    public int getFrontColor() {
        if (colorFront.blue()>colorFront.red()) {
            return 1;
        }
        if (colorFront.red()>colorFront.blue()) {
            return 1;
        }
        if (colorFront.green()>colorFront.blue() && colorFront.green()>colorFront.red()) {
            return 3;
        }
        return 3;
    }

    public int getRearColor() {
        if (colorRear.blue()>colorRear.red()) {
            return 1;
        }
        if (colorRear.red()>colorRear.blue()) {
            return 2;
        }
        if (colorRear.green()>colorRear.blue() && colorRear.green()>colorRear.red()) {
            return 3;
        }
        return 0;
    }

    public int getFrontBlue() {
        return colorFront.blue();
    }

    public int getFrontRed() {
        return colorFront.red();
    }

    public int getFrontGreen() {
        return colorFront.green();
    }

    public int getRearBlue() {
        return colorRear.blue();
    }

    public int getRearRed() {
        return colorRear.red();
    }

    public int getRearGreen() {
        return colorRear.green();
    }

    public boolean verifyFrontBlue() {
        if (getFrontColor() == 1) {
            // Blue
            return true;
        } else if(getFrontColor() == 3) {
            // Green
            return false;
        }
        return false;
    }

    public boolean verifyFrontRed() {
        if (getFrontColor() == 2) {
            // Red
            return true;
        } else if(getFrontColor() == 3) {
            // Green
            return false;
        }
        return false;
    }

    public boolean verifyRearBlue() {
        if (getRearColor() == 1) {
            // Blue
            return true;
        } else if (getRearColor() == 3) {
            // Green
            return false;
        }
        return false;
    }

    public boolean verifyRearRed() {
        if (getRearColor() == 2) {
            // Red
            return true;
        } else if (getRearColor() == 3) {
            // Green
            return false;
        }
        return false;
    }

}