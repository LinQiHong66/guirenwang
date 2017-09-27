package common.autodoc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.common.autocreate.bean.VersionType;
import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.autodoc.bean.ConfigBean;
import common.autodoc.bean.DocBean;
import common.autodoc.bean.ParamField;

/**
 * pdf生成工具
 * 
 * @author dixon
 * @date 2013/11/06
 */
public class ITextPDFUtil
{
    
    /**
     * 创建PDF文件
     * 
     * @param beanList
     */
    public static void createPDF(List<DocBean> beanList, List<DocBean> baseParamList, ConfigBean configBean)
    {
        System.out.println(">>>>生成pdf文档...");
        String version = "v" + XmlUtil.getVersion();
        String project = XmlUtil.getProject();
        long start = System.currentTimeMillis();
        
        String docName = "服务端接口文档-" + project;
        String filePath = configBean.getFilePath();
        Document document = null;
        PdfWriter writer = null;
        try
        {
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            
            // 摘要 在open方法前
            document.addTitle(docName);
            document.addSubject(docName);
            document.addAuthor("snsec");
            document.addCreator("autodoc");
            document.addCreationDate();
            document.addHeader(version, docName);
            
            document.open();
            
            writer.setViewerPreferences(PdfWriter.PageModeUseOutlines);// 显示大纲
            
            // 构建章节(list -> map)
            Map<String, List<DocBean>> tempMap = new HashMap<String, List<DocBean>>(50);
            for(DocBean bean : beanList)
            {
                List<DocBean> list = tempMap.get(bean.getModelName().trim());
                if(list == null)
                {
                    list = new ArrayList<DocBean>();
                    tempMap.put(bean.getModelName().trim(), list);
                }
                
                if(configBean.isProductCurrnetVersionInterface())
                {
                    if(!bean.getVersion().equals(configBean.getCurrentVersion().getDes()))
                    {
                        if(StringUtils.isNotBlank(bean.getUpdateVersion()))
                        {
                            if(!bean.getUpdateVersion().equals(configBean.getCurrentVersion().getDes()))
                            {
                                continue;
                            }
                        }
                        else
                        {
                            continue;
                        }
                        
                    }
                    
                }
                
                if(configBean.isProductAllNotDeprecatedInterface())
                {
                    if(bean.isDeprecated())
                    {
                        continue;
                    }
                    if(StringUtils.isNotBlank(bean.getStopVersion()))
                    {
                        if(!bean.getStopVersion().equals(configBean.getCurrentVersion().getDes()))
                        {
                            continue;
                        }
                    }
                    
                }
                list.add(bean);
            }
            
            Map<String, List<DocBean>> chapterMap = new HashMap<String, List<DocBean>>(50);
            for(Map.Entry<String, List<DocBean>> entry : tempMap.entrySet())
            {
                String key = entry.getKey();
                List<DocBean> valueList = entry.getValue();
                if(!valueList.isEmpty())
                {
                    chapterMap.put(key, valueList);
                }
            }
            
            // BaseFont baseFontChinese = BaseFont.createFont("STSong-Light",
            // "UniGB-UCS2-H",
            // BaseFont.NOT_EMBEDDED);
            BaseFont baseFontChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);
            
            float fontSize = 15f;
            Font chapterF = new Font(baseFontChinese, fontSize, Font.ITALIC);// 章节字体
            Font sessionF = new Font(baseFontChinese, fontSize - fontSize * 0.25f, Font.ITALIC);// 子区域字体
            Font contentF = new Font(baseFontChinese, fontSize - fontSize * 0.35f, Font.NORMAL);
            float leading = fontSize;// 行间距
            
            int i = 1;
            for(Map.Entry<String, List<DocBean>> entry : chapterMap.entrySet())
            {
                String key = entry.getKey();
                List<DocBean> valueList = entry.getValue();
                Collections.sort(valueList, new ExcelComparator());
                
                Paragraph chapterTitle = new Paragraph(key, chapterF);
                Chapter chapter = new Chapter(chapterTitle, i++);
                
                for(DocBean value : valueList)
                {
                    Paragraph sessionTitle = new Paragraph(leading * 2, value.getActionName(), sessionF);
                    
                    if(StringUtils.isNotBlank(value.getUpdateVersion())
                            && value.getUpdateVersion().equals(configBean.getCurrentVersion().getDes()))
                    {
                        Chunk versionChunk = new Chunk("(" + value.getUpdateVersion() + ")", sessionF);
                        sessionTitle.add(versionChunk);
                        
                        versionChunk = new Chunk("【修改】", new Font(baseFontChinese, fontSize - fontSize * 0.45f, Font.BOLD,
                                BaseColor.BLUE));
                        versionChunk.setTextRise(-1.5f);
                        sessionTitle.add(versionChunk);
                    }
                    
                    if(value.getVersion().equals(configBean.getCurrentVersion().getDes()))
                    {
                        Chunk versionChunk = new Chunk("(" + value.getVersion() + ")", sessionF);
                        sessionTitle.add(versionChunk);
                        
                        versionChunk = new Chunk("【新增】", new Font(baseFontChinese, fontSize - fontSize * 0.45f, Font.BOLD,
                                BaseColor.RED));
                        versionChunk.setTextRise(-1.5f);
                        sessionTitle.add(versionChunk);
                    }
                    
                    // 标题
                    Section session = chapter.addSection(sessionTitle);
                    // 描述
                    Paragraph desc = new Paragraph(leading, value.getActionDesc(), new Font(baseFontChinese, contentF.getSize(),
                            Font.ITALIC, BaseColor.GRAY));
                    session.add(desc);
                    
                    // 备注
                    if(value.getModifyRecodes() != null
                            && value.getModifyRecodes()[0] != null && value.getModifyRecodes()[0].length() > 0)
                    {
                        Annotation note = new Annotation(value.getActionName(), value.showModifyRecods());
                        session.add(note);
                    }
                    
                    // 普通样式
                    // simpleRender(fontChinese, leading, value, session);
                    
                    // 表格
                    tableRender(contentF, leading, value, session, configBean);
                    
                }
                
                document.add(chapter);
            }
            //创建者和时间
            
            // 文档说明
            Paragraph chapterTitle = new Paragraph("文档说明", chapterF);
            Chapter chapter = new Chapter(chapterTitle, i++);
            // 章节1
            Paragraph sessionTitle = new Paragraph(leading * 2, "基础响应说明", sessionF);
            Section session = chapter.addSection(sessionTitle);
            StringBuilder description = new StringBuilder();
            if(configBean.getCurrentVersion().getDes().equals(VersionType.V200.getDes())){
            description.append("同第一版");
            }else{
//            	description.append(beanList);
                /*.append("QuickBaseResponse 定义为所有接口的基础响应，所有接口的响应体均包含。	\n\n"
                        + ""
                        + ""
                        + "示例：1.1收集经纬度信息接口"
                        + "--返回的数据样例如下： \n"
                        + "{\"QuickBaseResponse\":{\"code\":\"0\",\"desc\":\"处理成功\"}}\n"
                        + "" 
                        + "\n"
                        + "示例：2.7登录接口--返回的数据样例如下：\n"
                        + "{\"QuickBaseResponse\":{\"code\":\"0\",\"desc\":\"处理成功\",\"bid\":\"2323\",\"token\":\"65897455441212544\",\"mobile\":\"13021356489\"}}"
                        + "\n\n示例：3.1查看卖家商铺信息商品集合接口--返回的数据样例如下\n"
                        + "{\"QuickBaseResponse\":{\"code\":\"0\",\"desc\":\"处理成功\",\"goodInfoList\":[{\"goodId\":\"00003\",\"goodTopicName\":\"店家推荐3月新品\",\"orignalPrice\":\"298.20\",\"price\":\"255.00\",\"totalSales\":\"20\",\"imgUrl\":\"http://xx/xx/xx/xx\"},{\"goodId\":\"00004\",\"goodTopicName\":\"店家推荐4月新品\",\"orignalPrice\":\"300.20\",\"price\":\"300.00\",\"totalSales\":\"30\",\"imgUrl\":\"http://xx/xx/xx/xx\"}]}}"
                        + "" + "" + "\n\ncode：处理结果状态码。  \ndesc：处理结果描述。\n" + "(状态码及描述详见" + (--i) + ".2章节)");*/
            }
            // 描述
            Paragraph desc = new Paragraph(leading, description.toString(), new Font(baseFontChinese,
                    fontSize - fontSize * 0.35f, Font.NORMAL));
            session.add(desc);
            
            // 章节2(状态码描述)
            //响应状态码及描述
            Paragraph sessionTitle1 = new Paragraph(leading * 1, "状态码描述", sessionF);
            Section session1 = chapter.addSection(sessionTitle1);
            // 生成状态码描述表格
            ResponseCode codeResponse = new ResponseCode();
            tableStatusRender(codeResponse.getClass(),contentF, leading, session1);
            // 章节3(状态码描述)
            /*//响应状态码及描述
            Paragraph sessionTitle2 = new Paragraph(leading * 1, "profit操作状态码及描述", sessionF);
            Section session2 = chapter.addSection(sessionTitle2);
            // 生成状态码描述表格
            ProfitCodeResponse codeResponse1 = new ProfitCodeResponse();
            tableStatusRender(codeResponse1.getClass(),contentF, leading, session2);*/
            document.add(chapter);
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(null != document) document.close();
            if(null != writer) writer.close();
        }
        
