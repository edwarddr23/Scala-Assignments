Scala project created for Big Data Programming course (Spring 2024).
_____________________________________________________________________________
Data Information:
Original data found from here https://archive.ics.uci.edu/dataset/15/breast+cancer+wisconsin+original
Information on the data's columns is in file: wdbc.names
Data is on breast cancer diagnostics. The features for the output column (diagnosis) are "radius", "texture", "perimeter", and "area". The prediction is a double of value 0.0 or 1.0. The LogisticRegression library was used to make a predicted diagnosis (M or B, or 0.0 or 1.0). 
_____________________________________________________________________________
How to run the scala file:
1. For simplicity, create a dataproc Linux VM environment (Google cloud has tools available).
2. Upload these files into the VM's root: input.txt, a14.scala
3. Type the following commands into the terminal:
   hdfs dfs -put input.txt input
   spark-shell -i [scala file here]
5. Wait for the script to fully run until it successfully writes to output/output.txt.
6. Type the following command:
   hdfs dfs -getmerge output/* [name of output file].txt
8. This output text file will hold the output attached to this repository.
