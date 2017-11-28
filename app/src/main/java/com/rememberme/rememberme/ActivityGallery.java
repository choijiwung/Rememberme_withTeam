package com.rememberme.rememberme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by samsung on 2017-11-28.
 */

public class ActivityGallery extends AppCompatActivity {
EditText editText;
EditText editText2;
SingerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garllery);
        ListView listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);



        adapter = new SingerAdapter();
        adapter.addItem(new ActivityGalleryItem("제주도여행","#가고싶다#회먹고싶다#바다보고싶다",R.drawable.landspace));
        adapter.addItem(new ActivityGalleryItem("우도여행","#가고싶다#회먹고싶다#바다보고싶다",R.drawable.landspace));
        adapter.addItem(new ActivityGalleryItem("강원도여행","#가고싶다#회먹고싶다#바다보고싶다",R.drawable.landspace));
        adapter.addItem(new ActivityGalleryItem("충청도여행","#가고싶다#회먹고싶다#바다보고싶다",R.drawable.landspace));
        adapter.addItem(new ActivityGalleryItem("이탈리아와우 재밌네","#가고싶다#회먹고싶다#바다보고싶다",R.drawable.landspace));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityGalleryItem item = (ActivityGalleryItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 : " + item.getTourTitle(),Toast.LENGTH_LONG ).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TourTitle = editText.getText().toString();
                String TourHash = editText2.getText().toString();

                adapter.addItem(new ActivityGalleryItem(TourTitle,TourHash,R.drawable.landspace));
                adapter.notifyDataSetChanged();

            }
        });

}

    class SingerAdapter extends BaseAdapter{
        ArrayList<ActivityGalleryItem> items = new ArrayList<ActivityGalleryItem>();
        @Override
        public int getCount() {
            return items.size();
        }
        public  void addItem(ActivityGalleryItem item){
            items.add(item);
        }
        @Override
        public Object getItem(int position) { // 만약 5개가 있으면 0,1,2,3,4 를 차례대로 넘겨줌
            return items.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ActivityGalleryItemView view = null;
            if(convertView == null ) {
                view = new ActivityGalleryItemView(getApplicationContext());
            }else{
                view=(ActivityGalleryItemView) convertView;
            }
            ActivityGalleryItem item = items.get(position);
            view.setTourTitle(item.getTourTitle());
            view.setTourHash(item.getTourHash());
            view.setImage(item.getImageId());
            return view;
        }



    }
}