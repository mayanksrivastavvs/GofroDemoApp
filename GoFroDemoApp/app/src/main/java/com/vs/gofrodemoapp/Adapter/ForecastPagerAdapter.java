package com.vs.gofrodemoapp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vs.gofrodemoapp.R;
import com.vs.gofrodemoapp.model.forcastPojo.List;

import java.util.Calendar;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by Naveen on 26-08-2016.
 */
public class ForecastPagerAdapter extends PagerAdapter{

    private Context mContext;
    private List[] mList;

    public ForecastPagerAdapter(Context context, List[] list) {
        mContext = context;
        mList = list;
    }
    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.weather_forcast_layout, container, false);

        String date = getDate(Long.parseLong(mList[position].getDt()));
        String time = getTime(Long.parseLong(mList[position].getDt()));
        TextView dateTextView = (TextView) view.findViewById(R.id.date);
        TextView timeTextView = (TextView) view.findViewById(R.id.time);
        dateTextView.setText(date);
        timeTextView.setText(time);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time*1000);
        return DateFormat.format("dd MMM, yyyy", cal).toString();
    }

    private String getTime(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time*1000);
        return DateFormat.format("hh:mm aa", cal).toString();
    }

}
