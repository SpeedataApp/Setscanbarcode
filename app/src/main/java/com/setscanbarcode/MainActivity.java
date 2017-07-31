package com.setscanbarcode;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.os.SystemProperties;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.setscanbarcode.adapter.ContentAdapter;
import com.setscanbarcode.bean.ContentBean;
import com.setscanbarcode.bean.MsgEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import win.reginer.adapter.CommonRvAdapter;


public class MainActivity extends AppCompatActivity implements CommonRvAdapter.OnItemClickListener {

    private ContentAdapter mAdapter;
    private List<ContentBean> mList = new ArrayList<>();
    private List<ContentBean> mShowContentList = new ArrayList<>();
    SharedPreferencesUitl preferencesUitl;
    boolean[] boolArr;
    String TAG = "SETSCAN";
    private String INIT_SERVICE = "com.scanservice.init";

    //设置项目
    private ContentBean contentBean1;
    private ContentBean contentBean2;
    private ContentBean contentBean3;
    private ContentBean contentBean4;
    private ContentBean contentBean5;
    private ContentBean contentBean6;
    private ContentBean contentBean7;
    private ContentBean contentBean8;
    private ContentBean contentBean9;
    private ContentBean contentBean10;
    private ContentBean contentBean11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferencesUitl = SharedPreferencesUitl.getInstance(this, "setscan");
        EventBus.getDefault().register(this);
        initData();
        intentFilter();
        boolArr = new boolean[items.length];
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }
    //eventbus接收数据，原为动态修改显示的描述内容。现在不需要。
    @org.greenrobot.eventbus.Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MsgEvent mEvent) {
        String type = mEvent.getType();
        String msg = (String) mEvent.getMsg();
        if (type.equals("showContent")) {
            mList.get(4).setDescribe(msg);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initData() {

        contentBean1 = new ContentBean();
        contentBean1.setTitle(getString(R.string.scan_key));
        contentBean1.setDescribe(getString(R.string.describe_scan_key));
        contentBean1.setTvVisible(true);
        contentBean1.setCbVisible(true);
        contentBean1.setCheck(preferencesUitl.read(isEnable, true));
        mList.add(contentBean1);

        contentBean2 = new ContentBean();
        contentBean2.setTitle(getString(R.string.select_camera));
        contentBean2.setDescribe(getString(R.string.front_camera));
        contentBean2.setTvVisible(true);
        contentBean2.setCbVisible(true);
        contentBean2.setCheck(preferencesUitl.read(isFront, false));
        mList.add(contentBean2);

        contentBean3 = new ContentBean();
        contentBean3.setTitle(getString(R.string.scan_results));
        contentBean3.setDescribe(getString(R.string.describe_scan_results));
        contentBean3.setTvVisible(true);
        contentBean3.setCbVisible(true);
        boolean b = preferencesUitl.read(isShowdecode, true);
        contentBean3.setCheck(b);
        mList.add(contentBean3);

        contentBean4 = new ContentBean();
        contentBean4.setTitle(getString(R.string.scan_sound));
        contentBean4.setDescribe(getString(R.string.describe_scan_sound));
        contentBean4.setTvVisible(true);
        contentBean4.setCbVisible(true);
        contentBean4.setCheck(preferencesUitl.read(isSound, true));
        mList.add(contentBean4);

        contentBean5 = new ContentBean();
        contentBean5.setTitle(getString(R.string.scan_vibration));
        contentBean5.setDescribe(getString(R.string.describe_scan_vibration));
        contentBean5.setTvVisible(true);
        contentBean5.setCbVisible(true);
        contentBean5.setCheck(preferencesUitl.read(isVibrator, true));
        mList.add(contentBean5);

        contentBean6 = new ContentBean();
        contentBean6.setTitle(getString(R.string.flash_light));
        contentBean6.setDescribe(getString(R.string.describe_flash_light));
        contentBean6.setTvVisible(true);
        contentBean6.setCbVisible(true);
        contentBean6.setCheck(preferencesUitl.read(isFlash, false));
        mList.add(contentBean6);

        contentBean7 = new ContentBean();
        contentBean7.setTitle(getString(R.string.continuous_scan));
        contentBean7.setDescribe(getString(R.string.describe_continuous_scan));
        contentBean7.setTvVisible(true);
        contentBean7.setCbVisible(true);
        contentBean7.setCheck(preferencesUitl.read(isContinuous, false));
        mList.add(contentBean7);

        contentBean8 = new ContentBean();
        contentBean8.setTitle(getString(R.string.save_image));
        contentBean8.setDescribe(getString(R.string.describe_save_image));
        contentBean8.setCheck(preferencesUitl.read(isSaveImage, true));
        contentBean8.setTvVisible(true);
        contentBean8.setCbVisible(true);
        mList.add(contentBean8);

        contentBean9 = new ContentBean();
        contentBean9.setTitle(getString(R.string.barcode_type));
//        contentBean5.setDescribe("使能连续扫描功能");
        contentBean9.setTvVisible(false);
        contentBean9.setCbVisible(false);
        contentBean9.setCheck(false);
        mList.add(contentBean9);

        contentBean10 = new ContentBean();
        contentBean10.setTitle(getString(R.string.custom_prefix));
        contentBean10.setTvVisible(false);
        contentBean10.setCbVisible(false);
        contentBean10.setCheck(false);
        mList.add(contentBean10);

        contentBean11 = new ContentBean();
        contentBean11.setTitle(getString(R.string.custom_suffix));
        contentBean11.setDescribe("");
        contentBean11.setTvVisible(false);
        contentBean11.setCbVisible(false);
        contentBean11.setCheck(false);
        mList.add(contentBean11);

        contentBean1.setEnable(true);
        contentBean2.setEnable(true);
        contentBean3.setEnable(true);
        contentBean4.setEnable(true);
        contentBean5.setEnable(true);
        contentBean6.setEnable(true);
        contentBean7.setEnable(true);
        contentBean8.setEnable(true);
        contentBean9.setEnable(true);
        contentBean10.setEnable(true);
        contentBean11.setEnable(true);

        initEnable();
    }
    //修改设置选项可点击与不可点击
    private void initEnable() {
        if (preferencesUitl.read(isEnable, true)) {

            contentBean2.setEnable(true);
            contentBean3.setEnable(true);
            contentBean4.setEnable(true);
            contentBean5.setEnable(true);
            contentBean6.setEnable(true);
            contentBean7.setEnable(true);
            contentBean8.setEnable(true);
            contentBean9.setEnable(true);
            contentBean10.setEnable(true);
            contentBean11.setEnable(true);
        } else {

            contentBean2.setEnable(false);
            contentBean3.setEnable(false);
            contentBean4.setEnable(false);
            contentBean5.setEnable(false);
            contentBean6.setEnable(false);
            contentBean7.setEnable(false);
            contentBean8.setEnable(false);
            contentBean9.setEnable(false);
            contentBean10.setEnable(false);
            contentBean11.setEnable(false);
        }
    }

    private void initView() {
        mAdapter = new ContentAdapter(MainActivity.this, R.layout.adapter_content, mList);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.notifyDataSetChanged();
    }


    private void sendBroadcast(String stirng, boolean b) {
        Intent intent = new Intent();
        intent.setAction(stirng);
        intent.putExtra("enableDecode", b);
        sendBroadcast(intent);
    }

    private void sendBroadcasts(String stirng, String s) {
        Intent intent = new Intent();
        intent.setAction(stirng);
        intent.putExtra("enableDecode", s);
        sendBroadcast(intent);
    }

    private static String isFront = "isfront";
    private static String isEnable = "isenable";
    private static String isShowdecode = "isshowdecode";
    private static String isSound = "issound";
    private static String isVibrator = "isvibrator";
    private static String isFlash = "isflash";
    private static String isContinuous = "iscontinuous";
    private static String isSaveImage = "issaveimage";

    private int position;

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        mList.get(position).setCheck(!mList.get(position).isCheck());
        boolean b = mList.get(position).isCheck();
        this.position = position;
        switch (position) {
            case 0:
                if (b) {
                    sendBroadcast("com.setscan.enablescan", true);
                    preferencesUitl.write(isEnable, b);
                } else {
                    sendBroadcast("com.setscan.enablescan", false);
                    preferencesUitl.write(isEnable, b);
                    initEnable();
                }
                mAdapter.notifyDataSetChanged();
                break;

            case 1: //使用前置摄像头
                if (b) {
                    SystemProperties.set("persist.sys.scancamera", "front");
                    sendBroadcast("com.setscan.front", true);
                    preferencesUitl.write(isFront, b);
                    contentBean6.setEnable(false);
                } else {
                    SystemProperties.set("persist.sys.scancamera", "back");
                    sendBroadcast("com.setscan.front", false);
                    preferencesUitl.write(isFront, b);
                    contentBean6.setEnable(true);
                }
                break;

            case 2:
                if (b) {
                    sendBroadcast("com.setscan.showdecode", true);
                    preferencesUitl.write(isShowdecode, b);
                } else {
                    sendBroadcast("com.setscan.showdecode", false);
                    preferencesUitl.write(isShowdecode, b);
                }
                break;
            case 3:
                if (b) {
                    sendBroadcast("com.setscan.sound", true);
                    preferencesUitl.write(isSound, b);
                } else {
                    sendBroadcast("com.setscan.sound", false);
                    preferencesUitl.write(isSound, b);
                }
                break;
            case 4:
                if (b) {
                    sendBroadcast("com.setscan.vibrator", true);
                    preferencesUitl.write(isVibrator, b);
                } else {
                    sendBroadcast("com.setscan.vibrator", false);
                    preferencesUitl.write(isVibrator, b);
                }
                break;
            case 5:
                if (b) {
//                    if ("front".equals(SystemProperties.get("persist.sys.scancamera"))){
//                    return; //检测到前置，不启动闪光灯
//                    }
                    sendBroadcast("com.setscan.flash", true);
                    preferencesUitl.write(isFlash, b);
                } else {
                    sendBroadcast("com.setscan.flash", false);
                    preferencesUitl.write(isFlash, b);
                }
                break;
            case 6:
                if (b) {
                    sendBroadcast("com.setscan.continuous", true);
                    preferencesUitl.write(isContinuous, b);
                } else {
                    sendBroadcast("com.setscan.continuous", false);
                    preferencesUitl.write(isContinuous, b);
                }
                break;
            case 7:
                if (b) {
                    sendBroadcast("com.setscan.issaveimage", true);
                    preferencesUitl.write(isSaveImage, b);
                } else {
                    sendBroadcast("com.setscan.issaveimage", false);
                    preferencesUitl.write(isSaveImage, b);
                }
                break;
            case 8:
                showMultiChoiceItems();
                break;
            case 9:
                showInputDialog(getString(R.string.custom_prefix));
                break;
            case 10:
                showInputDialog(getString(R.string.custom_suffix));
                break;
        }

        mAdapter.notifyDataSetChanged();
    }

    private void showInputDialog(String title) {
        final EditText editText = new EditText(this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(this);
        inputDialog.setTitle(title).setView(editText);
        inputDialog.setPositiveButton(getString(R.string.dialog_sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = editText.getText().toString();
                if (position == 9) {

                    sendBroadcasts("com.setscan.qianzhui", s);
                } else if (position == 10) {
                    sendBroadcasts("com.setscan.houzhui", s);
                }
            }
        }).setNegativeButton(getString(R.string.dialog_cancel), null).show();

    }

    private ListView lv; //加上ALL共48个 0-47
    private final String[] items = {"UPCA", "UPCA_2CHAR_ADDENDA", "UPCA_5CHAR_ADDENDA", "UPCE0", "UPCE1",
            "UPCE_EXPAND", "UPCE_2CHAR_ADDENDA", "UPCE_5CHAR_ADDENDA", "EAN8", "EAN8_2CHAR_ADDENDA",
            "EAN8_5CHAR_ADDENDA", "EAN13", "EAN13_2CHAR_ADDENDA", "EAN13_5CHAR_ADDENDA", "EAN13_ISBN",
            "CODE128", "GS1_128", "C128_ISBT", "CODE39", "COUPON_CODE", "TRIOPTIC", "I25", "S25", "IATA25",
            "M25", "CODE93", "CODE11", "CODABAR", "TELEPEN", "MSI", "RSS_14", "RSS_LIMITED", "RSS_EXPANDED",
            "CODABLOCK_F", "PDF417", "MICROPDF", "COMPOSITE", "COMPOSITE_WITH_UPC", "AZTEC", "MAXICODE",
            "DATAMATRIX", "DATAMATRIX_RECTANGLE", "QR", "HANXIN", "HK25", "KOREA_POST", "OCR", "ALL"};


    private void showMultiChoiceItems() {
        Log.i(TAG, "showMultiChoiceItems: " + boolArr);
        for (int i = 0; i < boolArr.length; i++) {
            boolArr[i] = preferencesUitl.read("decodetype" + i, true);
            Log.i(TAG, "showMultiChoiceItems: " + boolArr[i]);
        }
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title))
                .setMultiChoiceItems(items, boolArr, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        boolArr[which] = isChecked;
                        if (which != 47 && isChecked) {
                            preferencesUitl.write("decodetype" + which, isChecked);
                        } else if (which != 47 && !isChecked) {
                            SparseBooleanArray sb;
                            sb = lv.getCheckedItemPositions();
                                if (!sb.get(47)) {
                                    lv.setItemChecked(47, false);
                                }

                            preferencesUitl.write("decodetype" + which, isChecked);
                        }

                        if (which == 47 && isChecked){ //如果全选，则为全选
                            //把显示的勾选全置为ture
                            SparseBooleanArray sb;
                            sb = lv.getCheckedItemPositions();
                            for (int j = 0; j <= 47; j++) {
                                if (!sb.get(j)) {
                                    lv.setItemChecked(j, true);
                                }
                            }
                            for (int i = 0; i < 48; i++) {
                                preferencesUitl.write("decodetype" + i, true);
                            }
                        } else if (which == 47 && !isChecked) {
                            //把显示的勾选全置为false
                            for (int i = 0; i < boolArr.length; i++) {
                                boolArr[i] = false;
                            }
                            lv.clearChoices();
                            lv.setSelection(47);

                            for (int i = 0; i < 48; i++) {
                                preferencesUitl.write("decodetype" + i, false);
                            }
                        }


                    }
                }).setPositiveButton(getString(R.string.dialog_sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        preferencesUitl.write("decodetype" + which, boolArr);
//                        for (int i = 0; i < boolArr.length; i++) {
////                            boolArr[i]=preferencesUitl.read("decodetype", false);
//                        }

//                                for (int i = 0; i < items.length; i++) {
//                                    if (i == which && isChecked)
//                                        preferencesUitl.write("decodetype", isChecked);
//                                }
//                                if (which == items.length - 1) { // 点击全选
//                                    for (int i = 0; i < boolArr.length; i++) {
//                                        boolArr[i] = isChecked;
//                                    }
//                                } else
//
//                                {
//                                    boolArr[which] = isChecked;
//                                }
                        String s = "您选择了：";
                        int j = 0;
                        String[] decodeTypes = new String[boolArr.length];
                        for (int i = 0; i < boolArr.length; i++) {
//                            if (boolArr[i]) {
                                decodeTypes[j] = items[i].toString();
                                j++;
//                            } else {
//                                decodeTypes[j] = "";
//                                j++;
//                            }
                            if (lv.getCheckedItemPositions().get(i)) {
                                Log.i(TAG, i + "onClick: " + decodeTypes[i]);
                                s += i + ":" + lv.getAdapter().getItem(i) + " ";
                            }
                        }
                        Intent intent = new Intent();
                        intent.setAction("com.setscan.decodetype");
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("enableDecode", decodeTypes);
                        bundle.putBooleanArray("enableflag",boolArr);
                        intent.putExtras(bundle);
                        sendBroadcast(intent);
//                        // 用户至少选择了一个列表项
//                        if (lv.getCheckedItemPositions().size() > 0) {
//                            new AlertDialog.Builder(MainActivity.this)
//                                    .setMessage(s).show();
//                            System.out.println(lv.getCheckedItemPositions().size());
//                        }

//                        // 用户未选择任何列表项
//                        else if (lv.getCheckedItemPositions().size() <= 0) {
//                            new AlertDialog.Builder(MainActivity.this)
//                                    .setMessage("您未选择").show();
//                        }
                    }
                }).setNegativeButton(getString(R.string.dialog_cancel), null).create();
        lv = builder.getListView();
        builder.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //接收扫描服务的广播,使选项能够改变使用状态
    private void intentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(INIT_SERVICE);
        registerReceiver(receiver, filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getAction();
            if (state.equals(INIT_SERVICE)) {
                initEnable();
                mAdapter.notifyDataSetChanged();
            }
        }
    };



}
