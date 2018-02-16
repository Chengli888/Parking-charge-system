package com.aiseminar.util;

import com.cynovo.kivvidevicessdk.KivviDevice;
import com.cynovo.kivvidevicessdk.KvException;

import java.io.UnsupportedEncodingException;

/**
 * Created by 18852 on 2017/3/21.
 */

public class Print {
    public Print(String stream,String plate,String chargeway,int charge,String dbgData,String begintime,String endTime,String color) throws UnsupportedEncodingException, KvException {
        final KivviDevice kvDev = new KivviDevice();
        kvDev.Set(ConstantValue.DATA,"车牌号:"+plate);
        kvDev.Action(ConstantValue.PRINTER_TEXT);
        kvDev.Set(ConstantValue.DATA,"流水号:"+stream);
        kvDev.Action(ConstantValue.PRINTER_TEXT);
        kvDev.Set(ConstantValue.DATA,"付款方式:"+chargeway);
        kvDev.Action(ConstantValue.PRINTER_TEXT);
        kvDev.Set(ConstantValue.DATA,"应付金额:"+charge);
        kvDev.Action(ConstantValue.PRINTER_TEXT);
        kvDev.Set(KV.KEY.DATA, dbgData.getBytes("utf-8"));
        //kvDev.Set(KV.KEY.PAY_TYPE,"ALIPAY");
        kvDev.Action(KV.CMD.DISPLAY_QR_CODE);
        kvDev.Action(KV.CMD.PRINTER_QRCODE);
        kvDev.Set(ConstantValue.DATA,"开始时间:"+begintime);
        kvDev.Action(ConstantValue.PRINTER_TEXT);
        kvDev.Set(ConstantValue.DATA,"结束时间:"+endTime);
        kvDev.Action(ConstantValue.PRINTER_TEXT);
        kvDev.Set(ConstantValue.DATA,"颜色:"+color);
        kvDev.Action(ConstantValue.PRINTER_TEXT);
        kvDev.Set(KV.KEY.PRINT_LINE, 4);
        kvDev.Action(KV.CMD.PRINTER_FEED);
    }
}
