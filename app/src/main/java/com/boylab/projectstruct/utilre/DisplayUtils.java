package com.boylab.projectstruct.utilre;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class DisplayUtils {
    public static Point getDisplaySize(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }
}
