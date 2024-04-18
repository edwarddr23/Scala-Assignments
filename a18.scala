import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.Row
import org.apache.spark.ml.feature.StringIndexer

val spark = SparkSession.builder().appName("LogisticRegression Example").getOrCreate()

val df = spark.read.format("csv")
    // By setting header to false, the column names will have defaults _c1, _c2, etc.
    .option("header", "false")
    // inferSchema as true makes it so that the column types are interpreted and handled by scala.
    .option("inferSchema", "true")
    .load("input/input.txt")
    .select("_c1", "_c2", "_c3", "_c4", "_c5")
    .toDF("diag", "radius", "texture", "perimeter", "area")

val featureCols = Array("radius", "texture", "perimeter", "area")
val assembler = new VectorAssembler()
    .setInputCols(featureCols)
    .setOutputCol("features")

val assembledData = assembler.transform(df)
val indexer = new StringIndexer()
    .setInputCol("diag")
    .setOutputCol("label")

val trainingData = indexer.fit(assembledData).transform(assembledData)

val lsvc = new LinearSVC()
    .setMaxIter(10)
    .setRegParam(0.1)

val lsvcModel = lsvc.fit(trainingData)

val testFeatureVector = (16.99,10.31,121.8,1001)
val testData = Seq(testFeatureVector).toDF("radius", "texture", "perimeter", "area")

val testDataWithFeatures = assembler.transform(testData)

val predictions = lsvcModel.transform(testDataWithFeatures)

predictions.select("features", "prediction").show()

spark.stop()