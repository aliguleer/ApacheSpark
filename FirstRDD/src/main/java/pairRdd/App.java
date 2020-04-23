package pairRdd;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import rddfirst.CupModel;
import scala.Tuple2;

public class App {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        JavaSparkContext cont = new JavaSparkContext("local","Map Func");
        JavaRDD<String> rawData = cont.textFile("C:\\WorldCup\\WorldCups.csv");

        JavaRDD<CupModel> newRDD = rawData.map(new Function<String, CupModel>() {
            public CupModel call(String line) throws Exception {
                String[] str = line.split(",");
                return new CupModel(str[0], str[1], str[2], str[3], str[4], str[5],
                        Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]), str[9]);

            }
        });

        //
        JavaPairRDD<String, String> FirstPair = newRDD.mapToPair(new PairFunction<CupModel, String, String>() {
            public Tuple2<String, String> call(CupModel cupModel) throws Exception {
                return new Tuple2<String, String>(cupModel.getBirinci(), cupModel.getToplamkatilimci());
            }
        });

        JavaPairRDD<String, Iterable<String>> resultData = FirstPair.groupByKey();

        resultData.foreach(new VoidFunction<Tuple2<String, Iterable<String>>>() {
            public void call(Tuple2<String, Iterable<String>> line) throws Exception {
                System.out.println(line._1+" - "+line._2);

            }
        });


/*
        FirstPair.foreach(new VoidFunction<Tuple2<String, String>>() {
            public void call(Tuple2<String, String> line) throws Exception {
                System.out.println(line._1+" "+line._2);
            }
        });
        */

    }
}
