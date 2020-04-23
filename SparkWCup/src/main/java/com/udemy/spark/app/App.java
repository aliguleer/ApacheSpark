package com.udemy.spark.app;

import com.google.common.collect.Iterators;
import com.mongodb.spark.MongoSpark;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import org.bson.Document;


import java.util.Iterator;

public class App {
    public static void main(String[] args) {

        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        SparkSession spark= SparkSession.builder()
                .master("local")
                .appName("MongoSpark")
                .config("spark.mongodb.input.uri","mongodb://127.0.0.1/test.WordCupCollection")
                .config("spark.mongodb.output.uri","mongodb://127.0.0.1/test.WordCupCollection")
                .getOrCreate();

        JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
        JavaRDD<String> RawData = sc.textFile("C:\\WorldCup\\WorldCupPlayers.csv");

        //System.out.println(RawData.count());

        JavaRDD<PlayersModel> PlayersRdd = RawData.map(new Function<String, PlayersModel>() {
            public PlayersModel call(String line) throws Exception {
                String[] dizi = line.split(",",-1);

                return new PlayersModel(dizi[0], dizi[1], dizi[2], dizi[3], dizi[4], dizi[5], dizi[6], dizi[7], dizi[8]);
            }
        });
/*
        PlayersRdd.foreach(new VoidFunction<PlayersModel>() {
            public void call(PlayersModel playersModel) throws Exception {
                System.out.println("playersModel = " + playersModel.getPlayerName());
            }
        });
*/
/*
//messi dünya kupasında oynadığı maç

        JavaRDD<PlayersModel> messiRdd = PlayersRdd.filter(new Function<PlayersModel, Boolean>() {
            public Boolean call(PlayersModel playersModel) throws Exception {
                return playersModel.getPlayerName().equals("MESSI");
            }
        });

        System.out.println("args = " + messiRdd.count());
*/


    ///oyuncular kaç maç yaptı

        JavaPairRDD<String, String> mapRdd = PlayersRdd.mapToPair(new PairFunction<PlayersModel, String, String>() {
            public Tuple2<String, String> call(PlayersModel playersModel) throws Exception {
                return new Tuple2<String, String>(playersModel.getPlayerName(), playersModel.getMatchID());
            }
        });
/*
        mapRdd.foreach(new VoidFunction<Tuple2<String, String>>() {
            public void call(Tuple2<String, String> tline) throws Exception {
                System.out.println(tline._1+" "+tline._2);
            }
        });
        
        */

        JavaPairRDD<String, Iterable<String>> GRdd = mapRdd.groupByKey();
/*


        GRdd.foreach(new VoidFunction<Tuple2<String, Iterable<String>>>() {
            public void call(Tuple2<String, Iterable<String>> Stline) throws Exception {
                System.out.println(Stline._1+" "+Stline._2);

            }
        });
*/
// groupPlayer adında bir sınıf daha yarattık

        JavaRDD<groupPlayer> resultrdd = GRdd.map(new Function<Tuple2<String, Iterable<String>>, groupPlayer>() {
            public groupPlayer call(Tuple2<String, Iterable<String>> dizi) throws Exception {
                Iterator<String> iteratorraw = dizi._2().iterator();

                int size = Iterators.size(iteratorraw);
                return new groupPlayer(dizi._1, size);

            }
        });
/*
        resultrdd.foreach(new VoidFunction<groupPlayer>() {
            public void call(groupPlayer groupPlayer) throws Exception {
                System.out.println("groupPlayer = " + groupPlayer.getPlayerName()+" Kaçkere oynadı "+groupPlayer.getMatchCount());
            }
        });
        */

/*
{
PlayerName: KAmil,
MAtchCout:6
}
 */
        JavaRDD<Document> MongoRDD = resultrdd.map(new Function<groupPlayer, Document>() {
            public Document call(groupPlayer groupPlayer) throws Exception {
                return Document.parse("{PlayerName: " + " ' " + groupPlayer.getPlayerName() + "'"
                        + ","+"PlayerMatchCount: "+ groupPlayer.getMatchCount()
                        +"}");
            }
        });

        MongoSpark.save(MongoRDD);

    }
}
