package com.navy.yizhi.ui.widgets;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/3.
 */

public class RxManager {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void register(Disposable d) {
        compositeDisposable.add(d);
    }
    public  void unSubscribe(){
        compositeDisposable.dispose();
    }
}
