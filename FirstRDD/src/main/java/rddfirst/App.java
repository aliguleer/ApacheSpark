package rddfirst;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class App {
    public static void main(String[] args) {
        JavaSparkContext cont = new JavaSparkContext("local","First Exam");
        JavaRDD<String> wordrdd = cont.textFile("c:\\dosya\\ilkdataset.txt");
        System.out.println("first value :"+ wordrdd.first());
        System.out.println("first value :"+ wordrdd.count());

    }
}
