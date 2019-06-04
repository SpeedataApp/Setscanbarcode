package com.setscanbarcode;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
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
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.setscanbarcode.adapter.ContentAdapter;
import com.setscanbarcode.bean.ContentBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import win.reginer.adapter.CommonRvAdapter;

import static com.setscanbarcode.SetConstant.CONTINUOUS;
import static com.setscanbarcode.SetConstant.DECODEFLAG;
import static com.setscanbarcode.SetConstant.DECODE_TYPE;
import static com.setscanbarcode.SetConstant.DISPLAYTYPE;
import static com.setscanbarcode.SetConstant.DISPLAYTYPES;
import static com.setscanbarcode.SetConstant.ENABLEDECODE;
import static com.setscanbarcode.SetConstant.ENABLEFLAG;
import static com.setscanbarcode.SetConstant.FLASH;
import static com.setscanbarcode.SetConstant.FRONT;
import static com.setscanbarcode.SetConstant.FXSERVICE_RE;
import static com.setscanbarcode.SetConstant.INIT_JIHUO;
import static com.setscanbarcode.SetConstant.INIT_JIHUOMIAOSHU;
import static com.setscanbarcode.SetConstant.ISENABLE;
import static com.setscanbarcode.SetConstant.ISFLASH;
import static com.setscanbarcode.SetConstant.ISFRONT;
import static com.setscanbarcode.SetConstant.ISSAVEIMAGE;
import static com.setscanbarcode.SetConstant.ISSOUND;
import static com.setscanbarcode.SetConstant.JIHUO;
import static com.setscanbarcode.SetConstant.JIHUOMIAOSHU;
import static com.setscanbarcode.SetConstant.JILUXUANZE;
import static com.setscanbarcode.SetConstant.KEYREPORT;
import static com.setscanbarcode.SetConstant.MIAOZHUNDENG;
import static com.setscanbarcode.SetConstant.PREFIX;
import static com.setscanbarcode.SetConstant.SAVE_IMAGE;
import static com.setscanbarcode.SetConstant.SCANCAMERA;
import static com.setscanbarcode.SetConstant.SCANSERVICES_RE;
import static com.setscanbarcode.SetConstant.SCAN_RE;
import static com.setscanbarcode.SetConstant.SHOW_DECODE;
import static com.setscanbarcode.SetConstant.SIGHT_LIGHT;
import static com.setscanbarcode.SetConstant.SOUND;
import static com.setscanbarcode.SetConstant.STARTSCAN;
import static com.setscanbarcode.SetConstant.SUFFIX;
import static com.setscanbarcode.SetConstant.VIBRATOR;


public class MainActivity extends AppCompatActivity implements CommonRvAdapter.OnItemClickListener {

    private ContentAdapter mAdapter;
    private List<ContentBean> mList = new ArrayList<>();
    SharedPreferencesUtil preferencesUtil;
    boolean[] boolArr;
    boolean[] display;
    // TODO: 2018/5/3
    boolean[] display2;
    String TAG = "SETSCAN";


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
    // TODO: 2018/5/3
    private ContentBean contentBean12;
    private ContentBean contentBean103;
    private ContentBean contentBean13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferencesUtil = SharedPreferencesUtil.getInstance(this, "setscan");

