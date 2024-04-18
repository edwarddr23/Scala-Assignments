Scala project created for Big Data Programming course (Spring 2024).
_____________________________________________________________________________
Data Information:
Original data found from here: https://archive.ics.uci.edu/dataset/40/flags
Information on the data's columns is in file: flag.names
The output file here is what results from running these scripts.
_____________________________________________________________________________
How to run the scala file:
1. For simplicity, create a dataproc Linux VM environment (Google cloud has tools available).
2. Upload the input and scala files into the VM's root.
3. Type the following commands into the terminal:
   hdfs dfs -put input.txt input
   spark-shell -i [scala file here]
5. Wait for the script to fully run until it successfully writes to output/output.txt.
6. Type the following command:
   hdfs dfs -getmerge output/* [name of output file].txt
8. This output text file will hold the output attached to this repository.
