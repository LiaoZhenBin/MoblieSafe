package com.liaozhenbin.mobliesafe.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.liaozhenbin.mobliesafe.R;
import com.liaozhenbin.mobliesafe.adapter.IconAadapter;
import com.liaozhenbin.mobliesafe.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

import static com.liaozhenbin.mobliesafe.R.id.icon_from;
import static com.liaozhenbin.mobliesafe.R.id.icon_to;


public class IndexActivity extends BaseActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private List<View> viewList;
    private PagerAdapter pagerAdapter;
    private ImageView point0;
    private ImageView point1;
    private GridView gridView0;
    private GridView gridView1;
    private ImageView icon_to0;
    private ImageView icon_from0;




    private ImageView icon_to1;
    private ImageView icon_from1;
    private int SIGN;

    private static final int SIGN_GRID0 = 0;
    private static final int SIGN_GRID1 = 1;


    private int[] icon0 = new int[]{R.mipmap.main_speed, R.mipmap.main_clear_storage, R.mipmap.main_auto_nor,
            R.mipmap.main_flow, R.mipmap.main_harass, R.mipmap.main_soft_manager};
    private String[] iconName0 = new String[]{"手机加速", "空间清理", "自启管理", "流量管理", "骚捞拦截", "应用管理"};
    private int[] icon1 = new int[]{R.mipmap.main_applock, R.mipmap.main_virus, R.mipmap.main_permission,
            R.mipmap.main_anti_theft, R.mipmap.main_privacy};
    private String[] iconName1 = new String[]{"应用锁", "病毒查杀", "权限管理", "手机防盗", "私密空间"};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initUI();
        initToolBar();
        setListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_more:
                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolBar() {
        toolbar.setTitle("手机卫士");
        toolbar.setTitleTextColor(getResources().getColor(R.color.widowColor));
        toolbar.setLogo(R.mipmap.hi);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initUI() {
        findAllById();
        initFuncation();
        initAdapter();

    }

    private void findAllById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.my_pager);
        point0 = (ImageView) findViewById(R.id.iv_point0);
        point1 = (ImageView) findViewById(R.id.iv_point1);

    }

    private void initAdapter() {
        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        };
        viewPager.setAdapter(pagerAdapter);
        gridView0.setAdapter(new IconAadapter(this, iconName0, icon0));
        gridView1.setAdapter(new IconAadapter(this, iconName1, icon1));

    }

    private void setListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        point0.setImageResource(R.mipmap.line_green);
                        point1.setImageResource(R.mipmap.ic_indicator_normal);
                        SIGN = SIGN_GRID0;
                        break;
                    case 1:
                        point0.setImageResource(R.mipmap.ic_indicator_normal);
                        point1.setImageResource(R.mipmap.line_green);
                        SIGN = SIGN_GRID1;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        gridView0.setOnItemLongClickListener(new MyLongClickListener(icon_from0, icon_to0));
        gridView1.setOnItemLongClickListener(new MyLongClickListener(icon_from1, icon_to1));

        gridView0.setOnItemClickListener(new MyItemClickListener());
        gridView1.setOnItemClickListener(new MyItemClickListener());

    }

    private void initFuncation() {
        LayoutInflater lf = getLayoutInflater().from(this);
        View view0 = lf.inflate(R.layout.funcation0, null);
        View view1 = lf.inflate(R.layout.funcation1, null);
        viewList = new ArrayList<>();
        viewList.add(view0);
        viewList.add(view1);
        icon_to0 = (ImageView) view0.findViewById(icon_to);
        icon_from0 = (ImageView) view0.findViewById(icon_from);
        icon_to1 = (ImageView) view1.findViewById(icon_to);
        icon_from1 = (ImageView) view1.findViewById(icon_from);

        gridView0 = (GridView) view0.findViewById(R.id.grid0);
        gridView1 = (GridView) view1.findViewById(R.id.grid1);

    }

    private class MyLongClickListener implements AdapterView.OnItemLongClickListener {
        private ImageView view_from;
        private ImageView view_to;

        public MyLongClickListener(ImageView from, ImageView to) {
            view_from = from;
            view_to = to;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            View anima = view.findViewById(R.id.animation);
            int[] location = new int[2];
            anima.getLocationOnScreen(location);
            int x0 = location[0];
            int y0 = location[1];
            int[] endLocation = new int[2];
            view_to.getLocationOnScreen(endLocation);
            final int x1 = endLocation[0];
            final int y1 = endLocation[1];

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view_from.getLayoutParams();
            params.leftMargin = x0;
            params.topMargin = y0 - y1 + 10;
            if (SIGN == SIGN_GRID0) {
                view_from.setImageResource(icon0[position]);
            } else if (SIGN == SIGN_GRID1) {
                view_from.setImageResource(icon1[position]);

            }
            view_from.setVisibility(View.VISIBLE);
            view_from.setLayoutParams(params);

            TranslateAnimation translate = new TranslateAnimation(0, x1 - x0, 0, y1 - y0);
            translate.setDuration(600);
            view_from.startAnimation(translate);

            //when the animation finish,invisible the icon;
            ValueAnimator animator = ValueAnimator.ofInt(0, 1);
            animator.setDuration(600);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view_from.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();

            // Toast.makeText(IndexActivity.this, "x0:" + x0 + "x1:" + x1 + "y0:" + y0 + "y1:" + y1, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class MyItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ActivityUtil.startMyActivity(IndexActivity.this,SIGN,position);
//            Toast.makeText(IndexActivity.this,SIGN+"---"+position,Toast.LENGTH_SHORT).show();
        }
    }
}
