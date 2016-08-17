package pe.comercio.stepper.util;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Ricardo Bravo on 15/08/16.
 */

public class Utils {

    public static void requestFocus(Activity activity, View view) {
        if (view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
