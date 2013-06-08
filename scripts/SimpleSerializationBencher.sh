#!/bin/bash
sizes=(1 2 3 4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 100 200 300 400 500 1000 5000 10000)
echo "Size","Millis","Nanos"
for i in "${sizes[@]}"
do
	java -cp "../target/classes;../target/test-classes" com.enragedginger.stephenerialization.benching.SimpleSerializationBenchmarker $i
done
