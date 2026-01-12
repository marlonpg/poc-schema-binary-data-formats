#!/bin/bash

echo "========================================"
echo "Binary Serialization Format Comparison"
echo "========================================"

# Compile project
echo -e "\nCompiling project..."
./mvnw clean compile

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    
    # Run comparison
    echo -e "\nRunning performance comparison..."
    ./mvnw exec:java -Dexec.mainClass="com.example.PerformanceComparison"
    
    echo -e "\n========================================"
    echo "Individual Examples Available:"
    echo "- ./run-protobuf.sh"
    echo "- ./run-flatbuffers.sh"
    echo "========================================"
else
    echo "Compilation failed!"
fi