package com.inesv.digiccy.util.excel;

import com.inesv.digiccy.api.utils.PropertiesUtil;

public class ExcelTypes {

    public static final String  jiekouip            = PropertiesUtil.getPropertiesValue("componentKey.properties", "jiekouip");

    public static final String  jiekouport          = PropertiesUtil.getPropertiesValue("componentKey.properties", "jiekouport");

    public final static String  regTemplateFile     = "/regTemplate.xls";

    private final static String regTempCols[]       = { "注册人电话", "上级电话", "注册人名字", "注册人地址" };

    public final static String  balanceTemplateFile = "/balanceTemplate.xls";

    private final static String balanceTempCols[]   = { "入金人电话", "入金金额" };

    public final static String  regExcelType        = "regTemplate";

    public final static String  balanceType         = "balanceTemplate";


    public static String getFileName(String fileType) {
        String fileName = "";
        switch (fileType) {
            case regExcelType:
                fileName = regTemplateFile;
                break;
            case balanceType:
                fileName = balanceTemplateFile;
                break;
        }
        return fileName;
    }

    public static String[] getColNames(String fileType) {
        String[] colNames = null;
        switch (fileType) {
            case regExcelType:
                colNames = regTempCols;
                break;
            case balanceType:
                colNames = balanceTempCols;
                break;
        }
        return colNames;
    }
}
