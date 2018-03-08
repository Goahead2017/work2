package com.bignerdranch.android.wintervacationhomework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 14158 on 2018/2/11.
 */

public class FirstPage extends Fragment implements View.OnClickListener {

    RecyclerView mRecyclerView;
    ExtraAdapter extraAdapter;
    TextView tv1;
    TextView tv2;

    private SwipeRefreshLayout swipeRefresh;

    private Button button;
    private EditText editText;
    /*private int[] res = {R.id.menu_one,R.id.menu_two,R.id.menu_three};
    private List<FloatingActionButton> buttonList = new ArrayList<>();
    private boolean flag = true;*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_layout, null);

        button = view.findViewById(R.id.joke_button);
        editText = view.findViewById(R.id.joke_text);
        button.setOnClickListener(this);

        if (Other.SECOND) {
            for (int i = 0; i <= 4; i++) {
                Other.data2.add("快来写一些搞笑的事情吧");
            }
            Other.SECOND = false;
        }

        mRecyclerView = view.findViewById(R.id.recycler__view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        extraAdapter = new ExtraAdapter(getActivity(), Other.data2);
        mRecyclerView.setAdapter(extraAdapter);

        extraAdapter.setOnItemClickListener(new ExtraAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, ExtraAdapter.ViewName viewName, int position) {
                //处理点击事件，viewName用于区分
                switch (viewName) {
                    case GREAT:
                        tv1 = view.findViewById(R.id.tv1);
                        if (Other.flag1 && Other.flag2) {
                            Other.x++;
                            tv1.setText(String.valueOf(Other.x));
                            Other.flag1 = false;
                        } else if (!Other.flag1 && Other.flag2) {
                            Other.x--;
                            tv1.setText(String.valueOf(Other.x));
                            Other.flag1 = true;
                        }
                        break;
                    case BAD:
                        tv2 = view.findViewById(R.id.tv2);
                        if (Other.flag1 && Other.flag2) {
                            Other.y++;
                            tv2.setText(String.valueOf(Other.y));
                            Other.flag2 = false;
                        } else if (Other.flag1 && !Other.flag2) {
                            Other.y--;
                            tv2.setText(String.valueOf(Other.y));
                            Other.flag2 = true;
                        }
                        break;
                }
            }
        });

        swipeRefresh = view.findViewById(R.id.swipe__refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRecycler();
            }
        });

        return view;
    }

    private void refreshRecycler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //处理具体的刷新逻辑
                        extraAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.joke_button:
                String str;
                str = editText.getText().toString();
                if (str.trim().length() != 0) {
                    Other.data2.add(str);
                    extraAdapter = new ExtraAdapter(getActivity(), Other.data2);
                    mRecyclerView.setAdapter(extraAdapter);
                } else {
                    Toast.makeText(getActivity(), "请先写一个笑话", Toast.LENGTH_SHORT).show();
                }
                final Animation loadAnimation3;
                loadAnimation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
                button.startAnimation(loadAnimation3);
                editText.setText("");
                break;
        }
    }
}