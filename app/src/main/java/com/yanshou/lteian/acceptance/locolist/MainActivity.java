package com.yanshou.lteian.acceptance.locolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.yanshou.lteian.acceptance.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QMUIStatusBarHelper.translucent(MainActivity.this);

        SpeechUtility.createUtility(MainActivity.this, SpeechConstant.APPID +"=5b4611c4");

       viewPager = findViewById(R.id.viewpager);
       bottomNavigationView = findViewById(R.id.bottomnavigation);

//       BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
       bottomNavigationView.setOnNavigationItemSelectedListener(
               new BottomNavigationView.OnNavigationItemSelectedListener(){
                   @Override
                   public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.menu_home:
                               viewPager.setCurrentItem(0);
                               break;
                           case R.id.menu_add:
                               viewPager.setCurrentItem(1);
                               break;
                           case R.id.menu_me:
                               viewPager.setCurrentItem(2);
                               break;
                       }
                       return false;
                   }
               }
       );

       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
                if(menuItem!= null){
                    menuItem.setChecked(false);
                }else{
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

       setupViewPager(viewPager);
    }


    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new LocoListQMUIFragment());
        adapter.addFragment(new LocoAddFragment());
        adapter.addFragment(new MeFragment());
        viewPager.setAdapter(adapter);
    }
}
