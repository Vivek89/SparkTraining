package com.jpmorgan.vivek;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class TopFlights {

	private static final Function<String, String[]> WORDS_EXTRACTOR = new Function<String, String[]>() {
		private static final long serialVersionUID = -8713589421213970322L;

		@Override
		public String[] call(String s) throws Exception {
			return s.split(",");
		}
	};

	private static final PairFunction<String[], String, Integer> CARRIER_MAPPER = new PairFunction<String[], String, Integer>() {
		private static final long serialVersionUID = -653315781431311814L;

		@Override
		public Tuple2<String, Integer> call(String[] s) throws Exception {
			return new Tuple2<String, Integer>(s[5], 1);
		}
	};

	private static final Function2<Integer, Integer, Integer> CARRIER_REDUCER = new Function2<Integer, Integer, Integer>() {
		private static final long serialVersionUID = 6212036970371719545L;

		@Override
		public Integer call(Integer a, Integer b) throws Exception {
			return a + b;
		}
	};

	private static final PairFunction<Tuple2<String, Integer>, Integer, String> REVERSE_KEYS = new PairFunction<Tuple2<String, Integer>, Integer, String>() {
		private static final long serialVersionUID = -8713589481813970322L;

		@Override
		public Tuple2<Integer, String> call(Tuple2<String, Integer> carrier) throws Exception {
			return new Tuple2<Integer, String>(carrier._2, carrier._1);
		}
	};

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Please provide the input file full path as argument");
			System.exit(0);
		}
		SparkConf sc = new SparkConf().setAppName("Vivek-Top-Flights").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sc);

		JavaRDD<String> file = jsc.textFile(args[0]);
		JavaRDD<String[]> words = file.map(WORDS_EXTRACTOR);
		JavaPairRDD<String, Integer> pairs = words.mapToPair(CARRIER_MAPPER);
		JavaPairRDD<String, Integer> counter = pairs.reduceByKey(CARRIER_REDUCER);
//		JavaPairRDD<Integer, String> reveseKeys = counter.mapTopair(REVERSE_KEYS);

		counter.saveAsTextFile(args[1]);
	}

}
