/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
//@Disabled
public class TeleOpRun2 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor lineararm;
    public Servo intakebox;
    public DcMotor intake;
    public Servo rotatearm;
    public DcMotor fl_wheel;
    public DcMotor fr_wheel;
    public DcMotor bl_wheel;
    public DcMotor br_wheel;
    public DcMotor carousel_arm;
    public DcMotor rotateintake;

    private static final DcMotor.RunMode encoder_true = DcMotor.RunMode.RUN_USING_ENCODER;
    private static final DcMotor.RunMode encoder_false = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    private static final DcMotor.RunMode reset_encoder = DcMotor.RunMode.STOP_AND_RESET_ENCODER;
    private static final DcMotor.Direction direction_forward = DcMotor.Direction.FORWARD;
    private static final DcMotor.Direction direction_reverse = DcMotor.Direction.REVERSE;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        lineararm = hardwareMap.get(DcMotor.class, "linear");

        intakebox = hardwareMap.get(Servo.class, "intakebox");

        intake = hardwareMap.get(DcMotor.class, "intake");

        rotatearm = hardwareMap.get(Servo.class, "rarm");

        carousel_arm = hardwareMap.get(DcMotor.class, "carousel");

        rotateintake = hardwareMap.get(DcMotor.class, "ri");

        fl_wheel = hardwareMap.get(DcMotor.class, "fl");
        fr_wheel = hardwareMap.get(DcMotor.class, "fr");
        bl_wheel = hardwareMap.get(DcMotor.class, "bl");
        br_wheel = hardwareMap.get(DcMotor.class, "br");

        intake.setMode(encoder_false);
        intake.setDirection(direction_reverse);



        lineararm.setDirection(direction_forward);
        lineararm.setMode(reset_encoder);
        lineararm.setMode(encoder_true);
        lineararm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lineararm.setPower(0);

        fl_wheel.setDirection(direction_reverse);
        fl_wheel.setMode(reset_encoder);
        fl_wheel.setMode(encoder_true);
        fl_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl_wheel.setPower(0);

        fr_wheel.setDirection(direction_forward);
        fr_wheel.setMode(reset_encoder);
        fr_wheel.setMode(encoder_true);
        fr_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr_wheel.setPower(0);

        bl_wheel.setDirection(direction_reverse);
        bl_wheel.setMode(reset_encoder);
        bl_wheel.setMode(encoder_true);
        bl_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_wheel.setPower(0);

        br_wheel.setDirection(direction_forward);
        br_wheel.setMode(reset_encoder);
        br_wheel.setMode(encoder_true);
        br_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_wheel.setPower(0);

        carousel_arm.setDirection(direction_forward);
        carousel_arm.setMode(reset_encoder);
        carousel_arm.setMode(encoder_true);
        carousel_arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel_arm.setPower(0);

        rotateintake.setDirection(direction_reverse);
        rotateintake.setMode(reset_encoder);
        rotateintake.setMode(encoder_true);
        rotateintake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotateintake.setPower(0);

        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateintake.setTargetPosition((384/4)/2);
        rotateintake.setPower(0.3);
        while(rotateintake.isBusy()){

        }


        rotatearm.setPosition(0.52);
        intakebox.setPosition(0);

        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateintake.setTargetPosition(0);
        rotateintake.setPower(0.1);
        while (rotateintake.isBusy()){

        }
        rotateintake.setPower(0);
        //rotatearm.setPosition(0.5);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry


        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        if(gamepad2.y) {
            lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lineararm.setTargetPosition((384*2)+235);
            lineararm.setPower(0.2);
        }
        else if(gamepad2.a) {
            lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lineararm.setTargetPosition(0);
            lineararm.setPower(0.2);
        }
        else if(gamepad2.b){
            intakebox.setPosition(0.45);

        }
        else if(gamepad1.right_bumper){
            carousel_arm.setPower(1);
        }else if(gamepad1.left_bumper){
            carousel_arm.setPower(0);
        }
        else if (gamepad2.right_bumper){
            rotateintake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotateintake.setTargetPosition((384/4)/2);
            rotateintake.setPower(0.3);
            double runtimeNow = runtime.time();
            while (rotateintake.isBusy()){

            }
            intakebox.setPosition(0.45);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotateintake.setTargetPosition(0);
            rotateintake.setPower(0.3);
            while (rotateintake.isBusy()){

            }
            rotateintake.setPower(0);
            intake.setPower(-1);


        }else if(gamepad2.left_bumper){
            intake.setPower(0);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotateintake.setTargetPosition(24);
            rotateintake.setPower(0.3);
            intakebox.setPosition(0.5);
            intakebox.setPosition(0);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotateintake.setTargetPosition(0);
            rotateintake.setPower(0.1);

            rotateintake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }else if(gamepad2.dpad_left){
            rotatearm.setPosition(0.2);
        }else if(gamepad2.dpad_right){
            rotatearm.setPosition(0.8);
        }else if (gamepad2.dpad_up){
            rotatearm.setPosition(0.52);
        }else if(gamepad2.x){
            intakebox.setPosition(0);
        }else if(gamepad2.dpad_down){
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotateintake.setTargetPosition((384/4)/2);
            rotateintake.setPower(0.3);
            while (rotateintake.isBusy()){
                telemetry.addData("Rotating", "Intake");
                telemetry.update();

            }
            intakebox.setPosition(0.4);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotateintake.setTargetPosition(0);
            rotateintake.setPower(0.1);

            rotateintake.setPower(0);
        }
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) ;
        double backLeftPower = (y - x + rx) ;
        double frontRightPower = (y - x - rx) ;
        double backRightPower = (y + x - rx);

        fl_wheel.setPower(frontLeftPower);
        bl_wheel.setPower(backLeftPower);
        fr_wheel.setPower(frontRightPower);
        br_wheel.setPower(backRightPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
