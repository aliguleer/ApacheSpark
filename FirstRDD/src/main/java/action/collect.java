package action;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import rddfirst.CupModel;

import java.util.List;

public class collect {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        JavaSparkContext cont = new JavaSparkContext("local","Map Func");
        JavaRDD<String> rawData = cont.textFile("C:\\WorldCup\\WorldCups.csv");

       // System.out.println( rawData.count());

        //System.out.println( rawData.first());

        //collect tüm classterlardan verileri çeker listeye atar
        List<String> collectList = rawData.collect();

        for (String x :collectList)
            System.out.println(x);

        List<String> take = rawData.take(4); /// ilk 4 kaydı getirir
        List<String> strings = rawData.takeSample(false, 3);// aynı kayıt gelebilir mi, kaçtane gelsin

        JavaRDD<CupModel> newRDD = rawData.map(new Function<String, CupModel>() {
            public CupModel call(String line) throws Exception {
                String[] str = line.split(",");
                return new CupModel(str[0], str[1], str[2], str[3], str[4], str[5],
                        Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]), str[9]);

            }
        });
        JavaRDD<CupModel> toplamgol = newRDD.filter(new Function<CupModel, Boolean>() {
            public Boolean call(CupModel cupModel) throws Exception {
                return cupModel.getToplamgol()<120;
            }
        });


       // toplamgol.saveAsTextFile("C:\\WorldCups\\");

        //toplamgol.foreach(); buda bir actiondır

        JavaRDD<String> rusult = toplamgol.map(new Function<CupModel, String>() {
            public String call(CupModel cupModel) throws Exception {
                return cupModel.getYil() + " " + cupModel.getToplamgol();

            }
        });


        rusult.saveAsTextFile("C:\\WorldCup\\");


    }
}