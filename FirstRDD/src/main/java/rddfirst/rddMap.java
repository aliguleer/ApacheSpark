package rddfirst;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

public class rddMap {
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

      newRDD.foreach(new VoidFunction<CupModel>() {
          public void call(CupModel cupModel) throws Exception {
              System.out.println(cupModel.getYil()+" "+cupModel.getEvsahibi());
          }
      });

        ///   System.out.println(rawData.count());


    }
}
