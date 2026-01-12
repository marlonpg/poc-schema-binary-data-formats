# Binary Serialization Formats Comparison

This project demonstrates the differences between Protocol Buffers (Protobuf) and FlatBuffers using identical data structures and operations.

## Project Structure

```
src/main/
├── java/com/example/
│   ├── ProtobufExample.java      # Protobuf implementation
│   ├── FlatBuffersExample.java   # FlatBuffers implementation
│   ├── PerformanceComparison.java # Runs both examples
│   ├── protobuf/
│   │   └── UserProtos.java       # Simplified Protobuf classes
│   └── flatbuffers/
│       ├── User.java             # FlatBuffers User class
│       └── UserList.java         # FlatBuffers UserList class
├── proto/
│   └── user.proto               # Protobuf schema
└── flatbuffers/
    └── user.fbs                 # FlatBuffers schema
```

## Data Model

Both examples use identical User data:
- ID: integer
- Name: string  
- Email: string
- Age: integer
- Skills: array of strings

## Running the Examples

### Bash Scripts (Linux/macOS)
```bash
# Run complete comparison
./run-comparison.sh

# Run individual examples
./run-protobuf.sh
./run-flatbuffers.sh
```

### Maven Commands
```bash
# Compile project
./mvnw clean compile

# Run examples
./mvnw exec:java -Dexec.mainClass="com.example.PerformanceComparison"
./mvnw exec:java -Dexec.mainClass="com.example.ProtobufExample"
./mvnw exec:java -Dexec.mainClass="com.example.FlatBuffersExample"
```

### Manual Commands
```bash
# Compile all Java files
javac -cp "." src/main/java/com/example/*.java src/main/java/com/example/*/*.java

# Run comparison
java -cp "src/main/java" com.example.PerformanceComparison
```

## Key Differences Demonstrated

### Protobuf Characteristics:
- **Serialization**: Creates object tree, then serializes
- **Deserialization**: Full parsing required
- **Memory**: Higher overhead during processing
- **Size**: Compact binary format
- **API**: Clean, type-safe builder pattern

### FlatBuffers Characteristics:
- **Serialization**: Direct buffer writing
- **Deserialization**: Zero-copy, instant access
- **Memory**: Lower memory footprint
- **Size**: Larger due to alignment padding
- **API**: Buffer-centric, more verbose

## Expected Performance Results

| Metric | Protobuf | FlatBuffers |
|--------|----------|-------------|
| Binary Size | Smaller | Larger |
| Serialization | Moderate | Faster |
| Deserialization | Slower | Much Faster |
| Memory Usage | Higher | Lower |
| Random Access | No | Yes |

## Use Case Recommendations

**Choose Protobuf for:**
- Network APIs and microservices
- Data storage and persistence
- Cross-language compatibility
- Schema evolution requirements

**Choose FlatBuffers for:**
- Real-time applications
- Gaming and embedded systems
- Memory-constrained environments
- Zero-copy performance needs