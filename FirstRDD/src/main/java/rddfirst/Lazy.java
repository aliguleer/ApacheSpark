package rddfirst;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

// Lazy evulation
// count gibi birşey kodun çalışması için gerekli yoksa kod çalışmaz gibi birşry
public class Lazy {
    public static void main(String[] args) {

        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        JavaSparkContext cont = new JavaSparkContext("local","Map Func");
        JavaRDD<String> rawData = cont.textFile("C:\\WorldCup\\WorldCups.csv");

        JavaRDD<CupModel> newRDD = rawData.map(new Function<String, CupModel>() {
            public CupModel call(String line) throws Exception {
                System.out.println(line); // bu print de çalışmaz eğer aşağıda countu eklediğimizde göstermeye başlar
                String[] str = line.split(",");
                return new CupModel(str[0], str[1], str[2], str[3], str[4], str[5],
                        Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]), str[9]);

            }
        });

        /// yukarıda hiçbir işlem yapmaz action görene kadar
        ///////--------------------------------------/////
        ///count gördüğünde rdd işlemlerini de yapıp datayı yükleyip aksiyonu alır
       System.out.println(newRDD.count());
        ///




    }
}
