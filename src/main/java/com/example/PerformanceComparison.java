package com.example;

public class PerformanceComparison {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Binary Serialization Format Comparison");
        System.out.println("========================================\n");
        
        // Run Protobuf example
        ProtobufExample.main(args);
        
        System.out.println();
        
        // Run FlatBuffers example
        FlatBuffersExample.main(args);
        
        System.out.println();
        System.out.println("========================================");
        System.out.println("Key Observations:");
        System.out.println("- Protobuf: Smaller binary size, full parsing");
        System.out.println("- FlatBuffers: Larger binary, zero-copy access");
        System.out.println("- FlatBuffers typically faster deserialization");
        System.out.println("- Protobuf better for network transmission");
        System.out.println("========================================");
    }
}