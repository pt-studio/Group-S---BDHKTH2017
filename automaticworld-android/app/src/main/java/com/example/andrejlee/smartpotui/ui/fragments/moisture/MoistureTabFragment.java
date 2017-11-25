package com.example.andrejlee.smartpotui.ui.fragments.moisture;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrej Lee on 11/25/2017.
 */

public class MoistureTabFragment extends BaseFragment implements MoistureTabView {

    @BindView(R.id.lineChartMoisture)
    LineChart mLineChartMoisture;
    @BindView(R.id.tv_safe_moisture_upper)
    TextView mSafeMoistureUpper;
    @BindView(R.id.tv_safe_moisture_lower)
    TextView mSafeMoistureLower;
    @BindView(R.id.tv_current_moisture)
    TextView mCurrentMoisture;
    @BindView(R.id.tv_status)
    TextView mStatus;

    private MoistureTabPresenter mPresenter;
    private static int mCurrentTreeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moisture, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new MoistureTabPresenter();
        mPresenter.attachView(this);
        initViews();
    }

    private void initViews() {
        mPresenter.getDeviceById(mCurrentTreeId);
        initLineChartMoisture();
    }

    private void initLineChartMoisture() {
        mLineChartMoisture.setTouchEnabled(true);
        mLineChartMoisture.setScaleEnabled(true);
        mLineChartMoisture.setDoubleTapToZoomEnabled(true);

//        float yValues [] = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int xValues [] = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24};

        drawLineChartMoisture(xValues);
    }

    private void drawLineChartMoisture(int[] xValues) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        Description chartDescription = new Description();
        chartDescription.setText(formattedDate);
        mLineChartMoisture.setDescription(chartDescription);

        List<Entry> valsSafeMoistureUpper = new ArrayList<>();
        List<Entry> valsSafeMoistureLower = new ArrayList<>();
        for (int i = 0; i < 13; i++){
            valsSafeMoistureUpper.add(new Entry((float)i, 70));
            valsSafeMoistureLower.add(new Entry((float)i, 30));
        }

        List<Entry> valsCurrent = new ArrayList<>();
        valsCurrent.add(new Entry(0f, 35));
        valsCurrent.add(new Entry(1f, 20));
        valsCurrent.add(new Entry(3f, 30));
        valsCurrent.add(new Entry(4f, 40));
        valsCurrent.add(new Entry(5f, 60));
        valsCurrent.add(new Entry(6f, 50));
        valsCurrent.add(new Entry(7f, 70));
        valsCurrent.add(new Entry(8f, 79));
        valsCurrent.add(new Entry(9f, 45));
        valsCurrent.add(new Entry(10f, 67));
        valsCurrent.add(new Entry(11f, 23));
        valsCurrent.add(new Entry(12f, 90));


        XAxis xAxis = mLineChartMoisture.getXAxis();
        YAxis yAxisLeft = mLineChartMoisture.getAxisLeft();
        mLineChartMoisture.getAxisRight().setEnabled(false);
        mLineChartMoisture.animateX(2000);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(13f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf(xValues[(int) value]);
            }

        });

        yAxisLeft.setDrawLabels(true);
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setAxisMaximum(100f);
        yAxisLeft.setTextColor(Color.BLACK);
        yAxisLeft.setGranularityEnabled(true);
        yAxisLeft.setGranularity(10f);
        yAxisLeft.setTextSize(13f);
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        LineDataSet setSafeMoistureUpperValues = new LineDataSet(valsSafeMoistureUpper, "Độ ẩm an toàn trên");
        setSafeMoistureUpperValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setSafeMoistureUpperValues.setColor(Color.GREEN);
        LineDataSet setSafeMoistureLowerValues = new LineDataSet(valsSafeMoistureLower, "Độ ẩm an toàn dưới");
        setSafeMoistureLowerValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setSafeMoistureLowerValues.setColor(Color.GREEN);
        LineDataSet setCurrentValues = new LineDataSet(valsCurrent, "Độ ẩm hiện tại");
        setCurrentValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setCurrentValues.setColor(Color.BLUE);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setCurrentValues);
        dataSets.add(setSafeMoistureUpperValues);
        dataSets.add(setSafeMoistureLowerValues);

        LineData data = new LineData(dataSets);
        mLineChartMoisture.setData(data);
        mLineChartMoisture.invalidate();
    }


    @Override
    public void setContentSafeMoistureUpper(String content) {
        if (content != null){
            mSafeMoistureUpper.setText(content);
        }
    }

    @Override
    public void setContentCurrentMoisture(String content) {
        if (content != null){
            mCurrentMoisture.setText(content);
        }
    }

    @Override
    public void setContentSafeMoistureLower(String content) {
        if (content != null){
            mSafeMoistureLower.setText(content);
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }


    @Override
    public void showViewState(Constants.ViewState state) {

    }

    @Override
    public Context getCurrentContext() {
        return getContext();
    }

    @Override
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

    @Override
    public void setContentStatus(String content) {
        if (content != null){
            mStatus.setText(content);
        }
    }

    public void setCurrentTreeId(int mCurrentTreeId) {
        this.mCurrentTreeId = mCurrentTreeId;
    }
}
