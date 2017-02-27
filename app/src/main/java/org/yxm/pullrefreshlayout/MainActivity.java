package org.yxm.pullrefreshlayout;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PullRefreshLayout.OnRefreshListener {

  private PullRefreshLayout mPullRefreshLayout;
  private ListView mListView;
  private ArrayAdapter<String> mAdapter;
  private RecyclerView recyclerView;

  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      if (msg.what == 1) {
        mPullRefreshLayout.refreshFinished();
      } else if (msg.what == 2) {
        for (int i = 1; i <= 10; i++) {
          mAdapter.add("i" + i);
        }
        mPullRefreshLayout.loadMoreFinished();
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mPullRefreshLayout = (PullRefreshLayout) findViewById(R.id.main_pullrefresh_layout);
    mPullRefreshLayout.setRefreshListener(this);

    initListView();
  }


  private void initListView() {

    List<String> list = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      list.add("item:" + i);
    }

    mListView = (ListView) findViewById(R.id.main_listview);
    mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.list_item_text, list);

    mListView.setAdapter(mAdapter);
  }

//  private void initRechclerView() {
//    List<String> list = new ArrayList<>();
//    for (int i = 1; i <= 20; i++) {
//      list.add("item:" + i);
//    }
//
//    recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    recyclerView.setAdapter(new MyAdapter(this, list));
//  }

  @Override
  public void refreshFinished() {
    mHandler.sendEmptyMessageDelayed(1, 3 * 1000);
  }

  @Override
  public void loadMoreFinished() {
    mHandler.sendEmptyMessageDelayed(2, 3 * 1000);

  }
}
