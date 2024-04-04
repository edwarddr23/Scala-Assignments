Scala project created for Big Data Programming course (Spring 2024).
_____________________________________________________________________________
Data Information:
Original data found from here: https://archive.ics.uci.edu/dataset/10/automobile
Information on the data's columns is in file: imports-85.names
The last line of the output here shows the predicted price of a car with peak-rm: 5000.0, city-mpg: 21.0, and highway-mpg: 27.0. 
_____________________________________________________________________________
How to run each python file:
1. For simplicity, create a dataproc Linux VM environment (Google cloud has tools available).
2. Upload these files into the VM's root: input.txt, a14.scala
3. Type the following commands into the terminal:
   hdfs dfs -put input.txt input
   spark-shell -i [scala file here]
5. Wait for the script to fully run until it successfully writes to output/output.txt.
6. Type the following command:
   hdfs dfs -getmerge output/* [name of output file].txt
8. This output text file will hold the output attached to this repository.
