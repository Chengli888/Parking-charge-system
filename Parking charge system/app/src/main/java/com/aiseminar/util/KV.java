package com.aiseminar.util;

/**
 * 创建一个新版本的KV文件
 */
public class KV {
    //用来处理所有共用的信息
    //错误码
    public class RET {
        public static final int OK = (0x00);                 // 成功
        public static final int DATA_ERROR = (0x10);         // 数据错误
        public static final int CMD_ERROR = (0x11);          // 指令错误
        public static final int PARAM_ERROR = (0x12);        // 参数错误
        public static final int FAILED = (0x20);             // 指令执行失败
        public static final int BUSY = (0x21);               // 正在处理其他指令
        public static final int PROCESSING  = (0x22);        // 异步处理中...
        public static final int TIMEOUT  = (0x23);           // 指令执行(等待)超时
        public static final int CANCELED = (0x24);           // 用户取消
        public static final int EMV_FAILED = (0x30);         // EMV错误
        public static final int COMMUNICATE_ERROR = (0x40);  // 通信失败
        public static final int DEVICE_ERROR  = 0x50;       //设备异常
        public static final int OPEN_FAILED   = 0x51;   // 设备打开失败
    }

    // 行为指令
    public class CMD {
        //basic
        //public static final String HANDSHAKE = "basic.cmd.handshake";
        //public static final String STOP_ACTION = "basic.cmd.stop";
        public static final String TIME = "basic.cmd.time";                     //读取或设置时间
        public static final String GET_VERSION = "basic.cmd.version";           //读取金融模块和SDK版本信息
        public static final String SYSTEM = "basic.cmd.system";                 //系统级操作，如重启模块
        public static final String IS_BUSY = "basic.cmd.isbusy";                //固件是否处于BUSY状态
        public static final String LED_SWITCH = "basic.cmd.led";                //LED开关
        public static final String LOG_STATUE = "basic.cmd.log";                //LED开关


        //card
        public static final String CARD_SEARCH = "card.cmd.search";             //提示并等待用户搜卡
        public static final String TRANSACT = "card.cmd.transact";              //银行卡交易请求，提示用户刷(插/挥)卡和输密码
        public static final String DETECT_IC_SLOT = "card.cmd.detect_ic_slot";  //检测IC卡槽是否有卡
        public static final String EMV_TRANS_REQUEST = "card.cmd.emv_process";  //EMV交易请求
        public static final String EMV_ISSUER_PROC = "card.cmd.issuer_process"; //EMV交易的发卡行数据处理
        public static final String EMV_READ_RECORD = "card.cmd.emv_readrecord";
        public static final String MIFARE = "card.cmd.mifare";                  //Mifare卡的认证、读写等
        public static final String APDU = "card.cmd.apdu";                      //APDU交互操作

        //pinpad
        public static final String PINPAD_MSG_CONFIRM = "pinpad.cmd.confirm";   //余额等敏感信息确认
        public static final String PINPAD_CRYPT = "pinpad.cmd.crypto";          //测试加解密功能
        public static final String PINPAD_LOAD_KEY = "pinpad.cmd.loadkey";      //加载KPK、主密钥、工作密钥
        public static final String PINPAD_CALCULATE_MAC = "pinpad.cmd.mac";     //计算MAC
        public static final String PIN_PROC = "pinpad.cmd.pinprocess";          //提示并等待用户输入密码
        public static final String GET_RANDOM = "pinpad.cmd.random";            //生成随机数
        public static final String PINPAD_KSN = "pinpad.cmd.ksn";               //KSN相关操作
        public static final String PINPAD_TP ="pinpad.cmd.tp";

        //exscreen
        public static final String DISPLAY_IDLE = "exscreen.cmd.idle";          //小屏显示待机Logo
        public static final String DISPLAY_QR_CODE = "exscreen.cmd.qrcode";     //小屏显示二维码
        public static final String DISPLAY_CUSTOM_MSG = "exscreen.cmd.custom_msg";  //小屏显示用户自定义信息
        public static final String DISPLAY_BITMAPS = "exscreen.cmd.bitmap";     //小屏图片轮播

        //storage
        public static final String STORE_FILE = "storage.mpw.write_file";       //存储文件
        public static final String READ_FILE = "storage.mpr.read_file";         //读取文件
        public static final String DELETE_FILE = "storage.cmd.delete_file";     //删除文件
        public static final String STORE_BITMAP = "storage.mpw.load_bitmap";    //保存位图或logo
        public static final String STORE_EMV_PARAMS = "storage.cmd.load_emvparams"; //加载EMV参数
        public static final String STORE_FILE_INIT = "storage.cmd.init";            //初始化文件系统


