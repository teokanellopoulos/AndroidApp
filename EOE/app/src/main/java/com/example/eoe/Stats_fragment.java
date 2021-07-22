package com.example.eoe;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Stats_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_fragment,container,false);
        EditText date_from = (EditText) view.findViewById(R.id.date_from);
        EditText date_to = (EditText) view.findViewById(R.id.date_to);
        Button btn = (Button) view.findViewById(R.id.viewStats);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView results = (TextView) view.findViewById(R.id.results);
                TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tab);
                String date_start = date_from.getText().toString();
                String date_end = date_to.getText().toString();


                if(date_start.isEmpty() || date_end.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.fill_dates, Toast.LENGTH_SHORT).show();
                }else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date convertedCurrentDateStart = null;
                    Date convertedCurrentDateEnd = null;

                    try {
                        convertedCurrentDateStart = sdf.parse(date_start);
                        convertedCurrentDateEnd = sdf.parse(date_end);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String date_from = sdf.format(convertedCurrentDateStart);
                    String date_to = sdf.format(convertedCurrentDateEnd);

                    //diagrafontai oles oi grammes tou pinaka
                    tableLayout.removeAllViews();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://data.gov.gr/api/v1/query/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    DataApi dataApi = retrofit.create(DataApi.class);

                    Call<List<CityStats>> call = dataApi.getCityStats(date_from,date_to);
                    if(call == null){
                        results.setText("Not working");
                    }else{
                        call.enqueue(new Callback<List<CityStats>>() {
                            @Override
                            public void onResponse(Call<List<CityStats>> call, Response<List<CityStats>> response) {

                                List<CityStats> data = response.body();

                                TableLayout stats = (TableLayout) view.findViewById(R.id.tab);
                                TableRow tbrow0 = new TableRow(getActivity());
                                TextView tv0 = new TextView(getActivity());
                                tv0.setText(getString(R.string.date) + "   ");
                                tv0.setTextColor(Color.WHITE);
                                tv0.setBackgroundColor(Color.parseColor("#037bfc"));
                                tbrow0.addView(tv0);
                                TextView tv1 = new TextView(getActivity());
                                tv1.setText(getString(R.string.dose1) + " ");
                                tv1.setTextColor(Color.WHITE);
                                tv1.setBackgroundColor(Color.parseColor("#037bfc"));
                                tbrow0.addView(tv1);
                                TextView tv2 = new TextView(getActivity());
                                tv2.setText(getString(R.string.dose2) + " ");
                                tv2.setTextColor(Color.WHITE);
                                tv2.setBackgroundColor(Color.parseColor("#037bfc"));
                                tbrow0.addView(tv2);
                                TextView tv3 = new TextView(getActivity());
                                tv3.setText(getString(R.string.day_total) + "  ");
                                tv3.setTextColor(Color.WHITE);
                                tv3.setBackgroundColor(Color.parseColor("#037bfc"));
                                tbrow0.addView(tv3);
                                TextView tv4 = new TextView(getActivity());
                                tv4.setText(getString(R.string.total));
                                tv4.setTextColor(Color.WHITE);
                                tv4.setBackgroundColor(Color.parseColor("#037bfc"));
                                tbrow0.addView(tv4);
                                stats.addView(tbrow0);

                                List<List<CityStats>> days = new ArrayList<>();
                                List<DayStats> finalStats = new ArrayList<>();

                                //xwrizoume ton pinaka se upopinakes twn 74 stoixeiwn
                                for(int i=0; i<data.size(); i += 74) {
                                    days.add(data.subList(i, Math.min(i + 74, data.size())));
                                }

                                for(List<CityStats>  day : days) {
                                    int sumOfDailyDoses1 = 0;
                                    int sumOfDailyDoses2 = 0;
                                    int totalDoses = 0;
                                    int i = 0;
                                    String date = "";

                                    for(CityStats cities : day){
                                        sumOfDailyDoses1 += Integer.parseInt(cities.getDailyDoses1());
                                        sumOfDailyDoses2 += Integer.parseInt(cities.getDailyDoses2());
                                        date = cities.getReferenceDate();
                                        totalDoses += Integer.parseInt(cities.getTotalVaccinations());
                                    }

                                    try {
                                        finalStats.add(new DayStats(sumOfDailyDoses1,sumOfDailyDoses2,
                                                sumOfDailyDoses1+sumOfDailyDoses2,totalDoses,
                                                new SimpleDateFormat("yyyy-mm-dd").parse(date.split("T")[0])));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }

                                //taksinomoume ton pinaka kata fthinousa seira basei hmeromhnias
                                Collections.sort(finalStats, new Comparator<DayStats>() {

                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                                    @Override
                                    public int compare(DayStats o1, DayStats o2) {
                                        return dateFormat.format(o2.getDate()).compareTo(dateFormat.format(o1.getDate()));
                                    }
                                });

                                for(DayStats day : finalStats){
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                                    TableRow tbrow = new TableRow(getActivity());
                                    TextView t0 = new TextView(getActivity());
                                    t0.setText(dateFormat.format(day.getDate()) + "     ");
                                    t0.setTextColor(Color.BLACK);
                                    tbrow.addView(t0);
                                    TextView t1 = new TextView(getActivity());
                                    t1.setText(String.valueOf(day.getDailyDoses1()));
                                    t1.setTextColor(Color.BLACK);
                                    tbrow.addView(t1);
                                    TextView t2 = new TextView(getActivity());
                                    t2.setText(String.valueOf(day.getDailyDoses2()));
                                    t2.setTextColor(Color.BLACK);
                                    tbrow.addView(t2);
                                    TextView t3 = new TextView(getActivity());
                                    t3.setText(String.valueOf(day.getTotalDailyDoses()));
                                    t3.setTextColor(Color.BLACK);
                                    tbrow.addView(t3);
                                    TextView t4 = new TextView(getActivity());
                                    t4.setText(String.valueOf(day.getTotalDoses()));
                                    t4.setTextColor(Color.BLACK);
                                    tbrow.addView(t4);
                                    stats.addView(tbrow);
                                    System.out.println(day.getDate());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<CityStats>> call, Throwable t) {
                                results.setText(t.getMessage());
                            }
                        });
                    }
                }
            }
        });

        return view;
    }
}
