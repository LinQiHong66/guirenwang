package common.autodoc.util;

import java.io.File;

import common.autodoc.analyzer.QuickControllerAnalyzer;


public class Validater
{
    public static boolean isRightSourcePath()
    {
        System.out.println(">>>>验证源文件......");
        
        String sourcePath = new File("").getAbsolutePath();
        
        File sFile = new File(sourcePath);
        File[] childFiles = sFile.listFiles();
        if(childFiles.length == 0)
        {
            System.out.println(">>>>源文件目录为空文件夹");
            return false;
        }
        // 是否有src目录
        boolean haveSrc = false;
        for(File file : childFiles)
        {
            if(file.isDirectory() || "src".equals(file.getName()))
            ;
            haveSrc = true;
        }
        if(!haveSrc)
        {
            System.out.println(">>>>源文件目录错误");
            return false;
        }
        
        // 是否有action目录，以及相关模块
        String actionPath = sourcePath + File.separator + QuickControllerAnalyzer.CONTROLLER_PATH;
        File actionFile = new File(actionPath);
        if(!actionFile.exists() || !actionFile.isDirectory() || actionFile.listFiles().length == 0)
        {
            System.out.println(">>>>action目录错误");
            return false;
        }
        
        System.out.println(">>>>ok\n");
        return true;
    }
    
}