        initData();
        intentFilter();
        boolArr = new boolean[items.length];
        display = new boolean[displayItems.length];
        // TODO: 2018/5/3
        display2 = new boolean[displayItems2.length];
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initData() {

        displayItems = new String[]{getString(R.string.scanning_head_decode),
                getString(R.string.rear_camera_decode), getString(R.string.close_quick_scan)};
        // TODO: 2018/5/3
        displayItems2 = getResources().getStringArray(R.array.add_keyvalue_entries);

        //1.启动快捷扫描
        contentBean1 = new ContentBean();
        contentBean1.setTitle(getString(R.string.scan_key));
        contentBean1.setDescribe(preferencesUtil.read(JILUXUANZE, getString(R.string.click_to_select_scan_head_or_turn_off_scan)));
        contentBean1.setTvVisible(true);
        contentBean1.setCbVisible(false);
        contentBean1.setCheck(preferencesUtil.read(ISENABLE, false));
        mList.add(contentBean1);

        //2.文字：扫描成功
        ContentBean contentBean101 = new ContentBean();
        contentBean101.setTitle("");
        contentBean101.setDescribe(getString(R.string.scanning_success));
        contentBean101.setTvVisible(true);
        contentBean101.setCbVisible(false);
        contentBean101.setCheck(false);
        mList.add(contentBean101);

        //3.焦点显示：显示扫描结果
        contentBean2 = new ContentBean();
        contentBean2.setTitle(getString(R.string.scan_results));
        contentBean2.setDescribe(getString(R.string.describe_scan_results));
        contentBean2.setTvVisible(true);
        contentBean2.setCbVisible(true);
        contentBean2.setCheck(SystemProperties.getBoolean("persist.scan.showdecode", true));
        mList.add(contentBean2);

        //4.提示音
        contentBean3 = new ContentBean();
        contentBean3.setTitle(getString(R.string.scan_sound));
        contentBean3.setDescribe(getString(R.string.describe_scan_sound));
        contentBean3.setTvVisible(true);
        contentBean3.setCbVisible(true);
        contentBean3.setCheck(SystemProperties.getBoolean("persist.scan.sound", true));
        mList.add(contentBean3);

        //5.震动
        contentBean4 = new ContentBean();
        contentBean4.setTitle(getString(R.string.scan_vibration));
        contentBean4.setDescribe(getString(R.string.describe_scan_vibration));
        contentBean4.setTvVisible(true);
        contentBean4.setCbVisible(true);
        contentBean4.setCheck(SystemProperties.getBoolean("persist.scan.vibrator", true));
        mList.add(contentBean4);

        //6.保存图片
        contentBean5 = new ContentBean();
        contentBean5.setTitle(getString(R.string.save_image));
        contentBean5.setDescribe(getString(R.string.describe_save_image));
        contentBean5.setTvVisible(true);
        contentBean5.setCbVisible(true);
        contentBean5.setCheck(preferencesUtil.read(ISSAVEIMAGE, false));
        mList.add(contentBean5);

        //7.文字：扫描设置
        ContentBean contentBean102 = new ContentBean();
        contentBean102.setTitle("");
        contentBean102.setDescribe(getString(R.string.scan_settings));
        contentBean102.setTvVisible(true);
        contentBean102.setCbVisible(false);
        contentBean102.setCheck(false);
        mList.add(contentBean102);

        //8.连续扫描
        contentBean6 = new ContentBean();
        contentBean6.setTitle(getString(R.string.continuous_scan));
        contentBean6.setDescribe(getString(R.string.describe_continuous_scan));
        contentBean6.setTvVisible(true);
        contentBean6.setCbVisible(true);
        contentBean6.setCheck(SystemProperties.getInt("persist.scan.continuous", 1) == 2);
        mList.add(contentBean6);

        //9.瞄准灯
        contentBean7 = new ContentBean();
        contentBean7.setTitle(getString(R.string.aiming_lamp));
        contentBean7.setDescribe(getString(R.string.aiming_lamp_description));
        contentBean7.setTvVisible(true);
        contentBean7.setCbVisible(true);
        contentBean7.setCheck(preferencesUtil.read(MIAOZHUNDENG, true));
        mList.add(contentBean7);

        //10.补光灯
        contentBean8 = new ContentBean();
        contentBean8.setTitle(getString(R.string.fill_light));
        contentBean8.setDescribe(getString(R.string.white_light));
        contentBean8.setCheck(preferencesUtil.read(ISFLASH, true));
        contentBean8.setTvVisible(true);
        contentBean8.setCbVisible(true);
        mList.add(contentBean8);

        //11.条码类型
        contentBean9 = new ContentBean();
        contentBean9.setTitle(getString(R.string.scan_barcode_type));
        contentBean9.setTvVisible(false);
        contentBean9.setCbVisible(false);
        contentBean9.setCheck(false);
        mList.add(contentBean9);

        //12.自定义前缀
        contentBean10 = new ContentBean();
        contentBean10.setTitle(getString(R.string.custom_prefix));
        contentBean10.setTvVisible(false);
        contentBean10.setCbVisible(false);
        contentBean10.setCheck(false);
        mList.add(contentBean10);

        //13.自定义后缀
        contentBean11 = new ContentBean();
        contentBean11.setTitle(getString(R.string.custom_suffix));
        contentBean11.setDescribe("");
        contentBean11.setTvVisible(false);
        contentBean11.setCbVisible(false);
        contentBean11.setCheck(false);
        mList.add(contentBean11);

        // TODO: 2018/5/3
        //14.附带键值
        contentBean12 = new ContentBean();
        contentBean12.setTitle(getString(R.string.with_key_value));
        contentBean12.setDescribe("");
        contentBean12.setTvVisible(false);
        contentBean12.setCbVisible(false);
        contentBean12.setCheck(false);
        mList.add(contentBean12);


        //14.激活状态
        contentBean103 = new ContentBean();
        contentBean103.setTitle(preferencesUtil.read(JIHUO, getString(R.string.scan_not_active)));
        contentBean103.setDescribe("");
        contentBean103.setTvVisible(false);
        contentBean103.setCbVisible(false);
        contentBean103.setCheck(false);
        mList.add(contentBean103);

        //15.更多
        contentBean13 = new ContentBean();
        contentBean13.setTitle(getString(R.string.scan_more));
        contentBean13.setDescribe("");
        contentBean13.setTvVisible(false);
        contentBean13.setCbVisible(false);
        contentBean13.setCheck(false);
        mList.add(contentBean13);

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
        // TODO: 2018/5/3
        contentBean12.setEnable(true);

        initEnable();
    }

