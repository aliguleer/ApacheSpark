import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.beans.Encoder;
import java.util.Arrays;
import java.util.Iterator;

public class SparkListener {
    public static void main(String[] args) throws StreamingQueryException {

// en baştan sonuna kadar her seferinde analiz eder
        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        SparkSession sparkSession =SparkSession.builder().appName("SparkStreamingMsglistener").master("local").getOrCreate();

        Dataset<Row> rawData = sparkSession.readStream().format("socket").option("host", "localhost").option("port", "8005").load();

        Dataset<String> data = rawData.as(Encoders.STRING());

        Dataset<String> stringDataset = data.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        }, Encoders.STRING());

        Dataset<Row> groupData = stringDataset.groupBy("value").count();

        StreamingQuery start=groupData.writeStream().outputMode("complete").format("console").start();


        start.awaitTermination();

    }
}
