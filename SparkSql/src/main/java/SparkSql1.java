import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;

public class SparkSql1 {
    public static void main(String[] args) {


        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");
        SparkSession sparkSession =SparkSession.builder().appName("FirstSql").master("local").getOrCreate();
        Dataset<Row> data = sparkSession.read().json("C:\\Dosya\\example_1.json");

        data.show();
        System.out.println("------------------");
        Dataset<Row> sData = data.select("fruit", "color");
        sData.show();
        System.out.println("------------------");

        Dataset<Row> fruit = data.groupBy("fruit").sum("Count");

        fruit.show();




    }
}
