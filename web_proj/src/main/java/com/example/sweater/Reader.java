package com.example.sweater;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.Store;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private List<Product> products;
    private List<Store> stores;

    public List<Product> getProducts() {
        return products;
    }

    public List<Store> getStores() {
        return stores;
    }

    private InputStream inputStream;
    private HSSFWorkbook workBook;

    public Reader(String filePath) {
        products = new ArrayList<>();
        stores = new ArrayList<>();

        try {
            inputStream = new FileInputStream( new File(filePath));
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Store> ReadAndParseStores() {
        Sheet sheet = workBook.getSheetAt(1);
        for (int i  = 1; i < 21 ; i++) {
            Store tmp = new Store();
            tmp.setFullName(sheet.getRow(i).getCell(0).getStringCellValue());
            tmp.setShortName(tmp.getFullName().split(" ")[0]);
            stores.add(tmp);
        }
        return stores;
    }

    public List<Product> ReadAndParseProducts(int count) {
        //разбираем второй лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(1);

        //проходим по всему листу
        for (int i  = 22; i < count + 22 ; i++) {

            Product tmp = new Product();
            //добавляем id товара
            try {
                if (sheet.getRow(i).getCell(0).getCellType() == CellType.NUMERIC) {
                    tmp.setId((long) sheet.getRow(i).getCell(0).getNumericCellValue());
                } else if (sheet.getRow(i).getCell(0).getCellType() == CellType.STRING) {
                    tmp.setId(Long.valueOf(sheet.getRow(i).getCell(0).getStringCellValue()));
                }
            } catch (NumberFormatException e) {
                continue;
            }

            //бобавляем название товара
            tmp.setName(sheet.getRow(i).getCell(1).getStringCellValue());

            //добавляем ссылки на магизины
            for (int j = 2 ;j < 22; j++) {
                String tmp2 = sheet.getRow(i).getCell(j).getStringCellValue();
                if (tmp2.compareTo("") != 0) {
                    tmp.addStore(stores.stream().filter(x -> x.getShortName().equals(tmp2)).findFirst().get());
                }
            }

            // добавляем цену
            tmp.setPrice((int) sheet.getRow(i).getCell(22).getNumericCellValue());

            // добавляем бонусы
            tmp.setBonuses((int) sheet.getRow(i).getCell(23).getNumericCellValue());
            products.add(tmp);
        }

        return products;
    }

}