package com.example;

import com.example.protobuf.UserProtos;
import java.util.Arrays;

public class ProtobufExample {
    
    public static void main(String[] args) {
        System.out.println("=== Protobuf Example ===");
        
        // Create sample data
        UserProtos.User user1 = UserProtos.User.newBuilder()
            .setId(1)
            .setName("John Doe")
            .setEmail("john@example.com")
            .setAge(30)
            .addSkills("Java")
            .addSkills("Python")
            .addSkills("Go")
            .build();
            
        UserProtos.User user2 = UserProtos.User.newBuilder()
            .setId(2)
            .setName("Jane Smith")
            .setEmail("jane@example.com")
            .setAge(28)
            .addSkills("JavaScript")
            .addSkills("React")
            .addSkills("Node.js")
            .build();
            
        UserProtos.UserList userList = UserProtos.UserList.newBuilder()
            .addUsers(user1)
            .addUsers(user2)
            .build();
        
        // Serialization benchmark
        long startTime = System.nanoTime();
        byte[] serializedData = userList.toByteArray();
        long serializationTime = System.nanoTime() - startTime;
        
        System.out.println("Serialized " + userList.getUsersCount() + " users");
        System.out.println("Binary size: " + serializedData.length + " bytes");
        System.out.println("Serialization time: " + serializationTime / 1000 + " microseconds");
        
        // Deserialization benchmark
        startTime = System.nanoTime();
        try {
            UserProtos.UserList deserializedList = UserProtos.UserList.parseFrom(serializedData);
            long deserializationTime = System.nanoTime() - startTime;
            
            System.out.println("Deserialization time: " + deserializationTime / 1000 + " microseconds");
            
            // Access data
            startTime = System.nanoTime();
            for (UserProtos.User user : deserializedList.getUsersList()) {
                String info = "ID: " + user.getId() + 
                             ", Name: " + user.getName() + 
                             ", Email: " + user.getEmail() + 
                             ", Age: " + user.getAge() + 
                             ", Skills: " + user.getSkillsList();
                System.out.println(info);
            }
            long accessTime = System.nanoTime() - startTime;
            System.out.println("Data access time: " + accessTime / 1000 + " microseconds");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}