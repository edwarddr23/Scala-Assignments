import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.Row

val spark = SparkSession.builder().appName("Logistic Regression Example").getOrCreate()

val df = spark.read.format("csv")
    .option("header", "false")
    .option("inferSchema", "true")
    .load("input/input.txt")

df.show();

val schema = Seq("ID number", "Diagnosis", "radius", "texture", "perimeter", "area", "smoothness", "compactness", "concavity", "concave points", "symmetry", "fractal dimension", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth", "smth")

val df_with_headers = df.toDF(schema: _*)

df_with_headers.show()

val assembler = new VectorAssembler()
    .setInputCols(Array("radius", "texture", "perimeter", "area"))
    .setOutputCol("features")

val parseDiagnosis = udf((value: String) => if (value == "M") 0.00d else 1.00d)

val df3 = assembler.transform(df_with_headers)
    .withColumn("diagnosis", parseDiagnosis($"Diagnosis"))
    .select("diagnosis", "features")

df3.show()

val lr = new LogisticRegression()
  .setMaxIter(10)
  .setRegParam(0.3)
  .setElasticNetParam(0.8)
  .setLabelCol("diagnosis")

val lrModel = lr.fit(df3)

val testData = Seq((16.99,10.31,121.8,1001)).toDF("radius", "texture", "perimeter", "area")
val testAssembler = new VectorAssembler()
    .setInputCols(Array("radius", "texture", "perimeter", "area"))
    .setOutputCol("features")

val testdataWithFeatures = testAssembler.transform(testData) 

val predictions = lrModel.transform(testdataWithFeatures)
predictions.select("features", "prediction").show()

spark.stop()