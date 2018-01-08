package com.navy.yizhi.base;

import android.support.annotation.NonNull;

import com.navy.yizhi.ui.widgets.RxManager;

/**
 * Created by Administrator on 2018/1/3.
 */


public abstract class BasePresenter<M, V> {
    public M mIModel;
    public V mIView;
    protected RxManager manager = new RxManager();

    /**
     * 返回presenter想持有的Model引用
     *
     * @return presenter持有的Model引用
     */
    public abstract M getModel();

    /**
     * 绑定IModel和IView的引用
     *
     * @param m model
     * @param v view
     */
    public void attachMV(@NonNull M m, @NonNull V v) {
        this.mIModel = m;
        this.mIView = v;
        this.onStart();
    }

    /**
     * 解绑IModel和IView
     */
    public void detachMV() {
        manager.unSubscribe();
        mIView = null;
        mIModel = null;
    }

    /**
     * IView和IModel绑定完成立即执行
     * <p>
     * 实现类实现绑定完成后的逻辑,例如数据初始化等,界面初始化, 更新等
     */
    protected abstract void onStart();


}