        //printer
        public static final String PRINTER_PAPER_OUT = "printer.cmd.paperout";  //打印机缺纸检测
        public static final String PRINTER_OBCODE = "printer.cmd.obcode";       //打印一维码
        public static final String PRINTER_PHOTO = "printer.cmd.photo";         //打印图片
        public static final String PRINTER_QRCODE = "printer.cmd.qrcode";       //打印二维码
        public static final String PRINTER_TEXT = "printer.cmd.text";           //打印文字
        public static final String PRINTER_FEED = "printer.cmd.feed";           //打印机走纸

        //scan
        public static final String SCAN_CODE = "scan.cmd.scancode";           //扫码
    }

    // 行为参数名
    public class KEY{
        //通用，基础
        public static final String RESULT = "result";   //错误描述
        public static final String TIMEOUT = "timeout"; //超时时间，单位秒
        public static final String OPERATE = "operate";   //操作 有效值为："GET"/"SET", "ENCRYPT"/"DECRYPT", "ADD"/"CLEAN"等
        public static final String DATETIME = "datetime";   //仅"SET"时需要
        public static final String FW_VERSION = "fwVer";    //软件版本号
        public static final String SDK_VERSION = "SdkVerison";  //SDK版本
        public static final String KVDEV_VERSION = "KivviDeviceVerison";    //KivviDevice版本
        public static final String KVDEV_PLATFORM = "KivviDevicePlatform";  //KivviDevice平台
        public static final String SERIAL_NO = "KivviDeviceSN";      //主设备序列号
        public static final String KIVVI_SERIAL_NO = "serialNo";      //金融模块序列号
        public static final String HW_VERSION = "KivviDeviceHardwareVersion";    //主设备硬件版本号
        public static final String KIVVI_HW_VERSION = "hwVer";    //金融模块硬件版本号
        public static final String CHIP_VERSION = "chipVer";        //芯片版本号
        public static final String KVDEV_SECURITY = "security";      //安全触发寄存器
        public static final String DATA_TYPE = "dataType";
        public static final String DATA = "data";
        public static final String PAY_TYPE = "payType";        //有效值："ALIPA","WEPAY",默认为一般QR
        public static final String CUSTOMER = "customer"; // 客制化标记    有效值: "DEFAULT", "RICOM", "UMPAY"等
        public static final String FW_PATH = "fwPath";      //sbin文件存储路径,默认为SD卡根目录
        public static final String IS_BUSY = "isbusy";     //busy 态
        public static final String LED = "led";
        public static final String LOGSTATUE = "logstatue";


        //卡片相关
        public static final String ICCARD_INSLOT = "cardInSlot";    //0:无卡  1:有卡
        public static final String IS_ICCARD = "isICCard";          //仅当有卡时存在 0:错误卡  1:IC卡
        public static final String CARD_PAN = "pan";  //主帐号
        public static final String TRACK_MODE = "trackMode";  //有效值: "plain"(明文)、"entype0"(加密方式0)
        public static final String CARD_TRACK = "track";  // 磁道数据
        public static final String TRACK1_ERROR = "track1Error";    //磁道1错误码
        public static final String TRACK2_ERROR = "track2Error";    //磁道2错误码
        public static final String TRACK3_ERROR = "track3Error";    //磁道3错误码
        public static final String CARD_SEQNO = "seqNo";    //IC卡序列号
        public static final String CARD_EXP_DATE = "expDate";   //仅当cardType为”MSR”时存在,有效期
        public static final String CARD_TYPE = "cardType"; //卡类别 有效值: "MSR"、"IC"、"NFC"
        public static final String MSR_WITH_IC = "withIC"; //磁条卡带IC标记
        public static final String MIFARE_BLOCK = "block";  //操作扇区中的块，block值为扇区数*4
        public static final String MIFARE_AUTH_KEY_TYPE = "authKeyType";    //仅当"AUTH"操作时需要
        public static final String MIFARE_AUTH_KEY = "authKey";             //仅当"AUTH"操作时需要
        public static final String MIFARE_NUM = "num";                      //仅当"INCREASE"或"DECREASE"操作时需要
        public static final String NFC_TYPE = "nfcType";    //响应:“typeA”、”typeB” 、”M1”
        public static final String NFC_UID = "cardUid";    //NFC卡UID

