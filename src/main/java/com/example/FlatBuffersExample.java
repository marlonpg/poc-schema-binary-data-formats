package com.example;

import com.example.flatbuffers.User;
import com.example.flatbuffers.UserList;
import com.google.flatbuffers.FlatBufferBuilder;
import java.nio.ByteBuffer;

public class FlatBuffersExample {
    
    public static void main(String[] args) {
        System.out.println("=== FlatBuffers Example ===");
        
        // Serialization benchmark
        long startTime = System.nanoTime();
        
        FlatBufferBuilder builder = new FlatBufferBuilder(1024);
        
        // Create user 1
        int name1 = builder.createString("John Doe");
        int email1 = builder.createString("john@example.com");
        int[] skills1 = {
            builder.createString("Java"),
            builder.createString("Python"),
            builder.createString("Go")
        };
        int skillsVector1 = User.createSkillsVector(builder, skills1);
        int user1 = User.createUser(builder, 1, name1, email1, 30, skillsVector1);
        
        // Create user 2
        int name2 = builder.createString("Jane Smith");
        int email2 = builder.createString("jane@example.com");
        int[] skills2 = {
            builder.createString("JavaScript"),
            builder.createString("React"),
            builder.createString("Node.js")
        };
        int skillsVector2 = User.createSkillsVector(builder, skills2);
        int user2 = User.createUser(builder, 2, name2, email2, 28, skillsVector2);
        
        // Create user list
        int[] users = {user1, user2};
        int usersVector = UserList.createUsersVector(builder, users);
        int userList = UserList.createUserList(builder, usersVector);
        
        builder.finish(userList);
        
        ByteBuffer buffer = builder.dataBuffer();
        byte[] serializedData = new byte[buffer.remaining()];
        buffer.get(serializedData);
        
        long serializationTime = System.nanoTime() - startTime;
        
        System.out.println("Serialized 2 users");
        System.out.println("Binary size: " + serializedData.length + " bytes");
        System.out.println("Serialization time: " + serializationTime / 1000 + " microseconds");
        
        // Deserialization benchmark (zero-copy)
        startTime = System.nanoTime();
        ByteBuffer readBuffer = ByteBuffer.wrap(serializedData);
        UserList deserializedList = UserList.getRootAs(readBuffer);
        long deserializationTime = System.nanoTime() - startTime;
        
        System.out.println("Deserialization time: " + deserializationTime / 1000 + " microseconds");
        
        // Access data
        startTime = System.nanoTime();
        for (int i = 0; i < deserializedList.usersLength(); i++) {
            User user = deserializedList.users(i);
            StringBuilder skills = new StringBuilder("[");
            for (int j = 0; j < user.skillsLength(); j++) {
                if (j > 0) skills.append(", ");
                skills.append(user.skills(j));
            }
            skills.append("]");
            
            String info = "ID: " + user.id() + 
                         ", Name: " + user.name() + 
                         ", Email: " + user.email() + 
                         ", Age: " + user.age() + 
                         ", Skills: " + skills.toString();
            System.out.println(info);
        }
        long accessTime = System.nanoTime() - startTime;
        System.out.println("Data access time: " + accessTime / 1000 + " microseconds");
    }
}