package info.omia.sport;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class ButtonAdapter extends BaseAdapter {
    private Context mContext;
    private String[] filesnames;
    private NavigationActivity meep;

    // Gets the context so it can be used later
    public ButtonAdapter(Context c,NavigationActivity mopps,String[] master) {
        mContext = c;
        filesnames=master;
        meep=mopps;
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return filesnames.length;
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(450, 550));
            btn.setPadding(8, 8, 8, 8);
        }
        else {
            btn = (Button) convertView;
        }

        btn.setText(filesnames[position]);
        // filenames is an array of strings
        btn.setTextColor(Color.WHITE);
        btn.setId(position);
        btn.setOnClickListener(new MyOnClickListener(meep,filesnames[position]));

        return btn;
    }
}