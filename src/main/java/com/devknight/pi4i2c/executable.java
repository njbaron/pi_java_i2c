package com.devknight.pi4i2c;

import com.devknight.pi4i2c.lcd.lcd;
import com.devknight.pi4i2c.mpu6050.mpu6050;
import com.pi4j.component.lcd.impl.I2CLcdDisplay;

public class executable {



    public static void main(String[] args) {
        System.out.println("Welcome to JavaI2C");
        long x;
        long y;
        long z;
        try {
            mpu6050 mpu = new mpu6050((byte) 0x69);
            I2CLcdDisplay lcd = new I2CLcdDisplay(2,16,1,0x27,3,0,1,2,7,6,5,4);
            lcd.setBacklight(true);
            while (true) {
                System.out.println("MPU Temp: " + mpu.get_temp());
                double[] accelData = mpu.get_accel_data();
                System.out.println("MPU Accel x: " + accelData[0]);
                System.out.println("MPU Accel y: " + accelData[1]);
                System.out.println("MPU Accel z: " + accelData[2]);
                double[] gyroData = mpu.get_gyro_data();
                System.out.println("MPU Gyro x: " + gyroData[0]);
                System.out.println("MPU Gyro y: " + gyroData[1]);
                System.out.println("MPU Gyro z: " + gyroData[2]);

                x = Math.round(accelData[0]);
                y = Math.round(accelData[1]);
                z = Math.round(accelData[2]);

                lcd.write(0,"x: " + String.format("%03d", x) + " y: " + String.format("%03d", y));
                lcd.write(1,"z: " + String.format("%03d", z) , 2);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
