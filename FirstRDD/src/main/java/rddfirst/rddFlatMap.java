package rddfirst;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.Iterator;

public class rddFlatMap {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        JavaSparkContext cont = new JavaSparkContext("local","Map Func");
        JavaRDD<String> rawData = cont.textFile("C:\\WorldCup\\WorldCups.csv");


        JavaRDD<String> FlatmapRdd = rawData.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(",")).iterator();
            }
        });

        FlatmapRdd.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
        

/*
        JavaRDD<CupModel> italy = newRDD.filter(new Function<CupModel, Boolean>() {
            public Boolean call(CupModel cupModel) throws Exception {
                return cupModel.birinci.equals("Italy");
            }
        });

        JavaRDD<CupModel> toplamgol = newRDD.filter(new Function<CupModel, Boolean>() {
            public Boolean call(CupModel cupModel) throws Exception {
                return cupModel.getToplamgol()<120;
            }
        });

        toplamgol.foreach(new VoidFunction<CupModel>() {
          public void call(CupModel cupModel) throws Exception {
              System.out.println(cupModel.getYil()+" "+cupModel.getEvsahibi());
          }
      });

        ///   System.out.println(rawData.count());
*/

    }
}
