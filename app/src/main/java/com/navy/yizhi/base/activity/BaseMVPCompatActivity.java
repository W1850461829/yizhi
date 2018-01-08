package com.navy.yizhi.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.navy.yizhi.base.BasePresenter;
import com.navy.yizhi.base.IBaseActivity;
import com.navy.yizhi.base.IBaseModel;
import com.navy.yizhi.constant.ToastUtils;
import com.navy.yizhi.ui.activity.BaseCompatActivity;

/**
 * Created by Administrator on 2018/1/5.
 */

public abstract  class BaseMVPCompatActivity<P extends BasePresenter, M extends IBaseModel> extends BaseCompatActivity
        implements IBaseActivity {

    protected P mPresenter;
    private M mIMode;


    /**
     * 初始化数据
     * <p>
     * 子类可以复写此方法初始化子类数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIMode = (M) mPresenter.getModel();
            if (mIMode != null) {
                mPresenter.attachMV(mIMode, this);

            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();

        }
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void showWaitDialog(String waitMsg) {
        showProgressDialog(waitMsg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }

    @Override
    public void hideKeybord() {
        hideKeybord();
    }

    @Override
    public void back() {
        super.onBackPressedSupport();
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
   startActivity(clz,bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
      startActivityForResult(clz,bundle,requestCode);
    }

}
