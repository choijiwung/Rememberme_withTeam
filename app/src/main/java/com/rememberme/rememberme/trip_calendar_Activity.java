package com.rememberme.rememberme;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;

public class trip_calendar_Activity extends AppCompatActivity implements CalendarCellDecorator {
    boolean beforeClicked=false;


    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_calendar);


        Calendar nextMonth = Calendar.getInstance();
        Calendar beforeYear = Calendar.getInstance();
        beforeYear.add(Calendar.MONTH,-1);
        nextMonth.add(Calendar.MONTH, 2);

        final CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(beforeYear.getTime(), nextMonth.getTime())
                .withSelectedDate(today);




        calendar.init(beforeYear.getTime(), nextMonth.getTime())
                .inMode(RANGE);

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override

            public void onDateSelected(Date date) {

                Log.i("aaDates", calendar.getSelectedDates().toString());
                final List<Date> choiceDate = calendar.getSelectedDates();

                SimpleDateFormat startday, endday;


                if(calendar.getSelectedDates().size() > 1){
                    SimpleDateFormat sdf;
                    Date startDate = choiceDate.get(0);
                    Date endDate = choiceDate.get(choiceDate.size()-1);
                    //startDate를 tostring으로바꿔서
                    sdf = new SimpleDateFormat("YYYY년 MM월 dd일", Locale.KOREA);
                    String startDay = sdf.format(startDate);
                    String endDay = sdf.format(endDate);
                    Bundle bundle = new Bundle();
                    bundle.putString("startDay", startDay);
                    bundle.putString("endDay", endDay);
                    Fragment AddTitleFragment = new Fragment();
                    AddTitleFragment.setArguments(bundle);
                    AlertDialog.Builder alert = new AlertDialog.Builder(trip_calendar_Activity.this);
                    alert.setTitle("일정 저장");
                    alert.setMessage("일정을 저장하시겠습니까?");
                    alert.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
//                        negative, neu~
                    });

                    alert.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    });
                    alert.show();
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }
}
