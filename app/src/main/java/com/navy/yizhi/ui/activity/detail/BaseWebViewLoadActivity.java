package com.navy.yizhi.ui.activity.detail;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.navy.yizhi.R;
import com.navy.yizhi.base.activity.BaseMVPCompatActivity;
import com.navy.yizhi.ui.widgets.NestedScrollWebView;
import com.navy.yizhi.ui.widgets.WebViewLongClickedPopWindow;
import com.navy.yizhi.utils.DisplayUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/5.
 */

public class BaseWebViewLoadActivity<P extends BaseWebViewLoadContract.BaseWebViewLoadPresenter, M
        extends BaseWebViewLoadContract.IBaseWebViewLoadModel> extends BaseMVPCompatActivity<P, M> implements
        BaseWebViewLoadContract.IBaseWebViewLoadView {
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.iv_detail)
    ImageView ivDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_detail_copyright)
    TextView tvDetailcopyright;
    @BindView(R.id.nswv_detail_content)
    NestedScrollWebView nswvDetailContent;
    @BindView(R.id.fl_net_view)
    FrameLayout flNetView;
    @BindView(R.id.v_network_error)
    View vNetworkError;
    @BindView(R.id.pb_web)
    ProgressBar pvWeb;

    private int downX, downY;
    private WebViewLongClickedPopWindow popWindow;
    private String mImgurl;

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showPopupWindow() {

    }

    @Override
    public void dismissPopupWindow() {

    }

    @Override
    public boolean popupWindowIsShowing() {
        return false;
    }

    @Override
    public void gotoImageBrowse(String imgUrl) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitleBar(toolbar, "跳转中...");
        initWebSetting(nswvDetailContent.getSettings());
        initWebView();
        vNetworkError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetail();
            }
        });
        popWindow = new WebViewLongClickedPopWindow(this, WebViewLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW
                , DisplayUtils.dp2px(120), ViewGroup.LayoutParams.WRAP_CONTENT);

        popWindow.setOnItemClickListener(new WebViewLongClickedPopWindow.OnItemClickListener() {
            @Override
            public void onShowPicClicked() {
                mPresenter.gotoImageBrowseClicked(mImgurl);
            }

            @Override
            public void onSavePicClicked() {
                mPresenter.saveImageClicked(BaseWebViewLoadActivity.this, mImgurl);
            }
        });
        loadDetail();
    }

    @Override
    public void onBackPressedSupport() {

        if () {

        }
        super.onBackPressedSupport();
    }

    private void loadDetail() {

    }

    private void initWebView() {

    }

    private void initWebSetting(WebSettings settings) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
