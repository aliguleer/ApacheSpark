package rddfirst;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class rddArray {
    public static void main(String[] args) {

        JavaSparkContext cnt = new JavaSparkContext("local","secondExam");
        List<String> examp = Arrays.asList("Ali g.","Kamil S.","bla bla");
        JavaRDD<String> firstrp=cnt.parallelize(examp);

        System.out.println("first value :"+firstrp.first());



    }
}
