package com.izicap.testchatgptapi.DB;

public interface IDBWriter {
    void connect();
    void put(String qst, String ans);
    void close();
}
