import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Parse {
    public static void main( String[] args ) throws IOException{
        Document doc = Jsoup.connect("https://oxu.az/politics").get();
        String title = doc.title();
        System.out.println("Title: " + title);
        List<String> urls = new ArrayList<>();

        Document docs = Jsoup.connect("https://oxu.az/politics").get();


        Elements a = docs.select("a.news-i-inner[href]");
        for(Element element : a) {
            String url = element.absUrl("href");
            urls.add(url);
        }

        List<Article> articles = new ArrayList<>();
        for (String url : urls) {
            Document document = Jsoup.connect(url).get();
            String headline = document.select("h1").text();
            Element images = document.selectFirst("img.news-img.medium-up[src]");
            String imgLink = images.absUrl("src");
            String content = document.select("div p").text();

            //System.out.println(headline);
            //System.out.println(imgLink);
            //System.out.println(content);
            //System.out.println('\n');

            Article article = new Article();
            article.setHeadline(headline);
            article.setImg(imgLink);
            article.setContent(content);

            articles.add(article);
        }
        articles.forEach(System.out::println);

        try {
            String filename = "C:\\new\\Articles.xlsx";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Try");
            int rowCount = 0;

            for (Article book : articles) {
                Row row = sheet.createRow(++rowCount);
                row.createCell(1).setCellValue(book.getHeadline());
                row.createCell(2).setCellValue(book.getImg());
                row.createCell(3).setCellValue(book.getContent());
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Excel file has been generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeExcel(List<Article> listBook, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowCount = 0;

        for (Article book : listBook) {
            Row row = sheet.createRow(++rowCount);
            writeBook(book, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeBook(Article book, Row row) {
        Cell cell = row.createCell(1);
        cell.setCellValue(book.getHeadline());

        cell = row.createCell(2);
        cell.setCellValue(book.getImg());

        cell = row.createCell(3);
        cell.setCellValue(book.getContent());
    }
}
