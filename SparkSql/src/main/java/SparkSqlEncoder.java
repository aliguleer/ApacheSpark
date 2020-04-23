import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.*;


public class SparkSqlEncoder {
    public static void main(String[] args) {


        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");
        SparkSession sparkSession =SparkSession.builder().appName("FirstSql").master("local").getOrCreate();

        Encoder<meyve> fruitEncoder = Encoders.bean(meyve.class);

        Dataset<meyve> data = sparkSession.read().json("C:\\Dosya\\example_3.json").as(fruitEncoder);


        data.foreach( kv ->{
                System.out.println(kv.getColor());
        });


    }
}
