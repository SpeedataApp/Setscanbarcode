package com.setscanbarcode.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;

import com.setscanbarcode.R;
import com.setscanbarcode.bean.ContentBean;

import java.util.List;

import win.reginer.adapter.BaseAdapterHelper;
import win.reginer.adapter.CommonRvAdapter;


public class ContentAdapter extends CommonRvAdapter<ContentBean> {

    public Context mContext;
    public ContentAdapter(Context context, int layoutResId, List<ContentBean> data) {
        super(context, layoutResId, data);
        mContext = context;
    }

    @Override
    public void convert(BaseAdapterHelper helper, ContentBean item, int position) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_describe,item.getDescribe());
        helper.setChecked(R.id.cb,item.isCheck());
        helper.setVisible(R.id.cb,item.isCbVisible());
        helper.setVisible(R.id.tv_describe,item.isTvVisible());
        helper.setEnabled(R.id.ll_item, item.isEnable());

        if (TextUtils.equals(item.getTitle(), "")) {
            helper.setTextColor(R.id.tv_describe, Color.rgb(41,167,155));
        } else {
            helper.setTextColor(R.id.tv_describe,Color.rgb(117,117,117));
        }


    }
}
