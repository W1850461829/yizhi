package com.navy.yizhi.ui.widgets;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2017/12/26.
 */

public class WaitPorgressDialog extends ProgressDialog {
    public WaitPorgressDialog(Context context) {
        super(context, 0);
    }

    public WaitPorgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }
}
