package com.windcake.aidllearn;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service
{
    private static String TAG = "RemoteService";
    public RemoteService()
    {
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent)
    {

        return binder;
//        return null;
    }

//远程服务实现方法
  private List<Book> mBookList = new ArrayList<>();
  IBinder binder = new IMyAidlInterface.Stub()
  {
      @Override
      public int add(int i1, int i2) throws RemoteException
      {
          Log.i(TAG,"RemoteService收到加法请求");
          return i1 + i2;
      }

      @Override
      public void addBook(Book book) throws RemoteException
      {
          mBookList.add(book);
          Log.i(TAG,"RemoteService加了一本书");
      }

      @Override
      public List<Book> getBookList() throws RemoteException
      {
          Log.i(TAG,"RemoteService返回BookList");
          return mBookList;
      }
  };

}
