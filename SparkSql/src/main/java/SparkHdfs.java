import org.apache.spark.sql.*;

import java.util.Arrays;

public class SparkHdfs {
    public static void main(String[] args) {


        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");
        SparkSession sparkSession =SparkSession.builder().appName("FirstSql").master("local").getOrCreate();

        Encoder<MovieModel> movieModelEncoder= Encoders.bean(MovieModel.class);

        try{
        Dataset<MovieModel> hdfsdata = sparkSession.read()
                .option("infershema", true)
                .option("header", true)
                .csv("hdfs://localhost:8020/data/ratingsr.csv").as(movieModelEncoder);
///pig
       // System.out.println(hdfsdata.count());
        hdfsdata.foreach(data->{
                        System.out.println(data.getMovieId());
        });

        Dataset<MovieModel> ratings = hdfsdata.filter(new Column("ratings").equalTo("5.0"));
        ratings.show();
        System.out.println(ratings.count());

        Dataset<Row> gmov = hdfsdata.groupBy(new Column("movieId")).count();

        gmov.show();

        gmov.write().format("csv").save("hdfs://localhost/:8020/data/groupData.csv");




        }

        catch (Exception e) {
            System.out.println(e);

        }
    }
}
