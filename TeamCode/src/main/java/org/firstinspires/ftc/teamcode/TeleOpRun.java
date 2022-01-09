package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp!")
public class TeleOpRun extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor lineararm;
    Servo intakebox;
    DcMotor intake;
    Servo rotatearm;
    DcMotor fl_wheel;
    DcMotor fr_wheel;
    DcMotor bl_wheel;
    DcMotor br_wheel;
    DcMotor carousel_arm;
    DcMotor rotateintake;
    DcMotor.RunMode encoder_true = DcMotor.RunMode.RUN_USING_ENCODER;
    DcMotor.RunMode encoder_false = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    DcMotor.RunMode reset_encoder = DcMotor.RunMode.STOP_AND_RESET_ENCODER;
    DcMotor.Direction direction_forward = DcMotor.Direction.FORWARD;
    DcMotor.Direction direction_reverse = DcMotor.Direction.REVERSE;
    @Override
    public void init(){
        telemetry.addData("Initializing", "...");
        telemetry.update();



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

        intake.setDirection(direction_reverse);
        intake.setMode(encoder_false);

        lineararm.setDirection(direction_forward);
        lineararm.setMode(reset_encoder);
        lineararm.setMode(encoder_true);
        lineararm.setPower(0);
        lineararm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl_wheel.setDirection(direction_reverse);
        fl_wheel.setMode(reset_encoder);
        fl_wheel.setMode(encoder_true);
        fl_wheel.setPower(0);
        fl_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fr_wheel.setDirection(direction_forward);
        fr_wheel.setMode(reset_encoder);
        fr_wheel.setMode(encoder_true);
        fr_wheel.setPower(0);
        fr_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bl_wheel.setDirection(direction_reverse);
        bl_wheel.setMode(reset_encoder);
        bl_wheel.setMode(encoder_true);
        bl_wheel.setPower(0);
        bl_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        br_wheel.setDirection(direction_forward);
        br_wheel.setMode(reset_encoder);
        br_wheel.setMode(encoder_true);
        br_wheel.setPower(0);
        br_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        carousel_arm.setDirection(direction_forward);
        carousel_arm.setMode(reset_encoder);
        carousel_arm.setMode(encoder_true);
        carousel_arm.setPower(0);
        carousel_arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rotateintake.setDirection(direction_reverse);
        rotateintake.setMode(reset_encoder);
        rotateintake.setMode(encoder_true);
        rotateintake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rotateintake.setPower(0);

        rotateintake.setTargetPosition((1440/4)/2);
        rotateintake.setPower(0.3);
        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(rotateintake.isBusy()){

        }


        rotatearm.setPosition(0.55);
        intakebox.setPosition(1);


        rotateintake.setTargetPosition(0);
        rotateintake.setPower(0.1);
        rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (rotateintake.isBusy()){

        }
        rotateintake.setPower(0);

    }
    @Override
    public void init_loop(){
        telemetry.addData("Initialized", "Waiting for Start");

    }
    @Override
    public void start(){
        runtime.reset();
    }
    @Override
    public void loop(){
        if(gamepad2.y) {
            lineararm.setTargetPosition((1440*3)-450);
            lineararm.setPower(1);
            lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if(gamepad2.a) {
            lineararm.setTargetPosition(0);
            lineararm.setPower(1);
            lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if(gamepad2.b){
            intakebox.setPosition(0.4);

        }
        else if(gamepad1.right_bumper){
            carousel_arm.setPower(-1);
        }else if(gamepad1.left_bumper){
            carousel_arm.setPower(0);
        }
        else if (gamepad2.right_bumper){
            rotateintake.setTargetPosition((1440/4)/2);
            rotateintake.setPower(0.3);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (rotateintake.isBusy()){

            }
            intakebox.setPosition(0.35);
            rotateintake.setTargetPosition(0);
            rotateintake.setPower(0.3);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (rotateintake.isBusy()){

            }
            rotateintake.setPower(0);
            intake.setPower(-1);


        }else if(gamepad2.left_bumper){
            intake.setPower(0);
            rotateintake.setTargetPosition(90);
            rotateintake.setPower(0.3);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            intakebox.setPosition(0.5);
            intakebox.setPosition(1);
            rotateintake.setTargetPosition(0);
            rotateintake.setPower(0.1);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }else if(gamepad2.dpad_left){
            rotatearm.setPosition(0);
        }else if(gamepad2.dpad_right){
            rotatearm.setPosition(1);
        }else if (gamepad2.dpad_up){
            rotatearm.setPosition(0.55);
        }else if(gamepad2.x){
            intakebox.setPosition(1);
        }else if(gamepad2.dpad_down){
            rotateintake.setTargetPosition((1440/4)/2);
            rotateintake.setPower(0.3);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while(rotateintake.isBusy()){

            }


            rotatearm.setPosition(0.55);
            intakebox.setPosition(0.4);


            rotateintake.setTargetPosition(0);
            rotateintake.setPower(0.1);
            rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (rotateintake.isBusy()){

            }
            rotateintake.setPower(0);

        }
        double y = -gamepad1.right_stick_y;
        double x = gamepad1.right_stick_x ;
        double rx = gamepad1.left_stick_x;

        double denominator = Math.max(Math.abs(y)+Math.abs(x)+Math.abs(rx), 0.5);
        double frontLeftPower = (y+x+rx)/denominator;
        double backLeftPower = (y-x+rx)/denominator;
        double frontRightPower = (y-x-rx)/denominator;
        double backRightPower = (y+x-rx)/denominator;

        fl_wheel.setPower(frontLeftPower/2);
        bl_wheel.setPower(backLeftPower/2);
        fr_wheel.setPower(frontRightPower/2);
        br_wheel.setPower(backRightPower/2);



    }
    @Override
    public void stop(){

    }

}