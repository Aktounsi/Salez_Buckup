package com.prom.eazy;


import android.content.Context;
import android.content.Entity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.media.AudioMetadata;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    FragmentActionListener fragmentActionListener ;
    View rootView ;
    Context context;
    ImageView hamb;
    ProgressBar progressbarDay,progressbarweek;
    LineChart mpLineChart;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        return rootView;
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }

    private void initUI(){
        context  = getContext();

        hamb = (ImageView) rootView.findViewById(R.id.hamb);

        hamb.setClickable(true);
        hamb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentActionListener != null){
                    fragmentActionListener.openDrawer();
                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_hamb);
                hamb.startAnimation(animation);
                }


            }
        });

      progressbarDay = rootView.findViewById(R.id.dailyRailisation);
        progressbarweek = rootView.findViewById(R.id.weeklyRailisation);
        progressbarDay.setProgress(20);
        progressbarweek.setProgress(60);

        //find graph line chart
        mpLineChart = rootView.findViewById(R.id.line_chart);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Data Set 1");
        //Customize LineChart Properties
        lineDataSet1.setLineWidth(4);
        lineDataSet1.setColor(Color.rgb(250,137,107));
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setValueTextColor(Color.rgb(68,8,128));
        lineDataSet1.setValueTextSize(15);
        lineDataSet1.setDrawValues(false);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet1.setGradientColor(Color.rgb(250,137,107),Color.rgb(255,188,168));
        //lineDataSet1.setCubicIntensity((float) .2);
        lineDataSet1.setDrawHorizontalHighlightIndicator(false);
        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setFillColor(Color.rgb(250,137,107));

        lineDataSet1.setFillDrawable(getResources().getDrawable(R.drawable.line_chart_fill_bg));


//////////////////////////////////////
        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(),"Data Set 2");
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet2.setDrawHorizontalHighlightIndicator(false);
        lineDataSet2.setDrawFilled(true);
        lineDataSet2.setFillColor(Color.rgb(68,8,128));
        lineDataSet2.setCircleColor(Color.rgb(190,190,190));
        lineDataSet2.setCircleHoleColor(Color.rgb(98,38,158));
        lineDataSet2.setCircleRadius(9);
        lineDataSet2.setCircleHoleRadius(5);
        //lineDataSet2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        //lineDataSet2.setFormSize(15.f);





////////////////////////////////////////



        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        LineData data = new LineData(dataSets);

        mpLineChart.setNoDataText("NOTHING TO SHOW!");
        mpLineChart.setNoDataTextColor(Color.BLUE);
        mpLineChart.setDrawGridBackground(false);
        mpLineChart.getLegend().setEnabled(false);
        Description desc = new Description();
        desc.setText("");
        desc.setTextColor(Color.BLUE);
        desc.setTextSize(10);

        mpLineChart.setDescription(desc);
        mpLineChart.getAxisLeft().setDrawGridLines(false);
        mpLineChart.getXAxis().setDrawGridLines(false);
        mpLineChart.getAxisRight().setDrawGridLines(false);
        mpLineChart.getAxisRight().setDrawLabels(false);
        mpLineChart.getXAxis().setDrawLabels(true);
        mpLineChart.getXAxis().setTextColor(Color.BLUE);
        mpLineChart.getXAxis().setTextSize(10f);
        mpLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //mpLineChart.getXAxis().setValueFormatter(new MyXAxis());
        mpLineChart.getAxisLeft().setDrawLabels(false);
        mpLineChart.setDrawBorders(false);
        mpLineChart.getXAxis().setGranularity(1);
        //mpLineChart.setDrawMarkers(false);
        //mpLineChart.setLeft(1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mpLineChart.animateX(430);
            }
        }, 1000);

        /////
        final String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu"}; // Your List / array with String Values For X-axis Labels
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu"));

        // Set the value formatter
        //mpLineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(weekdays));
        //mpLineChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
        mpLineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // return the string va
                final String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu"};
                int index = Math.round(value);

                //if (index < 0 || index >= weekdays.length || index != (int)value)
                //    return "";

                return weekdays[index];
            }
        });

        /////


        mpLineChart.setData(data);
        mpLineChart.invalidate();



    }

    private ArrayList<Entry> dataValues1 (){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,35));
        dataVals.add(new Entry(2,5));
        dataVals.add(new Entry(3,45));
        dataVals.add(new Entry(4,10));
        return dataVals;
    }
    private ArrayList<Entry> dataValues2 (){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(4,10));

        return dataVals;
    }





}
