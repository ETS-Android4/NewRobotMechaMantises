package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class MantisesClass {
    LinearOpMode op = null;
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
    public MantisesClass(LinearOpMode opMode){
        op = opMode;
        lineararm = op.hardwareMap.get(DcMotor.class, "linear");

        intakebox = op.hardwareMap.get(Servo.class, "intakebox");

        intake = op.hardwareMap.get(DcMotor.class, "intake");

        rotatearm = op.hardwareMap.get(Servo.class, "rarm");

        carousel_arm = op.hardwareMap.get(DcMotor.class, "carousel");

        rotateintake = op.hardwareMap.get(DcMotor.class, "ri");

        fl_wheel = op.hardwareMap.get(DcMotor.class, "fl");
        fr_wheel = op.hardwareMap.get(DcMotor.class, "fr");
        bl_wheel = op.hardwareMap.get(DcMotor.class, "bl");
        br_wheel = op.hardwareMap.get(DcMotor.class, "br");

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
       // rotateintake.setPower(0);

        rotateintake.setTargetPosition((384/4)/2);
        rotateintake.setPower(0.3);
        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(rotateintake.isBusy()){

        }


        rotatearm.setPosition(0.525);
        intakebox.setPosition(0);


        rotateintake.setTargetPosition(0);
        rotateintake.setPower(0.1);
        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (rotateintake.isBusy()){

        }
       // rotateintake.setPower(0);



    }
    public void waitIfRIBusy(){
        while (rotateintake.isBusy()){
            op.telemetry.addData("Rotating", "Intake");
            op.telemetry.update();

        }
    }
    public void rotateIntakeUp(){
        rotateintake.setTargetPosition((384/4)/2);
        rotateintake.setPower(0.3);
        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitIfRIBusy();
    }
    public void rotateIntakeDown(){
        rotateintake.setTargetPosition(0);
        rotateintake.setPower(0.1);
        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitIfRIBusy();
        rotateintake.setPower(0);
    }
    public void mechanum(){
        double y = -op.gamepad1.left_stick_y; // Remember, this is reversed!
        double x = op.gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = op.gamepad1.right_stick_x;

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
    }
    public void move(int inches, int direction, double power, double maxtime) {
        int fl_wheel_tick = fl_wheel.getCurrentPosition();
        int bl_wheel_tick = bl_wheel.getCurrentPosition();
        int fr_wheel_tick = fr_wheel.getCurrentPosition();
        int br_wheel_tick = br_wheel.getCurrentPosition();
//        fl_wheel.setTargetPosition(inches * -41);
//        fr_wheel.setTargetPosition(inches * 41);
//        br_wheel.setTargetPosition(inches * -41);
//        bl_wheel.setTargetPosition(inches * 41);
        switch (direction) {
            case 1:
                fl_wheel.setTargetPosition(fl_wheel_tick+inches * 41);
                fr_wheel.setTargetPosition(fr_wheel_tick+inches * 41);
                br_wheel.setTargetPosition(br_wheel_tick+inches * 41);
                bl_wheel.setTargetPosition(bl_wheel_tick+inches * 41);
                break;
            case 2:
                fl_wheel.setTargetPosition(fl_wheel_tick+inches * 41);
        fr_wheel.setTargetPosition(fr_wheel_tick+inches * -41);
        br_wheel.setTargetPosition(br_wheel_tick+inches * 41);
        bl_wheel.setTargetPosition(bl_wheel_tick+inches * -41);
                break;
            case 3:
                fl_wheel.setTargetPosition(fl_wheel_tick+inches * -41);
                fr_wheel.setTargetPosition(fr_wheel_tick+inches * 41);
                br_wheel.setTargetPosition(br_wheel_tick+inches * -41);
                bl_wheel.setTargetPosition(bl_wheel_tick+inches * 41);
                break;
            case 4:
                fl_wheel.setTargetPosition(fl_wheel_tick+inches * -41);
                fr_wheel.setTargetPosition(fr_wheel_tick+inches * -41);
                br_wheel.setTargetPosition(br_wheel_tick+inches * -41);
                bl_wheel.setTargetPosition(bl_wheel_tick+inches * -41);
                break;


        }
        fl_wheel.setPower(power);
        fr_wheel.setPower(power);
        br_wheel.setPower(power);
        bl_wheel.setPower(power);



        fl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double runtime = op.getRuntime();

        while (bl_wheel.isBusy() || fr_wheel.isBusy() || fl_wheel.isBusy() || br_wheel.isBusy()) {
                if(op.getRuntime()>runtime+maxtime){
                    break;
                }
        }
//        fl_wheel.setPower(0);
//        fr_wheel.setPower(0);
//        br_wheel.setPower(0);
//        bl_wheel.setPower(0);


    }
    public void turn(int degrees, int direction){
        int fl_wheel_tick = fl_wheel.getCurrentPosition();
        int bl_wheel_tick = bl_wheel.getCurrentPosition();
        int fr_wheel_tick = fr_wheel.getCurrentPosition();
        int br_wheel_tick = br_wheel.getCurrentPosition();
        switch (direction) {
            case 1:
                fl_wheel.setTargetPosition(fl_wheel_tick+(degrees * -41));
                fr_wheel.setTargetPosition(fr_wheel_tick+(degrees * 41));
                br_wheel.setTargetPosition(br_wheel_tick+(degrees * 41));
                bl_wheel.setTargetPosition(bl_wheel_tick+ (degrees * -41));
                break;
            case 2:
                fl_wheel.setTargetPosition(fl_wheel_tick+degrees * 41);
                fr_wheel.setTargetPosition(fr_wheel_tick+degrees * -41);
                br_wheel.setTargetPosition(br_wheel_tick+degrees * -41);
                bl_wheel.setTargetPosition(bl_wheel_tick+degrees * 41);
                break;

        }
        fl_wheel.setPower(1);
        fr_wheel.setPower(1);
        br_wheel.setPower(1);
        bl_wheel.setPower(1);

        fl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (bl_wheel.isBusy() || fr_wheel.isBusy() || fl_wheel.isBusy() || br_wheel.isBusy()) {

        }
//        fl_wheel.setPower(0);
//        fr_wheel.setPower(0);
//        br_wheel.setPower(0);
//        bl_wheel.setPower(0);





    }

    public void curve2(int inches, int direction){
        int fl_wheel_tick = fl_wheel.getCurrentPosition();
        int bl_wheel_tick = bl_wheel.getCurrentPosition();
        int fr_wheel_tick = fr_wheel.getCurrentPosition();
        int br_wheel_tick = br_wheel.getCurrentPosition();



        fl_wheel.setTargetPosition(fl_wheel_tick+inches * 41);
        fr_wheel.setTargetPosition(fr_wheel_tick+inches * 41);
        br_wheel.setTargetPosition(br_wheel_tick+inches * 41);
        bl_wheel.setTargetPosition(bl_wheel_tick+inches * 41);
        //switch(direction){
        //  case 1:
        fl_wheel.setPower(0.215);
        fr_wheel.setPower(0.2);
        br_wheel.setPower(0.2);
        bl_wheel.setPower(0.215);
        //case 2:
        // fl_wheel.setPower(0.22);
        //fr_wheel.setPower(0.2);
        //br_wheel.setPower(0.2);
        //bl_wheel.setPower(0.22);
        // }




        fl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (bl_wheel.isBusy() || fr_wheel.isBusy() || fl_wheel.isBusy() || br_wheel.isBusy()) {

        }
//        fl_wheel.setPower(0);
//        fr_wheel.setPower(0);
//        br_wheel.setPower(0);
//        bl_wheel.setPower(0);





    }

    public void curve(int inches, int direction){
        int fl_wheel_tick = fl_wheel.getCurrentPosition();
        int bl_wheel_tick = bl_wheel.getCurrentPosition();
        int fr_wheel_tick = fr_wheel.getCurrentPosition();
        int br_wheel_tick = br_wheel.getCurrentPosition();



        fl_wheel.setTargetPosition(fl_wheel_tick+inches * 41);
        fr_wheel.setTargetPosition(fr_wheel_tick+inches * 41);
        br_wheel.setTargetPosition(br_wheel_tick+inches * 41);
        bl_wheel.setTargetPosition(bl_wheel_tick+inches * 41);
        //switch(direction){
          //  case 1:
                fl_wheel.setPower(0.2);
                fr_wheel.setPower(0.215);
                br_wheel.setPower(0.215);
                bl_wheel.setPower(0.2);
            //case 2:
               // fl_wheel.setPower(0.22);
                //fr_wheel.setPower(0.2);
                //br_wheel.setPower(0.2);
                //bl_wheel.setPower(0.22);
       // }




        fl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (bl_wheel.isBusy() || fr_wheel.isBusy() || fl_wheel.isBusy() || br_wheel.isBusy()) {

        }
//        fl_wheel.setPower(0);
//        fr_wheel.setPower(0);
//        br_wheel.setPower(0);
//        bl_wheel.setPower(0);





    }


}