    //修改设置选项可点击与不可点击
    private void initEnable() {
        if (preferencesUtil.read(ISENABLE, true)) {

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
            // TODO: 2018/5/3
            contentBean12.setEnable(true);
            contentBean13.setEnable(true);
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
            // TODO: 2018/5/3
            contentBean12.setEnable(false);
            contentBean13.setEnable(false);
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
        intent.putExtra(ENABLEDECODE, b);
        sendBroadcast(intent);
    }

    private void sendBroadcasts(String stirng, String s) {
        Intent intent = new Intent();
        intent.setAction(stirng);
        intent.putExtra(ENABLEDECODE, s);
        sendBroadcast(intent);
    }


    private int position;

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        mList.get(position).setCheck(!mList.get(position).isCheck());
        boolean b = mList.get(position).isCheck();
        this.position = position;
        Log.d(TAG, "点击了:" + position);
        switch (position) {
            case 0:
                //if(isWorked(this, FXSERVICE)){
                if (isWorked(this, FXSERVICE_RE)) {
                    break;
                }
                //弹出对话框，选择摄像头解码或关闭。
                //弹出对话框，详细设置在对话框选择后修改
                DisplayCameraSelection();

//                if (b) {
//                    Intent BarcodeIntent = new Intent();
//                    ComponentName cn = new ComponentName("com.scanservice","com.scanbarcodeservice.ScanServices");
//                    BarcodeIntent.setComponent(cn);
//
//                    this.startService(BarcodeIntent);
//                    preferencesUtil.write(isEnable, b);
//                    SystemProperties.set("persist.sys.keyreport", "true");
//                    initEnable();
//
//                } else {
//                    Intent BarcodeIntent = new Intent();
//                    ComponentName cn = new ComponentName("com.scanservice","com.scanbarcodeservice.ScanServices");
//                    BarcodeIntent.setComponent(cn);
//                    this.stopService(BarcodeIntent);
//                    preferencesUtil.write(isEnable, b);
//                    SystemProperties.set("persist.sys.keyreport","false");
//                    initEnable();
//                }
                mAdapter.notifyDataSetChanged();
                break;

            case 1:
                //不可点击的文字描述：扫描成功
                break;
//                //使用前置摄像头
//                if(isWorked(this, "com.scanbarcodeservice.FxService")){
//                    break;
//                }
//
//                if (b) {
//                    if (contentBean6.isCheck()) {
//                        sendBroadcast("com.setscan.flash", false);
//                        contentBean6.setCheck(false);
//                        preferencesUtil.write(isFlash, false);
//                    }
//                    SystemProperties.set("persist.sys.scancamera", "front");
//                    sendBroadcast("com.setscan.front", true);
//                    preferencesUtil.write(isFront, b);
//                    contentBean6.setEnable(false);
//                } else {
//                    SystemProperties.set("persist.sys.scancamera", "back");
//                    sendBroadcast("com.setscan.front", false);
//                    preferencesUtil.write(isFront, b);
//                    contentBean6.setEnable(true);
//                }
//                break;

            case 2:
                //焦点扫描，显示扫描结果
                if (b) {
                    sendBroadcast(SHOW_DECODE, true);
                    SystemProperties.set("persist.scan.sound", String.valueOf(b));
                } else {
                    sendBroadcast(SHOW_DECODE, false);
                    SystemProperties.set("persist.scan.sound", String.valueOf(b));
                }
                break;
            case 3:
                //提示音
                if (b) {
                    sendBroadcast(SOUND, true);
                    preferencesUtil.write(ISSOUND, b);
                } else {
                    sendBroadcast(SOUND, false);
                    preferencesUtil.write(ISSOUND, b);
                }
                break;
            case 4:
                //震动
                if (b) {
                    sendBroadcast(VIBRATOR, true);
                    SystemProperties.set("persist.scan.vibrator", String.valueOf(b));
                } else {
                    sendBroadcast(VIBRATOR, false);
                    SystemProperties.set("persist.scan.vibrator", String.valueOf(b));
                }
                break;
            case 5:
                //保存图片
                if (b) {
                    sendBroadcast(SAVE_IMAGE, true);
                    preferencesUtil.write(ISSAVEIMAGE, b);
                } else {
                    sendBroadcast(SAVE_IMAGE, false);
                    preferencesUtil.write(ISSAVEIMAGE, b);
                }
                break;
            case 6:
                //不可点击的文字提示：扫描设置
                break;
            case 7:
                //连续扫描
                if (b) {
                    sendBroadcast(CONTINUOUS, true);
                    SystemProperties.set("persist.scan.continuous", String.valueOf(2));
                } else {
                    sendBroadcast(CONTINUOUS, false);
                    SystemProperties.set("persist.scan.continuous", String.valueOf(1));
                }
                break;
            case 8:
                //瞄准灯，同补光灯一样。当后置时，此选项不可用
                if ("back".equals(SystemProperties.get(SCANCAMERA))) {
                    break;
                }
                if (b) {
                    sendBroadcast(SIGHT_LIGHT, true);
                    preferencesUtil.write(MIAOZHUNDENG, b);
                } else {
                    sendBroadcast(SIGHT_LIGHT, false);
                    preferencesUtil.write(MIAOZHUNDENG, b);
                }
                break;
            case 9:
                //补光灯
                //if(isWorked(this, "com.scanbarcodeservice.FxService")){
                if (isWorked(this, FXSERVICE_RE)) {
                    break;
                }
                if ("true".equals(SystemProperties.get(STARTSCAN))) {
                    if ("back".equals(SystemProperties.get(SCANCAMERA))) {
                        break;
                    }
                }

                if (b) {
                    sendBroadcast(FLASH, true);
                    preferencesUtil.write(ISFLASH, b);
                } else {
                    sendBroadcast(FLASH, false);
                    preferencesUtil.write(ISFLASH, b);
                }
                break;
            case 10:
                showMultiChoiceItems();
                break;
            case 11:
                showInputDialog(getString(R.string.custom_prefix));
                break;
            case 12:
                showInputDialog(getString(R.string.custom_suffix));
                break;
            // TODO: 2018/5/3
            case 13:
                showWithKeyValue();
                break;
            case 15:
                startActivity(new Intent("com.spd.action.SCAN.SERVICE"));
                break;
            default:
                break;
        }

        mAdapter.notifyDataSetChanged();
    }

