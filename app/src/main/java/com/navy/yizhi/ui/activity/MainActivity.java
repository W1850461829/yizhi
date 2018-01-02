package com.navy.yizhi.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.navy.yizhi.R;
import com.navy.yizhi.constant.AppUtils;
import com.navy.yizhi.constant.BundleKeyConstant;
import com.navy.yizhi.constant.SpUtils;
import com.navy.yizhi.constant.ToastUtils;
import com.navy.yizhi.helper.BottomNavigationViewHelper;
import com.navy.yizhi.model.bean.rxbus.RxEventHeadBean;
import com.navy.yizhi.rxbus.RxBus;
import com.navy.yizhi.rxbus.Subscribe;
import com.navy.yizhi.ui.fragment.book.BookRootFragment;
import com.navy.yizhi.ui.fragment.gankio.GankIoRootFragment;
import com.navy.yizhi.ui.fragment.home.HomeRootFragment;
import com.navy.yizhi.ui.fragment.home.child.HomeFragment;
import com.navy.yizhi.ui.fragment.movie.MovieRootFragment;
import com.navy.yizhi.ui.fragment.personal.PersonalRootFragment;
import com.navy.yizhi.ui.widgets.MovingImageView;
import com.navy.yizhi.ui.widgets.MovingViewAnimator;
import com.navy.yizhi.utils.FileUtils;
import com.navy.yizhi.utils.NavigationUtils;

import java.io.File;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportFragment;

import static com.navy.yizhi.constant.HeadConstant.HEAD_IMAGE_NAME;
import static com.navy.yizhi.constant.RxBusCode.RX_BUS_CODE_HEAD_IMAGE_URI;

public class MainActivity extends BaseCompatActivity implements HomeFragment
        .OnOpenDrawerLayoutListener {
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;
    @BindView(R.id.dl_root)
    DrawerLayout dlRoot;
    @BindView(R.id.bviv_bar)
    BottomNavigationView bottomNavigationView;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    private MovingImageView mivMenu;
    private CircleImageView civHead;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected void initData() {
        super.initData();
        RxBus.get().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
       /* if (savedInstanceState == null) {
            mFragments[FIRST] = HomeRootFragment.newInstance();
            mFragments[SECOND] = GankIoRootFragment.newInstance();
            mFragments[THIRD] = MovieRootFragment.newInstance();
            mFragments[FOURTH] = BookRootFragment.newInstance();
            mFragments[FIFTH] = PersonalRootFragment.newInatance();

            loadMultipleRootFragment(R.id.fl_container, FIRST, mFragments[FIRST], mFragments[SECOND]
                    , mFragments[THIRD], mFragments[FOURTH], mFragments[FIFTH]);


        } else {
            mFragments[FIRST] = findFragment(HomeRootFragment.class);
            mFragments[SECOND] = findFragment(GankIoRootFragment.class);
            mFragments[THIRD] = findFragment(MovieRootFragment.class);
            mFragments[FOURTH] = findFragment(BookRootFragment.class);
            mFragments[FIFTH] = findFragment(PersonalRootFragment.class);
        }*/

        NavigationUtils.disableNavigationViewScrollbars(nvMenu);
        mivMenu = (MovingImageView) nvMenu.getHeaderView(0).findViewById(R.id.miv_menu);
        civHead = (CircleImageView) nvMenu.getHeaderView(0).findViewById(R.id.civ_head);

        //此处实际应用中替换成服务器拉取图片
        Uri headUrl = Uri.fromFile(new File(getCacheDir(), HEAD_IMAGE_NAME + ".jpg"));
        if (headUrl != null) {
            String path = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), headUrl);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            if (bitmap != null) {
                civHead.setImageBitmap(bitmap);

            }

        }

        civHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlRoot.closeDrawer(GravityCompat.START);
                bottomNavigationView.setSelectedItemId(R.id.menu_item_personal);
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        showHideFragment(mFragments[FIRST]);
                        break;
                    case R.id.menu_item_gank_io:
                        showHideFragment(mFragments[SECOND]);
                        break;
                    case R.id.menu_item_movie:
                        showHideFragment(mFragments[THIRD]);
                        break;
                    case R.id.menu_item_book:
                        showHideFragment(mFragments[FOURTH]);
                        break;
                    case R.id.menu_item_personal:
                        showHideFragment(mFragments[FIFTH]);
                        break;

                }
                return true;
            }
        });
        nvMenu.getMenu().findItem(R.id.item_model).setTitle(SpUtils.getNightModel(mContext) ?
                "夜间模式" : "日间模式");
        nvMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.group_item_github:
                      /*  Bundle bundle = new Bundle();
                        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE, "Yizhi");
                        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL,
                                "https://github.com/Horrarndoo/YiZhi");
                        startActivity(WebViewLoadActivity.class, bundle);
                        break;*/
                    case R.id.group_item_more:
                     /*   Bundle bundle2 = new Bundle();
                        bundle2.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE,
                                "Horrarndoo");
                        bundle2.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL,
                                "http://blog.csdn.net/oqinyou");
                        startActivity(WebViewLoadActivity.class, bundle2);
                        break;*/
                    case R.id.group_item_qr_code:
                        startActivity(QRCodeActivity.class);
                        break;
                    case R.id.group_item_share_project:
                        showShare();
                        break;
                    case R.id.item_model:
                        SpUtils.setNightModel(mContext, !SpUtils.getNightModel(mContext));
                        MainActivity.this.reload();
                        break;
                    case R.id.item_about:
                        startActivity(AboutActivity.class);
                        break;

                }
                item.setCheckable(false);
                dlRoot.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        dlRoot.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mivMenu.pauseMoving();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.stop) {
                    mivMenu.startMoving();
                } else if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.pause) {
                    mivMenu.resumeMoving();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mivMenu.stopMoving();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.stop) {
                    mivMenu.startMoving();
                } else if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.pause) {
                    mivMenu.resumeMoving();
                }
            }
        });

    }

    private void showShare() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        if (dlRoot.isDrawerOpen(GravityCompat.START)) {
            dlRoot.closeDrawer(GravityCompat.START);
            return;
        }
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                setIsTransAnim(false);
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                ToastUtils.showToast(R.string.press_again);
            }
        }
    }

    @Override
    public void onOpen() {
        if (!dlRoot.isDrawerOpen(GravityCompat.START)) {
            dlRoot.openDrawer(GravityCompat.START);
        }
    }


    /**
     * RxBus接收图片Uri
     *
     * @param bean RxEventHeadBean
     */
    @Subscribe(code = RX_BUS_CODE_HEAD_IMAGE_URI)
    public void rxBusEvent(RxEventHeadBean bean) {
        Uri uri = bean.getUri();
        if (uri == null) {
            return;
        }
        String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), uri);
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null)
            civHead.setImageBitmap(bitMap);
    }
}