import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSql3 {
    public static void main(String[] args) {


        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        SparkSession sparkSession =SparkSession.builder().appName("FirstSql").master("local").getOrCreate();

        //option multiline true is for serail jason file
        Dataset<Row> data = sparkSession.read().json("C:\\Dosya\\example_1.json");

        data.show();

        //Dataset<Row> fruit = data.groupBy(new Column("fruit")).count();
        Dataset<Row> fruit = data.groupBy("fruit").count();

        fruit.show();

        Dataset<Row> avg = data.groupBy("fruit").avg("count");

        avg.show();

        data.groupBy(new Column("color")).sum("count").show();

        data.groupBy(new Column("color")).max("count").show();




    }
}
