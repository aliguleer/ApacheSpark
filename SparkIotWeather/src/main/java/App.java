import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;

public class App {
    public static void main(String[] args) throws StreamingQueryException {



        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        SparkSession sparkSession = SparkSession.builder().appName("SparkStreaminglistener").master("local").getOrCreate();

        StructType weatherF= new StructType()
                .add("quarter","string")
                .add("heatType","string")
                .add("heat","string")
                .add("windType","string")
                .add("wind","string");

        Dataset<Row> rawData = sparkSession.readStream().schema(weatherF).option("sep", ",").csv("D:\\Udemy\\BigData1\\SparkStreaming\\*");

        Dataset<Row> heatData = rawData.select("quarter", "heat").where("heat>29");




        heatData.show();

      //  StreamingQuery start = heatData.writeStream().outputMode("complete").format("console").start();

        //start.awaitTermination();
        

    }
}