        //交易、emv相关
        public static final String AMOUNT = "amount";   //交易金额
        public static final String AMOUNT_OTHER = "amountOther";  //其他金额
        public static final String RESP_CODE = "respCode";          //收单平台响应码，通信失败时不设置
        public static final String TRANS_TYPE_CODE = "transType";  //交易类型码   见直联规范(8583)Page99 消息域说明, 如，0x22为"消费"
        public static final String EMV_KERNEL_TYPE = "emvKernel";  //EMV内核类型  有效值: "AUTO", "PBOC", "QPBOC", "UPCASH"电子现金
        public static final String EMV_CHANNEL = "channel"; //EMV 交易渠道 有效值："AUTO"自动, "ForceOnline"强制联机, "ForceOffline" 强制脱机
        public static final String EMV_ISSUER_DATA = "issuerData";  //发卡行数据
        public static final String EMV_REQ_TAGS = "reqTagList";     // EMV 数据Tag标识，Tag之间用‘|’分隔，如没有特别要求，默认数据就符合直联规范（8583），可以不设置。
        public static final String EMV_DATA_TYPE = "emvDataType"; //EMV数据类型 有效值: "REQUEST" 请求、"CONFIRM" 确认、"REVERSAL" 冲正
        public static final String EMV_DATA = "emvData"; //TLV,EMV数据（55域）
        public static final String CVM_TYPE = "cvmType"; //有效值: "ONLINE_PIN" 联机PIN、 "SIGN"签名
        public static final String EMV_TRANS_RECORD_NUM = "recordNum"; //EMV 交易记录数
        public static final String EMV_TRANS_REACORD = "record"; //EMV 交易记录
        public static final String MSR_PIN_PROC = "msrPinProc";     //搜到磁条卡是否进行输密?0\1
        public static final String ALLOW_FALLBACK = "allowFallback";    //是否允许降级交易

        //密码键盘相关
        public static final String KEY_APP_ID = "appId";    //仅当磁道加密时需要,默认0x01
        public static final String KEY_MANAGER = "keyMng";  //密钥管理体系
        public static final String PIN_FORMAT = "pinFormat"; //PINBLOCK格式  有效值: "FORMAT0", "FORMAT1", "FORMAT3","PLAIN_TEXT"
        public static final String PIN_LEN_MIN = "pinMinLen"; //密码最小长度
        public static final String PIN_LEN_MAX = "pinMaxLen"; //密码最大长度
        public static final String PINBLOCK = "pinBlock";
        public static final String CRYPT_ALGORITHM = "algorithm"; //加解密算法 有效值: "DES_ECB" ...
        public static final String PADDING_TYPE = "padding";  //初位方法 有效值: "NULL" 不补位, "M1" 补全0, "M2" 补80 00[n] ...
        public static final String KEY_TYPE = "keyType";   //密钥类别      有效值: "KPK"、"MASTER_KEY"、"PIN_KEY"、"MAC_KEY"、"TD_KEY"
        public static final String KEY_FORMAT = "format";  //密钥格式。    有效值: "plain"(明文)、"EnByKPK"(KPK加密)、"EnByMK"(主密钥加密)、"TR31"
        public static final String KEY_DATA = "key";        //密钥数据
        public static final String KEY_CHECK_VALUE = "checkValue";  //密钥校验值（可选）
        public static final String MAC_TYPE = "macType";   // MAC类别, 有效值:"X99"、"ECB"、"Unionpay_ECB"、"RICOM"
        public static final String MAC = "mac";
        public static final String REQ_LENGTH = "length";   //随机数长度
        public static final String KSN = "ksn";

        //exscreen
        public static final String CAROUSEL_INTERVAL = "interval";  //轮播时间间隔, 单位 秒
        public static final String CAROUSEL_BEGIN_IDX = "beginIdx"; //轮播起始图片索引
        public static final String CAROUSEL_BITMAP_NUM = "palyNum"; //轮播图片的数量

        //storage
        public static final String FILE_TYPE = "fileType";
        public static final String FILE_NAME = "fileName";
        public static final String FILE_PATH = "filePath";
        public static final String FILE_DATA = "fileData";
        public static final String BITMAP_TYPE = "type";    //有效值: "NORMAL" 常规图   "LOGO" logo
        public static final String BITMAP_INDEX = "index";  //图片索引，仅type为"NORMAL"时需要
        public static final String EMV_PARAM_TYPE = "type";  //有效值: "AID", "CAPK", "TERM", "CARD", "CERT"

        //printer
        public static final String PRINT_LINE = "lines";  //走纸 几行
        public static final String PRINT_FEEDUNIT = "unit";
        public static final String PRINT_LINEGAP = "linegap"; //行间距 有效值：int类型 大于0即可
        public static final String PRINT_SIZE = "size"; //字体大小 大于0即可
        public static final String PRINT_FONTTYPE = "fonttype"; //字体类型 四个参数：“normal” "bold" ""
        public static final String PRINT_LEFTMARGIN = "leftmargin"; //页左边间距
        public static final String PRINT_RIGHTMARGIN = "rightmargin"; //页右边间距
        public static final String PRINT_TYPEFACE = "typeface"; //设置自定义字体，导入字体文件路径，类型string
        public static final String PRINT_TEXTWEIGHT = "textweight";
        public static final String PRINT_TEXTHEIGHT = "textheight";
        public static final String PRINT_TEXTPOSITION = "position"; //文字位置 int
    }
}
