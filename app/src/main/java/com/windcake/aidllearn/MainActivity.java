package com.windcake.aidllearn;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private final static String TAG = "MainActivity";
    private IMyAidlInterface mMyAidlInterface;
    private Button mBindButton;
    private Button mRemoteCallButton;
    private Button mAddBookButton;
    private Button mGetListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBindButton = (Button) findViewById(R.id.activity_main_bind);
        mBindButton.setOnClickListener(this);

        mRemoteCallButton = (Button) findViewById(R.id.activity_main_remotecall);
        mRemoteCallButton.setOnClickListener(this);

        mAddBookButton = (Button) findViewById(R.id.activity_main_addbook);
        mAddBookButton.setOnClickListener(this);

        mGetListButton = (Button) findViewById(R.id.activity_main_getlist);
        mGetListButton.setOnClickListener(this);

    }


    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            mMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            Log.i(TAG,"connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
//            mMyAidlInterface = null;
            Log.i(TAG,"disconnect");
        }
    };


    private void bindRemoteService()
    {
        Intent intent = new Intent(MainActivity.this,RemoteService.class);
        intent.setAction(RemoteService.class.getName());
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    private void add(int x,int y)
    {
        int sum = 0;
        try
        {
            if (mMyAidlInterface != null)
            {
                sum = mMyAidlInterface.add(x, y);
                Log.i(TAG, "客户端调用服务端计算结果：" + sum);
            }
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.activity_main_bind:
                bindRemoteService();
                break;
            case R.id.activity_main_remotecall:
                add(1,1);
                break;

            case R.id.activity_main_addbook:
                try
                {
                    mMyAidlInterface.addBook(new Book("易经"));
                } catch (RemoteException e)
                {
                    e.printStackTrace();
                }
                break;

            case R.id.activity_main_getlist:
                try
                {
                    List<Book> bookList = mMyAidlInterface.getBookList();
                    Log.i(TAG,bookList.toString());
                } catch (RemoteException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }
}
