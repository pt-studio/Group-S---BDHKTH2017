package com.example.andrejlee.smartpotui.ui.activities.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.activities.update_tree.UpdateTreeActivity;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTreeActivity extends BaseDefaultActivity implements DetailTreeView {

//    @BindView(R.id.lineChart)
//    LineChart mLineChart;
    @BindView(R.id.tv_screen_tree_name)
    TextView mScreenTitle;
//    @BindView(R.id.tv_safe_moisture_upper)
//    TextView mSafeMoistureUpper;
//    @BindView(R.id.tv_safe_moisture_lower)
//    TextView mSafeMoistureLower;
//    @BindView(R.id.tv_current_moisture)
//    TextView mCurrentMoisture;
//    @BindView(R.id.tv_status)
//    TextView mStatus;
    @BindView(R.id.tv_edit_tree)
    TextView mEdit;

    private DetailTreePresenter mPresenter;
    private int mCurrentTreeId;

    public static void start(Activity context, int treeId) {
        Intent starter = new Intent(context, DetailTreeActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        starter.putExtra(Constants.STRING_TREE_ID_INTENT, treeId);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_detail_tree);
        KeyboardUtils.hideSoftInput(this);
        showToolbar(false);
        ButterKnife.bind(this);
        mPresenter = new DetailTreePresenter();
        mPresenter.attachView(this);
        //initViews();
    }

//    private void initViews() {
//        Intent intent = getIntent();
//
//        if (intent != null) {
//            mCurrentTreeId = intent.getIntExtra(Constants.STRING_TREE_ID_INTENT, Constants.NEGA_ONE_VALUE);
//            if (mCurrentTreeId != Constants.NEGA_ONE_VALUE) {
//                mPresenter.getDeviceById(mCurrentTreeId);
//            }
//        }
//
//        initLineChart();
//    }
//
//    private void initLineChart() {
//        mLineChart.setTouchEnabled(true);
//        mLineChart.setScaleEnabled(true);
//        mLineChart.setDoubleTapToZoomEnabled(true);
//
//        float yValues [] = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
//        int xValues [] = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24};
//
//        drawLineChart(yValues, xValues);
//    }
//
//    private void drawLineChart(float yValues[], int xValues[]){
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = df.format(c.getTime());
//
//        Description chartDescription = new Description();
//        chartDescription.setText(formattedDate);
//        mLineChart.setDescription(chartDescription);
//
////        ArrayList<Entry> yData = new ArrayList<>();
////        for(int i = 0; i < yValues.length; i++){
////            yData.add(new Entry(yValues[i], i));
////        }
//
////        ArrayList<Integer> xData = new ArrayList<>();
////        for(int i = 0; i < xValues.length; i++){
////            xData.add(xValues[i]);
////        }
//
////        LineDataSet lineDataSet = new LineDataSet(yData, "%");
////        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
////
////        LineData lineData = new LineData(lineDataSet);
////        lineData.setValueTextSize(13);
////        lineData.setValueTextColor(Color.BLACK);
//
////        mLineChart.setData(lineData);
//
//        List<Entry> valsSafe = new ArrayList<>();
//        for (int i = 0; i < 13; i++){
//            valsSafe.add(new Entry((float)i, 50));
//        }
//
//        List<Entry> valsCurrent = new ArrayList<>();
//        valsCurrent.add(new Entry(0f, 35));
//        valsCurrent.add(new Entry(1f, 20));
//        valsCurrent.add(new Entry(3f, 30));
//        valsCurrent.add(new Entry(4f, 40));
//        valsCurrent.add(new Entry(5f, 60));
//        valsCurrent.add(new Entry(6f, 50));
//        valsCurrent.add(new Entry(7f, 70));
//        valsCurrent.add(new Entry(8f, 79));
//        valsCurrent.add(new Entry(9f, 45));
//        valsCurrent.add(new Entry(10f, 67));
//        valsCurrent.add(new Entry(11f, 23));
//        valsCurrent.add(new Entry(12f, 90));
//
//
//        XAxis xAxis = mLineChart.getXAxis();
//        YAxis yAxisLeft = mLineChart.getAxisLeft();
//        mLineChart.getAxisRight().setEnabled(false);
//        mLineChart.animateX(2000);
//
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTextSize(13f);
//        xAxis.setTextColor(Color.BLACK);
//        xAxis.setDrawAxisLine(true);
//        xAxis.setDrawGridLines(true);
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return String.valueOf(xValues[(int) value]);
//            }
//
//        });
//
//        yAxisLeft.setDrawLabels(true);
//        yAxisLeft.setDrawGridLines(true);
//        yAxisLeft.setAxisMinimum(0f);
//        yAxisLeft.setAxisMaximum(100f);
//        yAxisLeft.setTextColor(Color.BLACK);
//        yAxisLeft.setGranularityEnabled(true);
//        yAxisLeft.setGranularity(10f);
//        yAxisLeft.setTextSize(13f);
//        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//
//        LineDataSet setSafeValues = new LineDataSet(valsSafe, "Độ ẩm an toàn");
//        setSafeValues.setAxisDependency(YAxis.AxisDependency.LEFT);
//        setSafeValues.setColor(Color.GREEN);
//        LineDataSet setCurrentValues = new LineDataSet(valsCurrent, "Độ ẩm hiện tại");
//        setCurrentValues.setAxisDependency(YAxis.AxisDependency.LEFT);
//        setCurrentValues.setColor(Color.BLUE);
//
//        List<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(setCurrentValues);
//        dataSets.add(setSafeValues);
//
//        LineData data = new LineData(dataSets);
//        mLineChart.setData(data);
//        mLineChart.invalidate();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }

    @Override
    public Context getCurrentContext() {
        return this;
    }


    @Override
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

//    @Override
//    public void setContentSafeMoistureUpper(String content) {
//        mSafeMoistureUpper.setText(content);
//    }
//
//    @Override
//    public void setContentCurrentMoisture(String content) {
//        mCurrentMoisture.setText(content);
//    }
//
//    @Override
//    public void setContentSafeMoistureLower(String content) {
//
//    }
//
//    @Override
//    public void setContentSafeLightUpper(String content) {
//
//    }
//
//    @Override
//    public void setContentCurrentLight(String content) {
//
//    }
//
//    @Override
//    public void setContentSafeLightLower(String content) {
//
//    }
//
//    @Override
//    public void setContentSafeTempUpper(String content) {
//
//    }
//
//    @Override
//    public void setContentCurrentTemp(String content) {
//
//    }
//
//    @Override
//    public void setContentSafeTempLower(String content) {
//
//    }
//
//    @Override
//    public void setContentStatus(String content) {
//        mStatus.setText(content);
//    }

    @Override
    public void setScreenTitle(String title) {
        mScreenTitle.setText(title);
    }

    @OnClick(R.id.tv_edit_tree)
    public void onClick(View v) {
        UpdateTreeActivity.start(this, mCurrentTreeId);
    }
}
