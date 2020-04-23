
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSql4 {
    public static void main(String[] args) {


        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        SparkSession sparkSession =SparkSession.builder().appName("FirstSql").master("local").getOrCreate();

        //option multiline true is for serail jason file
        Dataset<Row> data = sparkSession.read().json("C:\\Dosya\\example_1.json");


        data.createOrReplaceTempView("fruitTbl");


        Dataset<Row> sqldata = sparkSession.sql("select * from fruitTbl");
        sqldata.show();


        Dataset<Row> sqldata2 = sparkSession.sql("select fruit,color from fruitTbl");
        Dataset<Row> dataS = data.select("fruit", "color");
        sqldata2.show();
        dataS.show();
//
        Dataset<Row> sql = sparkSession.sql("select fruit,count(*) countx from fruitTbl group by fruit");


        sql.show();

/*
        data.createOrReplaceGlobalTempView("fruitTbl");
        /// global sadece classter yapısında kullanılabiliyor sql gibi global claster ayakta oldukça duracaktır temp sql deki gibi sessiın kapanana kadar tutuluyor
        */

    }
}
