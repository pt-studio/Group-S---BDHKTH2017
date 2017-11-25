package com.example.andrejlee.smartpotui.ui.fragments.temperature;

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
import com.example.andrejlee.smartpotui.ui.fragments.light.LightTabPresenter;
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

public class TempTabFragment extends BaseFragment implements TempTabView {

    @BindView(R.id.lineChartTemp)
    LineChart mLineChartTemp;
    @BindView(R.id.tv_safe_temp_upper)
    TextView mSafeTempUpper;
    @BindView(R.id.tv_safe_temp_lower)
    TextView mSafeTempLower;
    @BindView(R.id.tv_current_temp)
    TextView mCurrentTemp;
    @BindView(R.id.tv_status)
    TextView mStatus;

    private TempTabPresenter mPresenter;
    private static int mCurrentTreeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new TempTabPresenter();
        mPresenter.attachView(this);
        initViews();
    }

    private void initViews() {
        mPresenter.getDeviceById(mCurrentTreeId);
        initLineChartTemp();
    }

    private void initLineChartTemp() {
        mLineChartTemp.setTouchEnabled(true);
        mLineChartTemp.setScaleEnabled(true);
        mLineChartTemp.setDoubleTapToZoomEnabled(true);

//        float yValues [] = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int xValues [] = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24};

        drawLineChartTemp(xValues);
    }

    private void drawLineChartTemp(int[] xValues) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        Description chartDescription = new Description();
        chartDescription.setText(formattedDate);
        mLineChartTemp.setDescription(chartDescription);

        List<Entry> valsSafeMoistureUpper = new ArrayList<>();
        List<Entry> valsSafeMoistureLower = new ArrayList<>();
        for (int i = 0; i < 13; i++){
            valsSafeMoistureUpper.add(new Entry((float)i, 70));
            valsSafeMoistureLower.add(new Entry((float)i, 20));
        }

        List<Entry> valsCurrent = new ArrayList<>();
        valsCurrent.add(new Entry(0f, 50));
        valsCurrent.add(new Entry(1f, 40));
        valsCurrent.add(new Entry(3f, 23));
        valsCurrent.add(new Entry(4f, 54));
        valsCurrent.add(new Entry(5f, 23));
        valsCurrent.add(new Entry(6f, 34));
        valsCurrent.add(new Entry(7f, 56));
        valsCurrent.add(new Entry(8f, 37));
        valsCurrent.add(new Entry(9f, 45));
        valsCurrent.add(new Entry(10f,74));
        valsCurrent.add(new Entry(11f, 34));
        valsCurrent.add(new Entry(12f, 45));


        XAxis xAxis = mLineChartTemp.getXAxis();
        YAxis yAxisLeft = mLineChartTemp.getAxisLeft();
        mLineChartTemp.getAxisRight().setEnabled(false);
        mLineChartTemp.animateX(2000);

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

        LineDataSet setSafeMoistureUpperValues = new LineDataSet(valsSafeMoistureUpper, "Nhiệt độ an toàn trên");
        setSafeMoistureUpperValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setSafeMoistureUpperValues.setColor(Color.GREEN);
        LineDataSet setSafeMoistureLowerValues = new LineDataSet(valsSafeMoistureLower, "Nhiệt độ an toàn dưới");
        setSafeMoistureLowerValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setSafeMoistureLowerValues.setColor(Color.GREEN);
        LineDataSet setCurrentValues = new LineDataSet(valsCurrent, "Nhiệt độ hiện tại");
        setCurrentValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setCurrentValues.setColor(Color.BLUE);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setCurrentValues);
        dataSets.add(setSafeMoistureUpperValues);
        dataSets.add(setSafeMoistureLowerValues);

        LineData data = new LineData(dataSets);
        mLineChartTemp.setData(data);
        mLineChartTemp.invalidate();
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void setContentSafeTempUpper(String content) {
        if (content != null){
            mSafeTempUpper.setText(content);
        }
    }

    @Override
    public void setContentCurrentTemp(String content) {
        if (content != null){
            mCurrentTemp.setText(content);
        }
    }

    @Override
    public void setContentSafeTempLower(String content) {
        if (content != null){
            mSafeTempLower.setText(content);
        }
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
