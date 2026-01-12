// Auto-generated FlatBuffers classes (simplified for demo)
package com.example.flatbuffers;

import com.google.flatbuffers.*;
import java.nio.ByteBuffer;

public final class User extends Table {
    public static User getRootAs(ByteBuffer _bb) { return getRootAs(_bb, new User()); }
    public static User getRootAs(ByteBuffer _bb, User obj) { return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
    
    public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
    public User __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }
    
    public int id() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
    public String name() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
    public String email() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
    public int age() { int o = __offset(10); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
    public String skills(int j) { int o = __offset(12); return o != 0 ? __string(__vector(o) + j * 4) : null; }
    public int skillsLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
    
    public static int createUser(FlatBufferBuilder builder, int id, int nameOffset, int emailOffset, int age, int skillsOffset) {
        builder.startTable(5);
        User.addSkills(builder, skillsOffset);
        User.addAge(builder, age);
        User.addEmail(builder, emailOffset);
        User.addName(builder, nameOffset);
        User.addId(builder, id);
        return User.endUser(builder);
    }
    
    public static void startUser(FlatBufferBuilder builder) { builder.startTable(5); }
    public static void addId(FlatBufferBuilder builder, int id) { builder.addInt(0, id, 0); }
    public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(1, nameOffset, 0); }
    public static void addEmail(FlatBufferBuilder builder, int emailOffset) { builder.addOffset(2, emailOffset, 0); }
    public static void addAge(FlatBufferBuilder builder, int age) { builder.addInt(3, age, 0); }
    public static void addSkills(FlatBufferBuilder builder, int skillsOffset) { builder.addOffset(4, skillsOffset, 0); }
    public static int createSkillsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
    public static void startSkillsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
    public static int endUser(FlatBufferBuilder builder) { return builder.endTable(); }
}