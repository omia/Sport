package info.omia.sport;

import android.view.View;

class MyOnClickListener implements View.OnClickListener
{
    private final String position;

    private NavigationActivity meeps;

    public MyOnClickListener(NavigationActivity meep,String Name)
    {
        this.position = Name;
        this.meeps=meep;
    }

    public void onClick(View v)
    {
    meeps.buttonprest(position);

    }
}