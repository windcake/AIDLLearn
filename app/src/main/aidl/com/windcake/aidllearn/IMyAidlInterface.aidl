// IMyAidlInterface.aidl
package com.windcake.aidllearn;

// Declare any non-default types here with import statements

import com.windcake.aidllearn.Book;

interface IMyAidlInterface {

//  aidl文件定义方法
    int add(int i1,int i2);

    void addBook(in Book book);
    List<Book> getBookList();

}