        long end = System.currentTimeMillis();
        
        System.out.println(">>>>生成文档完毕，花费时间：" + (end - start) + "ms");
        System.out.println(">>>>文档位置：" + filePath);
    }
    
    /**
     * 普通渲染样式
     * 
     * @param fontChinese
     * @param leading
     * @param value
     * @param session
     */
    private static void simpleRender(Font fontChinese, float leading, DocBean value, Section session)
    {
        Paragraph urlPar = new Paragraph(leading, "请求地址：" + value.getActionUrl(), fontChinese);
        
        Paragraph paramsPar = new Paragraph(leading, "请求参数：", fontChinese);
        com.itextpdf.text.List list = new com.itextpdf.text.List(false, leading);
        list.setListSymbol("·");
        for(ParamField field : value.getParams())
        {
            list.add(new ListItem(field.getName() + "<" + field.getType() + ">:" + field.getNote(), fontChinese));
        }
        paramsPar.add(list);
        
        Paragraph responsePar = new Paragraph(leading, "响应参数：\n" + value.getResponse().toString(), fontChinese);
        
        session.add(urlPar);
        session.add(paramsPar);
        session.add(responsePar);
    }
    
    /**
     * table渲染样式
     * 
     * @param contentFont
     * @param leading
     * @param docBean
     * @param session
     * @throws DocumentException
     */
    private static void tableRender(Font contentFont, float leading, DocBean docBean, Section session, ConfigBean configBean)
            throws DocumentException
    {
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f); // 占全屏
        float[] f = { 0.15f, 0.85f };
        table.setWidths(f); // 列比例
        // table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        // table.getDefaultCell().setPadding(0);
        table.setSpacingBefore(leading - leading * 0.4f);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setLeading(leading - leading * 0.13f, 0);
        // table.getDefaultCell().setMinimumHeight(10*leading);
        table.setSplitLate(false); // 可以跨页
        table.setSplitRows(true);
        
        // 作者
        if(docBean.getAuthor() != null)
        {
            PdfPCell authorCel = new PdfPCell(new Paragraph("作者：", contentFont));
            authorCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            authorCel.setMinimumHeight(leading + 0.2f * leading);
            PdfPCell authorCel2 = new PdfPCell(new Paragraph(docBean.getAuthor().getDes(), contentFont));
            table.addCell(authorCel);
            table.addCell(authorCel2);
        }
        
        // 创建时间
        if(StringUtils.isNotBlank(docBean.getCreateTime()))
        {
            PdfPCell updateTimeCel = new PdfPCell(new Paragraph("创建时间：", contentFont));
            updateTimeCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            updateTimeCel.setMinimumHeight(leading + 0.2f * leading);
            PdfPCell updateTimeCel2 = new PdfPCell(new Paragraph(docBean.getCreateTime(), contentFont));
            table.addCell(updateTimeCel);
            table.addCell(updateTimeCel2);
        }
        
        // 创建版本
        if(StringUtils.isNotBlank(docBean.getVersion()))
        {
            PdfPCell urlCel = new PdfPCell(new Paragraph("创建版本：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            PdfPCell urlCel2 = new PdfPCell(new Paragraph(docBean.getVersion(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 接口开发进度
        if(configBean != null && configBean.isDevelop())
        {
            if(StringUtils.isNotBlank(docBean.getProgress()))
            {
                PdfPCell urlCel = new PdfPCell(new Paragraph("接口进度：", contentFont));
                urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
                urlCel.setMinimumHeight(leading + 0.2f * leading);
                PdfPCell urlCel2 = new PdfPCell(new Paragraph(docBean.getProgress(), contentFont));
                table.addCell(urlCel);
                table.addCell(urlCel2);
            }
        }
        
        // 修改版本
        if(StringUtils.isNotEmpty(docBean.getUpdateVersion()))
        {
            PdfPCell urlCel = new PdfPCell(new Paragraph("修改版本：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            PdfPCell urlCel2 = new PdfPCell(new Paragraph(docBean.getUpdateVersion(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 修改时间
        if(StringUtils.isNotEmpty(docBean.getUpdateTime()))
        {
            PdfPCell urlCel = new PdfPCell(new Paragraph("修改时间：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            PdfPCell urlCel2 = new PdfPCell(new Paragraph(docBean.getUpdateTime(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 修改人
        if(StringUtils.isNotEmpty(docBean.getUpdateBy()))
        {
            PdfPCell urlCel = new PdfPCell(new Paragraph("修改人：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            PdfPCell urlCel2 = new PdfPCell(new Paragraph(docBean.getUpdateBy(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 接口状态
        Font deprecatedFont = contentFont;
        if(docBean.isDeprecated())
        {
            deprecatedFont = new Font(contentFont.getBaseFont(), contentFont.getSize(), contentFont.getStyle(), new BaseColor(
                    255, 0, 0));
        }
        
        PdfPCell urlCel = new PdfPCell(new Paragraph("接口状态：", contentFont));
        urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
        urlCel.setMinimumHeight(leading + 0.2f * leading);
        PdfPCell urlCel2 = new PdfPCell(new Paragraph(docBean.isDeprecated() ? "已停用" : "使用中", deprecatedFont));
        table.addCell(urlCel);
        table.addCell(urlCel2);
        
        // 接口停用版本
        if(StringUtils.isNotEmpty(docBean.getStopVersion()))
        {
            urlCel = new PdfPCell(new Paragraph("停用版本：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            urlCel2 = new PdfPCell(new Paragraph(docBean.getStopVersion(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 请求地址
        if(StringUtils.isEmpty(docBean.getActionUrl()))
        {
            urlCel = new PdfPCell(new Paragraph("请求地址：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            urlCel2 = new PdfPCell(new Paragraph(docBean.getMappingUrl(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 请求方式
        if(StringUtils.isNotEmpty(docBean.getRequestMode()))
        {
            urlCel = new PdfPCell(new Paragraph("请求方式：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            urlCel2 = new PdfPCell(new Paragraph(docBean.getRequestMode(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 请求参数
        // 连接锚点
        Paragraph pp = new Paragraph("请求参数：" /* + value.getClassName() */, contentFont);
        
        PdfPCell paramsCel = new PdfPCell(pp);
        paramsCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
        paramsCel.setMinimumHeight(leading + 0.2f * leading);
        
        System.out.println(docBean.getParams().size() + "999999999999999");
        Paragraph pp2 = new Paragraph(docBean.showParam(), contentFont);
        
        PdfPCell paramsCel2 = new PdfPCell(pp2);
        
        table.addCell(paramsCel);
        table.addCell(paramsCel2);
        
        // 请求举例
        if(true)
        {
            urlCel = new PdfPCell(new Paragraph("请求举例：", contentFont));
            urlCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            urlCel.setMinimumHeight(leading + 0.2f * leading);
            urlCel2 = new PdfPCell(new Paragraph(docBean.showCallExample(), contentFont));
            table.addCell(urlCel);
            table.addCell(urlCel2);
        }
        
        // 响应参数
        if(docBean.getResponse() != null)
        {
            PdfPCell responseCel = new PdfPCell(new Paragraph("响应参数：", contentFont));
            responseCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            responseCel.setMinimumHeight(leading + 0.2f * leading);
            
            String response = "";
            if(StringUtils.isNotBlank(docBean.getResponse().toString()))
            {
                response = docBean.getResponse().toString();
            }
            else
            {
                response = "基础响应 \n";
            }
            PdfPCell responseCel2 = new PdfPCell(new Paragraph(response, contentFont));
            
            table.addCell(responseCel);
            table.addCell(responseCel2);
        }
        // 备注
        if(docBean.getModifyRecodes() != null && StringUtils.isNotEmpty(docBean.getModifyRecodes()[0]))
        {
            PdfPCell noteCel = new PdfPCell(new Paragraph("备注：", contentFont));
            noteCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            noteCel.setMinimumHeight(leading + 0.2f * leading);
            PdfPCell noteCel2 = new PdfPCell(new Paragraph(docBean.showModifyRecods(), contentFont));
            table.addCell(noteCel);
            table.addCell(noteCel2);
        }
        
        session.add(table);
    }
    
    private static void tableStatusRender(Class mobCode,Font contentF, float leading, Section session1) throws Exception
    {
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(30f);
        float[] f = { 0.15f, 0.25f };
        table.setWidths(f); // 列比例
        
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        // table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        // table.getDefaultCell().setPadding(0);
        table.setSpacingBefore(leading - leading * 0.4f);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setLeading(leading - leading * 0.13f, 0);
        // table.getDefaultCell().setMinimumHeight(10*leading);
        table.setSplitLate(false); // 可以跨页
        table.setSplitRows(true);
        //参数
//        MobResponseCode mobCode = new MobResponseCode();
//        OptCodeResponse mobCode = new OptCodeResponse();
        Field[] fields = mobCode.getDeclaredFields();
        
        for(Field field : fields)
        {
            if(!field.getName().contains("DESC"))
            {
                PdfPCell noteCel = new PdfPCell(new Paragraph((String) field.get(mobCode.getClass()), contentF));
                noteCel.setHorizontalAlignment(Element.ALIGN_RIGHT);
                noteCel.setMinimumHeight(leading + 0.2f * leading);
                table.addCell(noteCel);
            }
            else
            {
                PdfPCell noteCel2 = new PdfPCell(new Paragraph((String) field.get(mobCode.getClass()), contentF));
                table.addCell(noteCel2);
            }
        }
        session1.add(table);
        
    }
    
}
