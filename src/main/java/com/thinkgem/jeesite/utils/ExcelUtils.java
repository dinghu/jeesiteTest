package com.thinkgem.jeesite.utils;

import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ExcelUtils {

    public static abstract class ImportExcelListener<T> {
        public abstract void onImport(List<T> list);
    }

    public static <T> void importExcel(MultipartFile file, Class<T> tClass, ImportExcelListener<T> importExcelListener) throws Exception {
        ImportExcel ei = new ImportExcel(file, 1, 0);
        List<T> list = ei.getDataList(tClass);
        importExcelListener.onImport(list);
    }
}
