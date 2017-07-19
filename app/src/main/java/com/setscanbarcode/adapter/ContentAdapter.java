package com.setscanbarcode.adapter;

import android.content.Context;

import com.setscanbarcode.R;
import com.setscanbarcode.bean.ContentBean;

import java.util.List;

import win.reginer.adapter.BaseAdapterHelper;
import win.reginer.adapter.CommonRvAdapter;


public class ContentAdapter extends CommonRvAdapter<ContentBean> {


    public ContentAdapter(Context context, int layoutResId, List<ContentBean> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void convert(BaseAdapterHelper helper, ContentBean item, int position) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_describe,item.getDescribe());
        helper.setChecked(R.id.cb,item.isCheck());
        helper.setVisible(R.id.cb,item.isCbVisible());
        helper.setVisible(R.id.tv_describe,item.isTvVisible());
        helper.setEnabled(R.id.ll_item, item.isEnable());

    }


}
