import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.rdd._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._

// Write decision tree in Scala to predict price of a given car with the following feature vector -

// 23. peak-rpm:                 continuous from 4150 to 6600.
// 24. city-mpg:                 continuous from 13 to 49.
// 25. highway-mpg:              continuous from 16 to 54.

def parseElement(element: String) : Int = {
    if(element == "?") 0
    else element.toInt
}

def createLabeledPoints(fields: Array[String]): LabeledPoint = {
    val peakrpm = parseElement(fields(22))
    val citympg = parseElement(fields(23))
    val highwaympg = parseElement(fields(24))
    val price = parseElement(fields(25))

    LabeledPoint(price, Vectors.dense(Array(peakrpm.toDouble, citympg.toDouble, highwaympg.toDouble)))
}

val text = sc.textFile("input/input.txt")
val trainingData = text.map(x => x.split(",")).map(createLabeledPoints)

trainingData.collect().foreach(println)

val categoricalFeaturesInfo = Map[Int, Int]()
val model = DecisionTree.trainClassifier(trainingData, 45401, categoricalFeaturesInfo, "gini", 5, 100)

val testData = Vectors.dense(Array(5000.0,21.0,27.0))
val prediction = model.predict(testData)
println(prediction)