import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSql2 {
    public static void main(String[] args) {


        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        SparkSession sparkSession =SparkSession.builder().appName("FirstSql").master("local").getOrCreate();

        //option multiline true is for serail jason file
        Dataset<Row> data = sparkSession.read().option("multiline",true).json("C:\\Dosya\\datasample.json");

        data.show();

        Dataset<Row> filter = data.filter(new Column("gender").equalTo("Male"));
        filter.show();

        Dataset<Row> filter2 = data.filter(new Column("gender").contains("ale").desc());
        filter2.show();






    }
}
