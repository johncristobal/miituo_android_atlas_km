package com.miituo.atlaskm;

import android.os.AsyncTask;
import com.miituo.atlaskm.activities.VehiclePictures;

/**
 * Created by john.cristobal on 08/01/18.
 */

public class TaskCanceler implements Runnable{
    private VehiclePictures.sendVehiclePicture task;

    public TaskCanceler(VehiclePictures.sendVehiclePicture task) {
        this.task = task;
    }

    @Override
    public void run() {
        if ((task.getStatus() == AsyncTask.Status.RUNNING) && (task.cont != 4)) {
            task.cancel(true);
            //task.progress.dismiss();
            task.flag = false;
        }
    }
}