    // TODO: 2018/5/3
    private void showWithKeyValue() {
        Log.i(TAG, "showWithKeyValue: " + Arrays.toString(display2));
        int choose = 0;
        for (int i = 0; i < display2.length; i++) {
            display2[i] = preferencesUtil.read(DISPLAYTYPES + i, false);
            if (display2[i]) {
                choose = i;
            }
            Log.i(TAG, "showWithKeyValue: " + display2[i]);
        }

        //准备显示3个选项，极其相关使能的关系
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle(R.string.with_key_value)
                .setSingleChoiceItems(displayItems2, choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            display2[which] = true;
                            display2[1] = false;
                            display2[2] = false;
                        } else if (which == 1) {
                            display2[0] = false;
                            display2[which] = true;
                            display2[2] = false;
                        } else if (which == 2) {
                            display2[0] = false;
                            display2[1] = false;
                            display2[which] = true;
                        }
                    }

                }).setPositiveButton(getString(R.string.dialog_sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < display2.length; i++) {
                            preferencesUtil.write(DISPLAYTYPES + i, display2[i]);
                        }
                        if (display2[0] || display2[1]) {
                            if (display2[0]) {
                                SystemProperties.set("persist.sys.keywithoutkey", "true");
                                SystemProperties.set("persist.sys.keyenter", "false");
                                SystemProperties.set("persist.sys.keytab", "false");
                            } else {
                                SystemProperties.set("persist.sys.keyenter", "true");
                                SystemProperties.set("persist.sys.keywithoutkey", "false");
                                SystemProperties.set("persist.sys.keytab", "false");
                            }
                        } else {
                            SystemProperties.set("persist.sys.keytab", "true");
                            SystemProperties.set("persist.sys.keywithoutkey", "false");
                            SystemProperties.set("persist.sys.keyenter", "false");
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton(getString(R.string.dialog_cancel), null).create();
        lv = builder.getListView();
        builder.show();

    }

    private void showInputDialog(String title) {
        final EditText editText = new EditText(this);
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title).setView(editText).setPositiveButton(getString(R.string.dialog_sure), null)
                .setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        //这里必须要先调show()方法，后面的getButton才有效
        dialog.show();

        if (position == 11) {
            editText.setText(SystemProperties.get("persist.scan.prefix", ""));
        } else if (position == 12) {
            editText.setText(SystemProperties.get("persist.scan.suffix", ""));
        }
        //将光标移动到最后显示最下面的信息.
        Editable text = editText.getText();
        Selection.setSelection(text, text.length());


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if (s.length() > 20) {
                    Toast.makeText(MainActivity.this, getString(R.string.scan_too_long_toast), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (position == 11) {
                    SystemProperties.set("persist.scan.prefix", s);
                    sendBroadcasts(PREFIX, s);
                } else if (position == 12) {
                    SystemProperties.set("persist.scan.suffix", s);
                    sendBroadcasts(SUFFIX, s);
                }
                dialog.dismiss();
            }
        });

