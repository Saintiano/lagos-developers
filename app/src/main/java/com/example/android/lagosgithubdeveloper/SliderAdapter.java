package com.example.android.lagosgithubdeveloper;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by saint on 8/23/2017.
 */

public class SliderAdapter extends PagerAdapter {

    private int[] Imageresource = new int[]{
            R.drawable.gdglagos, R.drawable.gdglagos1, R.drawable.gdglagos2,
            R.drawable.gdglagos4, R.drawable.gdglagos5
    };

    private Context context;

    private LayoutInflater layoutInflater;

    public SliderAdapter(Context context){  //manually created adapter activity java file

        this.context = context;

    }


    @Override
    public int getCount() {
        return Imageresource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.slideradapter, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view);
        TextView textView = (TextView) itemView.findViewById(R.id.image_count);
        imageView.setImageResource(Imageresource[position]);
        textView.setText("Google Developers"); // add + position to display the image count numbers
        container.addView(itemView);


        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout) object);

    }
}
