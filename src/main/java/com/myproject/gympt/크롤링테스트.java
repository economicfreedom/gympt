package com.myproject.gympt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class 크롤링테스트 {
    public static void main(String[] args) {
         String url = "https://www.youtube.com/results?search_query=%EC%9A%B4%EB%8F%99+%ED%95%A0%EB%96%84+%EB%93%A3%EA%B8%B0+%EC%A2%8B%EC%9D%80+%EC%9D%8C%EC%95%85";



        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Elements elementsByClass = document.getElementsByClass("yt-simple-endpoint style-scope ytd-video-renderer");

            for (int i = 0; i < elementsByClass.size(); i++) {
                System.out.println(elementsByClass.get(i).text());
                System.out.println(elementsByClass.get(i).attr("href"));

            }

        }catch (Exception e){
            e .printStackTrace();
        }


    }
}