//        final EditText editText = new EditText(this);
//        final AlertDialog.Builder inputDialog = new AlertDialog.Builder(this);
//
//        inputDialog.setTitle(title).setView(editText);
//
//        if(position == 11){
//            editText.setText(preferencesUtil.read(qianzhui, ""));
//        }else if(position == 12){
//            editText.setText(preferencesUtil.read(houzhui, ""));
//        }
//        //将光标移动到最后显示最下面的信息.
//        Editable text = editText.getText();
//        Selection.setSelection(text, text.length());
//
//
//        inputDialog.setPositiveButton(getString(R.string.dialog_sure), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String s = editText.getText().toString();
//
//                if (position == 11) {
//                    preferencesUtil.write(qianzhui, s);
//                    sendBroadcasts("com.setscan.qianzhui", s);
//                } else if (position == 12) {
//                    preferencesUtil.write(houzhui, s);
//                    sendBroadcasts("com.setscan.houzhui", s);
//                }
//            }
//        }).setNegativeButton(getString(R.string.dialog_cancel), null).show();

    }


    private ListView lv; //加上ALL共47个 0-46
    private final String[] items = {"UPCA", "UPCA_2CHAR_ADDENDA", "UPCA_5CHAR_ADDENDA", "UPCE0", "UPCE1",
            "UPCE_EXPAND", "UPCE_2CHAR_ADDENDA", "UPCE_5CHAR_ADDENDA", "EAN8", "EAN8_2CHAR_ADDENDA",
            "EAN8_5CHAR_ADDENDA", "EAN13", "EAN13_2CHAR_ADDENDA", "EAN13_5CHAR_ADDENDA", "EAN13_ISBN",
            "CODE128", "GS1_128", "C128_ISBT", "CODE39", "COUPON_CODE", "TRIOPTIC", "I25", "S25", "IATA25",
            "M25", "CODE93", "CODE11", "CODABAR", "TELEPEN", "MSI", "RSS_14", "RSS_LIMITED", "RSS_EXPANDED",
            "CODABLOCK_F", "PDF417", "MICROPDF", "COMPOSITE", "COMPOSITE_WITH_UPC", "AZTEC", "MAXICODE",
            "DATAMATRIX", "DATAMATRIX_RECTANGLE", "QR", "HANXIN", "HK25", "KOREA_POST", "ALL"};


    private String[] displayItems = new String[3];
    // TODO: 2018/5/3  
    private String[] displayItems2 = new String[3];


    /**
     * 条码类型的选择，弹出对话框来确定
     */
    private void showMultiChoiceItems() {
        Log.i(TAG, "showMultiChoiceItems: " + Arrays.toString(boolArr));
        for (int i = 0; i < boolArr.length; i++) {
            if (i == 8 || i == 11 || i == 15 || i == 42) {
                boolArr[i] = preferencesUtil.read(DECODEFLAG + i, true);
                Log.i(TAG, "showMultiChoiceItems: " + boolArr[i]);
            } else {
                boolArr[i] = preferencesUtil.read(DECODEFLAG + i, false);
                Log.i(TAG, "showMultiChoiceItems: " + boolArr[i]);
            }

        }
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title))
                .setMultiChoiceItems(items, boolArr, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        boolArr[which] = isChecked;
                        if (which != 46 && isChecked) {

                            boolArr[which] = isChecked;
                        } else if (which != 46 && !isChecked) {
                            SparseBooleanArray sb;
                            sb = lv.getCheckedItemPositions();
                            if (!sb.get(46)) {
                                lv.setItemChecked(46, false);
                            }
                            boolArr[which] = isChecked;

                        }

                        if (which == 46 && isChecked) { //如果全选，则为全选
                            //把显示的勾选全置为ture
                            SparseBooleanArray sb;
                            sb = lv.getCheckedItemPositions();
                            for (int j = 0; j <= 46; j++) {
                                if (!sb.get(j)) {
                                    lv.setItemChecked(j, true);
                                }
                            }
                            for (int i = 0; i < 47; i++) {
                                boolArr[i] = true;

                            }
                        } else if (which == 46 && !isChecked) {
                            //把显示的勾选全置为false
                            for (int i = 0; i < boolArr.length; i++) {
                                boolArr[i] = false;
                            }
                            lv.clearChoices();
                            lv.setSelection(46);


                        }


                    }
                }).setPositiveButton(getString(R.string.dialog_sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int j = 0;
                        String[] decodeTypes = new String[boolArr.length];
                        for (int i = 0; i < boolArr.length; i++) {
                            decodeTypes[j] = items[i];
                            preferencesUtil.write(DECODEFLAG + i, boolArr[i]);
                            j++;
                        }

                        Intent intent = new Intent();
                        intent.setAction(DECODE_TYPE);
                        Bundle bundle = new Bundle();
                        bundle.putStringArray(ENABLEDECODE, decodeTypes);
                        bundle.putBooleanArray(ENABLEFLAG, boolArr);
                        intent.putExtras(bundle);
                        sendBroadcast(intent);

                    }
                }).setNegativeButton(getString(R.string.dialog_cancel), null).create();
        lv = builder.getListView();
        builder.show();

    }


    /**
     * 点击快捷扫描时，显示的界面内容及点击效果,此为单选
     */
    private void DisplayCameraSelection() {
        Log.i(TAG, "DisplayCameraSelection: " + Arrays.toString(display));
        int choose = 2;
        for (int i = 0; i < display.length; i++) {
            display[i] = preferencesUtil.read(DISPLAYTYPE + i, false);
            if (display[i]) {
                choose = i;
            }
            Log.i(TAG, "DisplayCameraSelection: " + display[i]);
        }

        //准备显示3个选项，极其相关使能的关系
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle(R.string.select_the_scan_head_or_close_the_quick_scan)
                .setSingleChoiceItems(displayItems, choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {

                            display[which] = true;
                            display[1] = false;
                            display[2] = false;

                        } else if (which == 1) {

                            display[0] = false;
                            display[which] = true;
                            display[2] = false;

                        } else if (which == 2) {

                            display[0] = false;
                            display[1] = false;
                            display[which] = true;

                        }
                    }

                }).setPositiveButton(getString(R.string.dialog_sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < display.length; i++) {
                            preferencesUtil.write(DISPLAYTYPE + i, display[i]);
                        }

                        //发广播，判断一下服务起没起，没起来就起服务，起来了就只改摄像头

//                        Intent intent = new Intent();
//                        intent.setAction("com.setscan.decodetype");
//                        Bundle bundle = new Bundle();
//                        bundle.putStringArray("enableDecode", decodeTypes);
//                        bundle.putBooleanArray("enableflag", boolArr);
//                        intent.putExtras(bundle);
//                        sendBroadcast(intent);


                        if (display[0] || display[1]) {
                            //扫描头0，摄像头1
                            //if (isWorked(MainActivity.this, SCANSERVICES)) {
                            if (isWorked(MainActivity.this, SCANSERVICES_RE)) {
                                //在运行就只发广播
                                if (display[0]) {
                                    SystemProperties.set(SCANCAMERA, "front");
                                    sendBroadcast(FRONT, true);
                                    preferencesUtil.write(ISFRONT, true);
                                    preferencesUtil.write(JILUXUANZE, getString(R.string.scanning_head_decode));
                                    contentBean1.setDescribe(preferencesUtil.read(JILUXUANZE, getString(R.string.scanning_head_decode)));
                                    contentBean7.setEnable(true);
                                } else if (display[1]) {
                                    SystemProperties.set(SCANCAMERA, "back");
                                    sendBroadcast(FRONT, false);
                                    preferencesUtil.write(ISFRONT, false);
                                    preferencesUtil.write(JILUXUANZE, getString(R.string.rear_camera_decode));
                                    contentBean1.setDescribe(preferencesUtil.read(JILUXUANZE, getString(R.string.rear_camera_decode)));
                                    contentBean7.setEnable(false);
                                }

                            } else {
                                //服务没运行，则启动服务并在intent中加入摄像头的选择
                                Intent BarcodeIntent = new Intent();
                                //ComponentName cn = new ComponentName("com.scanservice","com.scanbarcodeservice.ScanServices");
                                ComponentName cn = new ComponentName(SCAN_RE, SCANSERVICES_RE);
                                BarcodeIntent.setComponent(cn);
                                // 向intent中添加前后置的请求，可以直接发给服务，让服务判断systemproperties后选取前后置
                                if (display[0]) {

                                    SystemProperties.set(SCANCAMERA, "front");
                                    preferencesUtil.write(ISFRONT, true);
                                    preferencesUtil.write(JILUXUANZE, getString(R.string.scanning_head_decode));
                                    contentBean1.setDescribe(preferencesUtil.read(JILUXUANZE, getString(R.string.scanning_head_decode)));
                                    contentBean7.setEnable(true);
                                } else if (display[1]) {

                                    SystemProperties.set(SCANCAMERA, "back");
                                    preferencesUtil.write(ISFRONT, false);
                                    preferencesUtil.write(JILUXUANZE, getString(R.string.rear_camera_decode));
                                    contentBean1.setDescribe(preferencesUtil.read(JILUXUANZE, getString(R.string.rear_camera_decode)));
                                    contentBean7.setEnable(false);
                                }

                                startService(BarcodeIntent);
                                preferencesUtil.write(ISENABLE, true);
                                SystemProperties.set(KEYREPORT, "true");
                                initEnable();

                            }

                        } else {
                            //2,关闭扫描服务,已关闭则不做操作，未关闭则关闭
                            // if (isWorked(MainActivity.this, "com.scanbarcodeservice.ScanServices")){
                            if (isWorked(MainActivity.this, SCANSERVICES_RE)) {
                                Intent BarcodeIntent = new Intent();
                                //ComponentName cn = new ComponentName("com.scanservice","com.scanbarcodeservice.ScanServices");
                                ComponentName cn = new ComponentName(SCAN_RE, SCANSERVICES_RE);
                                BarcodeIntent.setComponent(cn);
                                stopService(BarcodeIntent);
                            }

                            preferencesUtil.write(ISENABLE, false);
                            SystemProperties.set(KEYREPORT, "false");
                            initEnable();
                            preferencesUtil.write(JILUXUANZE, getString(R.string.close_quick_scan));
                            contentBean1.setDescribe(preferencesUtil.read(JILUXUANZE, getString(R.string.close_quick_scan)));

                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton(getString(R.string.dialog_cancel), null).create();
        lv = builder.getListView();
        builder.show();

    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();

    }

    //接收扫描服务的广播,使选项能够改变使用状态
    private void intentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(INIT_JIHUO);
        filter.addAction(INIT_JIHUOMIAOSHU);
        this.registerReceiver(receiver, filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getAction();
            assert state != null;
            if (state.equals(INIT_JIHUO)) {
                //比对发来的信息，存入pre，更改按钮显示
                String s = intent.getExtras().getString("message");
                if ("未激活".equals(s)) {
                    s = getString(R.string.scan_not_active);
                } else {
                    s = getString(R.string.scan_already_activated);
                }
                preferencesUtil.write(JIHUO, s);
                contentBean103.setTitle(preferencesUtil.read(JIHUO, getString(R.string.scan_not_active)));
            } else if (state.equals(INIT_JIHUOMIAOSHU)) {
                String s = intent.getExtras().getString("message");
                preferencesUtil.write(JIHUOMIAOSHU, s);
                contentBean103.setDescribe(preferencesUtil.read(JIHUOMIAOSHU, ""));
            }
            mAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 判断某个服务是否正在运行的方法
     * <p>
     * <p>
     * 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     *
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isWorked(Context context, String name) {
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        assert myManager != null;
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (name.equals(runningService.get(i).service.getClassName())) {
                return true;
            }
        }
        return false;

    }


}
