package com.setscanbarcode;

/**
 * @author :xu in  2018/1/30 15:41.
 *         联系方式:QQ:2419646399
 *         功能描述:
 */
public class SetConstant {


    /**
     * 按设备侧键触发的扫描广播
     */
    public static final String SCAN = "com.geomobile.se4500barcode";
    /**
     * 停止扫描广播,目前没有这个广播发过来,系统侧键只发扫描广播
     */
    public static final String POWER_OFF = "com.geomobile.se4500barcode.poweroff";
    /**
     * 设备要打开相机或手电筒时发来的广播,此时关闭扫描并修改系统属性
     */
    public static final String OPEN_CAMERA = "com.se4500.opencamera";
    /**
     * 设备要关闭相机或手电筒时发来的广播,此时修改系统属性
     */
    public static final String CLOSE_CAMERA = "com.se4500.closecamera";
    /**
     * 设备要休眠时发来的广播,此时关闭扫描并修改系统属性
     */
    public static final String SCREEN_OFF = "android.intent.action.SCREEN_OFF";
    /**
     * 是否要焦点显示扫描结果
     */
    public static final String SHOW_DECODE = "com.setscan.showdecode";
    /**
     * 是否播放扫描提示音
     */
    public static final String SOUND = "com.setscan.sound";
    /**
     * 是否震动
     */
    public static final String VIBRATOR = "com.setscan.vibrator";
    /**
     * 是否开启补光灯
     */
    public static final String FLASH = "com.setscan.flash";
    /**
     * 是否连续扫描
     */
    public static final String CONTINUOUS = "com.setscan.continuous";
    /**
     * 扫描结果添加自定义前缀
     */
    public static final String PREFIX = "com.setscan.qianzhui";
    /**
     * 扫描结果添加自定义后缀
     */
    public static final String SUFFIX = "com.setscan.houzhui";
    /**
     * 使能的条码类型
     */
    public static final String DECODE_TYPE = "com.setscan.decodetype";
    /**
     * 是否保存条码图片
     */
    public static final String SAVE_IMAGE = "com.setscan.issaveimage";
    /**
     * 选择扫描头解码或是后置摄像头解码
     */
    public static final String FRONT = "com.setscan.front";
    /**
     * 是否开启瞄准灯
     */
    public static final String SIGHT_LIGHT = "com.setscan.miaozhundeng";

    /**
     *  发送的成功激活广播
     */
    public static final String INIT_ACTIVATE = "com.scanservice.jihuo";
    /**
     *  发送的激活信息广播
     */
    public static final String INIT_ACTIVATE_MESSAGE = "com.scanservice.jihuomiaoshu";

    /**
     * 如果要焦点显示，则发送此广播给系统
     */
    public static final String RECE_DATA_ACTION = "com.se4500.onDecodeComplete";

    /**
     *  获取广播内数据的action
     */
    public static final String ENABLEDECODE = "enableDecode";

    /**
     *
     */
    public static final String SHOWDECODEDATA = "com.speedata.showdecodedata";


    /**
     * 用于sp存储的变量写在这里
     */
    public static final String ISENABLE = "isenable";

    public static final String ISSHOWDECODE = "isshowdecode";

    public static final String ISSOUND = "issound";

    public static final String ISVIBRATOR = "isvibrator";

    public static final String ISFLASH = "isflash";

    public static final String ISCONTINUOUS = "iscontinuous";

    public static final String QIANZHUI = "qianzhui";

    public static final String HOUZHUI = "houzhui";

    public static final String DECODEFLAG = "decodeflag";

    public static final String ENABLEFLAG = "enableflag";

    public static final String ISSAVEIMAGE = "issaveimage";

    public static final String ISFRONT = "isfront";

    public static final String MIAOZHUNDENG = "miaozhundeng";


    /**
     * SystemPropties保存的键名
     */

    public static final String ISCAMERA = "persist.sys.iscamera";

    public static final String SCANCAMERA = "persist.sys.scancamera";

    public static final String STARTSCAN = "persist.sys.startscan";

    /**
     * EventBus传递的内容
     */
    public static final String STARTSETSETTINGS = "StartSetSettings";

    public static final String MANAGERESULT = "ManageResult";

    public static final String SAVEIMAGE = "SaveImage";

    public static final String DISPLAYTYPE = "displaytype";

    /**
     * 预览服务的包名，用于判断预览框是否在运行
     */
    public static final String FXSERVICE_RE = "com.spd.scan.FxService";

    /**
     *  扫描服务包名，用于判断是否在运行
     */
    public static final String SCANSERVICES_RE = "com.spd.scan.ScanServices";


    public static final String INIT_JIHUO = "com.scanservice.jihuo";

    public static final String INIT_JIHUOMIAOSHU = "com.scanservice.jihuomiaoshu";


    //调整设置界面，修改显示效果
    public static final String JILUXUANZE = "jiluxuanze";

    public static final String JIHUO = "jihuo";

    public static final String JIHUOMIAOSHU = "jihuomiaoshu";

    public static final String FXSERVICE = "com.spd.scan.FxService";

    public static final String SCANSERVICES = "com.spd.scan.ScanServices";

    public static final String SCAN_RE = "com.spd.scan";

    public static final String KEYREPORT = "persist.sys.keyreport";











}
