package com.example.navadon.androidnamecard;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.navadon.androidnamecard.databinding.ActivityMainBindingImpl;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener;
//    private TextView fullName;
    private int num = 0,num2=0;
    private MainViewModel viewModel;
    ActivityMainBindingImpl binding;

    private String fname,contact,who;
    private Drawable background,bgBtn,bgCard,bgText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        who = "smile";
        bindView();
        initView();
    }

    private void bindView(){
        viewModel = new MainViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(viewModel);

        settingCard(who);
        dataBinding();





    }

    private void initView(){
        initOnClickListener();
        binding.btnClick.setOnClickListener(onClickListener);
        binding.cardView.setOnClickListener(onClickListener);
    }

    // You don't have to bind any functions to "android:onClick" in layout XML file.
    private void initOnClickListener(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnClick :
                        if (who  == "smile"){
                            who = "beam";
                        }else {
                            who = "smile";
                        }
                        settingCard(who);
                        dataBinding();
                        break;
                    case R.id.cardView:
                        if (who == "smile"){
                            changeName(num,who);
                            num +=1;
                        }else {
                            changeName(num2,who);
                            num2++;
                        }



                }
            }
        };
    }

    @SuppressLint("SetTextI18n")
    private  void changeName(int val,String who){
        if (val%2 != 0) {
                    viewModel.setFullName( who == "smile" ? getString(R.string.nameSmile) : getString(R.string.nameBeam));
        }
        else {

            viewModel.setFullName(who == "smile" ? "SMILE :D" : "I'm BEAMMHEE. :)");
        }
        binding.nameMe.setText(viewModel.getFullName());


    }

    private void settingCard(String who){
        switch (who){
            case "smile":
                fname = getString(R.string.nameSmile);
                contact = getString(R.string.telMailSmile);
                background = getResources().getDrawable(R.drawable.bg2);
                bgBtn = getDrawable(R.drawable.button1);
                bgCard = getDrawable(R.drawable.card);
                bgText = getDrawable(R.drawable.card_purple);
                break;
            case "beam":
                fname = getString(R.string.nameBeam);
                contact = getString(R.string.beamPhone);
                background = getResources().getDrawable(R.drawable.bg3);
                bgBtn = getDrawable(R.drawable.button2);
                bgCard = getDrawable(R.drawable.bg7);
                bgText = null;
        }


    }

    private void dataBinding(){
        viewModel.setText(fname,contact);
        viewModel.setDrawable(background,bgBtn,bgCard,bgText);
        binding.nameMe.setText(viewModel.getFullName());
        binding.contactSmile.setText(viewModel.getContact());
        binding.nameMe.setBackground(viewModel.getBgText());
        binding.btnClick.setBackground(viewModel.getBgBtn());
        binding.layoutBg.setBackground(viewModel.getBackground());
        binding.card.setBackground(viewModel.getBgCard());


    }



}
