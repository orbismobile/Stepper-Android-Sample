package pe.comercio.stepper;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import pe.comercio.stepper.model.StepperEntity;
import pe.comercio.stepper.view.adapter.ViewPagerAdapter;
import pe.comercio.stepper.view.component.CirclePageIndicator;
import pe.comercio.stepper.view.component.CustomViewPager;
import pe.comercio.stepper.view.fragment.FragmentFour;
import pe.comercio.stepper.view.fragment.FragmentThree;
import pe.comercio.stepper.view.fragment.FragmentOne;
import pe.comercio.stepper.view.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        View.OnClickListener {

    private AppCompatTextView lblTitle;

    private CustomViewPager customViewPager;
    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listFragmentTitle = new ArrayList<>();

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;

    private AppCompatButton btnBack;
    private AppCompatButton btnNext;
    private StepperEntity stepperEntity;
    private boolean doubleBackToExit = false;

    private int currentPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBack = (AppCompatButton) findViewById(R.id.btnBack);
        btnNext = (AppCompatButton) findViewById(R.id.btnNext);
        AppCompatImageView imgBack = (AppCompatImageView) findViewById(R.id.imgBack);
        lblTitle = (AppCompatTextView) findViewById(R.id.lblTitle);
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        customViewPager = (CustomViewPager) findViewById(R.id.viewPager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        stepperEntity = new StepperEntity();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment, listFragmentTitle);

        customViewPager.setOffscreenPageLimit(4);
        customViewPager.setAdapter(adapter);

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();

        listFragment.add(fragmentOne);
        listFragment.add(fragmentTwo);
        listFragment.add(fragmentThree);
        listFragment.add(fragmentFour);

        adapter.notifyDataSetChanged();
        indicator.setViewPager(customViewPager);

        customViewPager.addOnPageChangeListener(this);
        assert btnBack != null;
        btnBack.setOnClickListener(this);
        assert btnNext != null;
        btnNext.setOnClickListener(this);
        assert imgBack != null;
        imgBack.setOnClickListener(this);

        lblTitle.setText(getString(R.string.data));

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        switch (position){
            case 0:
                btnBack.setEnabled(false);
                break;
            case 1:
                lblTitle.setText(getString(R.string.shipping));
                btnBack.setEnabled(true);
                break;
            case 2:
                lblTitle.setText(getString(R.string.payment));
                btnBack.setEnabled(true);
                break;
            case 3:
                lblTitle.setText(getString(R.string.confirmation));
                btnBack.setEnabled(false);
                btnNext.setEnabled(false);
                Log.i("x- pos", ""+currentPosition);
                Log.i("x- data", new Gson().toJson(stepperEntity));

                fragmentFour.lblMessage.setText(getString(R.string.message_confirmation,
                        stepperEntity.getName(), stepperEntity.getLastname(),
                        stepperEntity.getAddress(), stepperEntity.getCard()));

                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnBack:
                if(currentPosition>0){
                    customViewPager.setCurrentItem(currentPosition-1, true);
                }
                break;
            case R.id.btnNext:
                switch (currentPosition){
                    case 0:
                        if(fragmentOne.validateFields()){
                            stepperEntity.setName(fragmentOne.name);
                            stepperEntity.setLastname(fragmentOne.lastName);
                            customViewPager.setCurrentItem(1, true);
                        }
                        break;
                    case 1:
                        if(fragmentTwo.validateFields()){
                            stepperEntity.setAddress(fragmentTwo.address);
                            customViewPager.setCurrentItem(2, true);
                        }
                        break;
                    case 2:
                        if(fragmentThree.validateFields()){
                            stepperEntity.setCard(fragmentThree.card);
                            customViewPager.setCurrentItem(3, true);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case R.id.imgBack:
                    onBackPressed();
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExit) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExit = true;
        Toast.makeText(this, getString(R.string.message_back), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExit=false;
            }
        }, 2000);
    }


}
