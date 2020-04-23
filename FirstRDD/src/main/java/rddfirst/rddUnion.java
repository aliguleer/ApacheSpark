package rddfirst;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import scala.sys.SystemProperties;

public class rddUnion {
    public static void main(String[] args) {
       System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

    JavaSparkContext cont = new JavaSparkContext("local","Map Func");
    JavaRDD<String> rawData = cont.textFile("C:\\WorldCup\\WorldCups.csv");
    JavaRDD<String> rawData2 = cont.textFile("C:\\WorldCup\\WorldCupPlayers.csv");

        JavaRDD<String> resultRdd = rawData.union(rawData2);

        System.out.println("1. Rdd:"+rawData.count());
        System.out.println("2. Rdd:"+rawData2.count());
        System.out.println("3. Rdd:"+resultRdd.count());



    }
}
