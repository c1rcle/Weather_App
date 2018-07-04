package com.c1rcle.weatherapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.c1rcle.weatherapp.Fragments.Intro.IntroFragment1;
import com.c1rcle.weatherapp.Fragments.Intro.IntroFragment2;
import com.c1rcle.weatherapp.Fragments.Intro.IntroFragment3;
import com.c1rcle.weatherapp.Fragments.Intro.IntroFragment4;
import com.c1rcle.weatherapp.R;
import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        addSlide(new IntroFragment1());
        addSlide(new IntroFragment2());
        addSlide(new IntroFragment3());
        addSlide(new IntroFragment4());

        setSkipText(getText(R.string.intro_skip));
        setDoneText(getText(R.string.intro_done));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment)
    {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment)
    {
        super.onDonePressed(currentFragment);
        finish();
    }
}
