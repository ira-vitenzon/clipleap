package com.joyfulshark.clipleap.WizardView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public abstract class WizardPage extends Fragment {

    abstract public void setData(Bundle data);

    /* override to create actual page output */
    public Bundle getData(){
        return null;
    }

}
