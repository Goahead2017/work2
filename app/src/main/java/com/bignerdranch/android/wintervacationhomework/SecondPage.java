package com.bignerdranch.android.wintervacationhomework;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14158 on 2018/2/11.
 */

public class SecondPage extends Fragment implements ImageBarnnerFramLayout.FramLayoutLisenner{

    private int[] ids = new int[]{R.drawable.carousel_one,R.drawable.carousel_two,R.drawable.carousel_three};
    private List<Integer> imageId = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.second_layout,null);

        //计算当前手机的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Other.WITTH = dm.widthPixels;

        ImageBarnnerFramLayout mGroup = view.findViewById(R.id.image_group);
        mGroup.setLisenner(this);
        List<Bitmap>list = new ArrayList<>();

        for (int id : ids) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);


        //初始化图片
        imageId.add(R.drawable.back6);
        imageId.add(R.drawable.back7);
        imageId.add(R.drawable.back8);
        imageId.add(R.drawable.back9);
        imageId.add(R.drawable.back10);

        RecyclerView recyclerViewOne = view.findViewById(R.id.recycler_one);
        GridLayoutManager layoutManagerOne = new GridLayoutManager(getContext(),2);
        recyclerViewOne.setLayoutManager(layoutManagerOne);
        ImageAdapter imageAdapterOne = new ImageAdapter(imageId);
        recyclerViewOne.setAdapter(imageAdapterOne);

        RecyclerView recyclerViewTwo = view.findViewById(R.id.recycler_two);
        GridLayoutManager layoutManagerTwo = new GridLayoutManager(getContext(),3);
        recyclerViewTwo.setLayoutManager(layoutManagerTwo);
        ImageAdapter imageAdapterTwo = new ImageAdapter(imageId);
        recyclerViewTwo.setAdapter(imageAdapterTwo);

        RecyclerView recyclerViewThree = view.findViewById(R.id.recycler_three);
        GridLayoutManager layoutManagerThree = new GridLayoutManager(getContext(),2);
        recyclerViewThree.setLayoutManager(layoutManagerThree);
        ImageAdapter imageAdapterThree = new ImageAdapter(imageId);
        recyclerViewThree.setAdapter(imageAdapterThree);

        return view;
    }

    @Override
    public void clickImageIndex(int pos) {
        //实现轮播图具体图片的点击事件
        switch (pos){
            case 0:
                Intent intent1 = new Intent(getActivity(),ActivityOne.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(getActivity(),ActivityTwo.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(getActivity(),ActivityThree.class);
                startActivity(intent3);
                break;
        }
    }
}
