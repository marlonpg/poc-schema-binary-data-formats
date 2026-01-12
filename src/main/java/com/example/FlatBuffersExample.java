package com.example;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class FlatBuffersExample {
    
    public static void main(String[] args) {
        System.out.println("=== FlatBuffers Example ===");
        
        // Serialization benchmark
        long startTime = System.nanoTime();
        
        // Create sample data (simplified FlatBuffers-style)
        SimpleFlatBuffer buffer = new SimpleFlatBuffer();
        
        // Add users
        buffer.addUser(1, "John Doe", "john@example.com", 30, new String[]{"Java", "Python", "Go"});
        buffer.addUser(2, "Jane Smith", "jane@example.com", 28, new String[]{"JavaScript", "React", "Node.js"});
        
        byte[] serializedData = buffer.serialize();
        long serializationTime = System.nanoTime() - startTime;
        
        System.out.println("Serialized 2 users");
        System.out.println("Binary size: " + serializedData.length + " bytes");
        System.out.println("Serialization time: " + serializationTime / 1000 + " microseconds");
        
        // Deserialization benchmark (zero-copy simulation)
        startTime = System.nanoTime();
        SimpleFlatBuffer deserializedBuffer = SimpleFlatBuffer.deserialize(serializedData);
        long deserializationTime = System.nanoTime() - startTime;
        
        System.out.println("Deserialization time: " + deserializationTime / 1000 + " microseconds");
        
        // Access data
        startTime = System.nanoTime();
        List<SimpleUser> users = deserializedBuffer.getUsers();
        for (SimpleUser user : users) {
            String info = "ID: " + user.id + 
                         ", Name: " + user.name + 
                         ", Email: " + user.email + 
                         ", Age: " + user.age + 
                         ", Skills: " + java.util.Arrays.toString(user.skills);
            System.out.println(info);
        }
        long accessTime = System.nanoTime() - startTime;
        System.out.println("Data access time: " + accessTime / 1000 + " microseconds");
    }
    
    // Simplified FlatBuffers-like implementation
    static class SimpleFlatBuffer {
        private List<SimpleUser> users = new ArrayList<>();
        
        void addUser(int id, String name, String email, int age, String[] skills) {
            users.add(new SimpleUser(id, name, email, age, skills));
        }
        
        byte[] serialize() {
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            
            // Write user count
            buffer.putInt(users.size());
            
            // Write users with padding (FlatBuffers style)
            for (SimpleUser user : users) {
                buffer.putInt(user.id);
                
                // Align to 4-byte boundary
                while (buffer.position() % 4 != 0) buffer.put((byte)0);
                
                writeString(buffer, user.name);
                writeString(buffer, user.email);
                buffer.putInt(user.age);
                
                // Write skills array
                buffer.putInt(user.skills.length);
                for (String skill : user.skills) {
                    writeString(buffer, skill);
                }
            }
            
            byte[] result = new byte[buffer.position()];
            buffer.rewind();
            buffer.get(result);
            return result;
        }
        
        static SimpleFlatBuffer deserialize(byte[] data) {
            SimpleFlatBuffer buffer = new SimpleFlatBuffer();
            ByteBuffer bb = ByteBuffer.wrap(data);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            
            int userCount = bb.getInt();
            
            for (int i = 0; i < userCount; i++) {
                int id = bb.getInt();
                
                // Skip padding
                while (bb.position() % 4 != 0) bb.get();
                
                String name = readString(bb);
                String email = readString(bb);
                int age = bb.getInt();
                
                int skillsCount = bb.getInt();
                String[] skills = new String[skillsCount];
                for (int j = 0; j < skillsCount; j++) {
                    skills[j] = readString(bb);
                }
                
                buffer.addUser(id, name, email, age, skills);
            }
            
            return buffer;
        }
        
        List<SimpleUser> getUsers() {
            return users;
        }
        
        private static void writeString(ByteBuffer buffer, String str) {
            byte[] bytes = str.getBytes();
            buffer.putInt(bytes.length);
            buffer.put(bytes);
        }
        
        private static String readString(ByteBuffer buffer) {
            int length = buffer.getInt();
            byte[] bytes = new byte[length];
            buffer.get(bytes);
            return new String(bytes);
        }
    }
    
    static class SimpleUser {
        int id;
        String name;
        String email;
        int age;
        String[] skills;
        
        SimpleUser(int id, String name, String email, int age, String[] skills) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
            this.skills = skills;
        }
    }
}