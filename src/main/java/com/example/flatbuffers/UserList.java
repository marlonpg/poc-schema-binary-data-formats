package com.example.flatbuffers;

import com.google.flatbuffers.*;
import java.nio.ByteBuffer;

public final class UserList extends Table {
    public static UserList getRootAs(ByteBuffer _bb) { return getRootAs(_bb, new UserList()); }
    public static UserList getRootAs(ByteBuffer _bb, UserList obj) { return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
    
    public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
    public UserList __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }
    
    public User users(int j) { return users(new User(), j); }
    public User users(User obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
    public int usersLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
    
    public static int createUserList(FlatBufferBuilder builder, int usersOffset) {
        builder.startTable(1);
        UserList.addUsers(builder, usersOffset);
        return UserList.endUserList(builder);
    }
    
    public static void startUserList(FlatBufferBuilder builder) { builder.startTable(1); }
    public static void addUsers(FlatBufferBuilder builder, int usersOffset) { builder.addOffset(0, usersOffset, 0); }
    public static int createUsersVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
    public static void startUsersVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
    public static int endUserList(FlatBufferBuilder builder) { return builder.endTable(); }
}