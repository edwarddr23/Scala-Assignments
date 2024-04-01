val textfile = sc.textFile("input/input.txt")
val token = textfile.map(x => x.split(","))
val keyval = token.map(x => (x(1), (x(0), x(4).toInt)))
val max_pop = keyval.reduceByKey{ case((x1, x2), (y1, y2)) => if(x2 > y2) {(x1, x2)} else {(y1, y2)} } 

max_pop.collect.foreach(println)

max_pop.saveAsTextFile("output")