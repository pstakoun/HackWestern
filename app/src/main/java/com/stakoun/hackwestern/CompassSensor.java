package com.stakoun.hackwestern;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marcel on 2015-11-28.
 */
public class CompassSensor implements SensorEventListener {
    private GameActivity ga;
    // define the display assembly compass picture
    private ImageView image;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;

    TextView tvHeading;

    Sensor accelerometer;
    Sensor magnetometer;

    public CompassSensor(GameActivity ga)
    {
        this.ga = ga;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        tvHeading = (TextView) ga.findViewById(R.id.tvHeading);
        if (tvHeading == null)
            return;
        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        Log.d("debug", "degree: " + degree);
        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
}
