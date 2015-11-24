package com.jskz.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jskz.network.model.PhoneBean;
import com.jskz.network.util.Logger;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final int kURLCONNECTION_GET_RESPONSE = 0x1; 
	
	private Button mTestBtn1;
	private Button mTestBtn2;
	private Button mTestBtn3;
	private Button mTestBtn4;
	private Button mTestBtn5;
	private Button mTestBtn6;
	private TextView mShowTV;
	
	private String Baiduurl = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber";
	private String Phone = "phone=18710057085";
	
	private final static String ALBUM_PATH = Environment.getExternalStorageDirectory().toString() + "/phone.txt/";

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case kURLCONNECTION_GET_RESPONSE:
		         mShowTV.setText((CharSequence) msg.obj);
		         try{
		             File file = new File(ALBUM_PATH);  
		             if (!file.exists())
		 			{
		 				File dir = new File(file.getParent());
		 				dir.mkdirs();
		 				//file.createNewFile();
		 			}
		             FileOutputStream outStream = new FileOutputStream(file);  
		             outStream.write(msg.obj.toString().getBytes("UTF-8"));  
		             outStream.close();
		         } catch (Exception e) {  
		             e.printStackTrace();  
		         }  
				break;
			default:
				break;
			}
		};
	};
	

	//==============��ʼ��=============//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }


	private void initView() {
		
		this.mTestBtn1 = (Button) this.findViewById(R.id.aci_test_btn1);
		this.mTestBtn2 = (Button) this.findViewById(R.id.aci_test_btn2);
		this.mTestBtn3 = (Button) this.findViewById(R.id.aci_test_btn3);
		this.mTestBtn4 = (Button) this.findViewById(R.id.aci_test_btn4);
		this.mTestBtn5 = (Button) this.findViewById(R.id.aci_test_btn5);
		this.mTestBtn6 = (Button) this.findViewById(R.id.aci_test_btn6);
		this.mShowTV = (TextView) this.findViewById(R.id.aci_show_tv);

	}
	
	private void initListener() {
		
		mTestBtn1.setOnClickListener(this);
		mTestBtn2.setOnClickListener(this);
		mTestBtn3.setOnClickListener(this);
		mTestBtn4.setOnClickListener(this);
		mTestBtn5.setOnClickListener(this);
		mTestBtn6.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.aci_test_btn1:
			new Thread(new Runnable() {
				@Override
				public void run() {
					httpurlConnectionget();
				}
			}).start();
			break;
		case R.id.aci_test_btn2:
			new Thread(new Runnable() {
				@Override
				public void run() {				
					httpurlConnectionpost();
				} 
			}).start();
			break;
		case R.id.aci_test_btn3:
			new Thread(new Runnable() {
				@Override
				public void run() {				
					httpClientget();
				} 
			}).start();
			break;
		case R.id.aci_test_btn4:
			new Thread(new Runnable() {
				@Override
				public void run() {				
					httpClientpost();
				} 
			}).start();
			break;
		case R.id.aci_test_btn5:
			new Thread(new Runnable() {
				@Override
				public void run() {				
					okHttpget();
				} 
			}).start();
			break;
		case R.id.aci_test_btn6:
			new Thread(new Runnable() {
				@Override
				public void run() {
					okHttppost();
				} 
			}).start();
			break;
		}
	}


		private void httpurlConnectionget() {
		     BufferedReader reader = null;
		     String result = null;
		     StringBuffer sbf = new StringBuffer();
		     Baiduurl = Baiduurl + "?" + Phone;

		     try {
		         URL url = new URL(Baiduurl);
		         // TODO
		         HttpURLConnection connection = (HttpURLConnection) url
		                 .openConnection();
		         connection.setRequestMethod("GET");
		         connection.setRequestProperty("apikey","6c36e1ebba98b1c157d34cfe81c5ef3e");
		         connection.connect();
		         // TODO
		         InputStream is = connection.getInputStream();
		         reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		         String strRead = null;
		         while ((strRead = reader.readLine()) != null) {
		             sbf.append(strRead);
		             sbf.append("\r\n");
		         }
		         reader.close();
		         result = sbf.toString();
		         Log.e("PhoneBean", result);
		         PhoneBean phoneBean = JSON.parseObject(result, PhoneBean.class);
		         Log.e("PhoneBean", phoneBean.toString());
		         
        	     Message msg = Message.obtain();
		         msg.what = kURLCONNECTION_GET_RESPONSE;
		         msg.obj = phoneBean.toString();
		         mHandler.sendMessage(msg);
		         // TODO
		         Logger.e(result);
		     } catch (IOException e) {
		         e.printStackTrace();
		     }			    
		 }
	

		
		private void httpurlConnectionpost() {
		     BufferedReader reader = null;
		     String result = null;
		     StringBuffer sbf = new StringBuffer();

		     try {
		         URL url = new URL(Baiduurl);
		         HttpURLConnection connection = (HttpURLConnection) url
		                 .openConnection();
		         connection.setRequestMethod("POST");
		         connection.setUseCaches(false);
		         connection.setRequestProperty("apikey","6c36e1ebba98b1c157d34cfe81c5ef3e");
		         connection.connect();
		         // TODO
		         OutputStream os = connection.getOutputStream();
		         DataOutputStream dos = new DataOutputStream(os);
		         dos.writeBytes(Phone);
		         dos.close();
		         InputStream is = connection.getInputStream();
		         BufferedReader br = new BufferedReader(new InputStreamReader(is));
		         String line = null;
		         StringBuffer sb = new StringBuffer();
		         while((line = br.readLine()) != null) {
		        	 sb.append(line);
		         }
		         result = sb.toString();
		         
		         Message msg = Message.obtain();
		         msg.what = kURLCONNECTION_GET_RESPONSE;
		         msg.obj = result;
		         mHandler.sendMessage(msg);
		     } catch(IOException e) {
		    	 e.printStackTrace();
		     }
		     
		 }


		
		private void httpClientget() {
			Baiduurl = Baiduurl + "?" + Phone;
			String result;
			// TODO
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpGet httpRequest = new HttpGet(Baiduurl);
	        httpRequest.addHeader("apikey","6c36e1ebba98b1c157d34cfe81c5ef3e");
	        HttpResponse httpResponse;
	        try {
	            httpResponse = httpclient.execute(httpRequest);
	            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	                result = EntityUtils.toString(httpResponse.getEntity());
	            }else{
	                result= "请求失败！";
	            }
	            
	            Message msg = Message.obtain();
	            msg.what = kURLCONNECTION_GET_RESPONSE;
	            msg.obj = result;
	            mHandler.sendMessage(msg);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
		
		private void httpClientpost() {
			String result;
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httpRequest = new HttpPost(Baiduurl);
	        httpRequest.addHeader("apikey", "6c36e1ebba98b1c157d34cfe81c5ef3e");
	        List params=new ArrayList();
	        params.add(new BasicNameValuePair("Phone",null));
	        try {
	            httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
	            HttpResponse httpResponse = httpclient.execute(httpRequest);
	            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	                result = EntityUtils.toString(httpResponse.getEntity());
	            }else{
	                result = "请求失败！";
	            }
	            
	            Message msg = Message.obtain();
		        msg.what = kURLCONNECTION_GET_RESPONSE;
		        msg.obj = result;
		        mHandler.sendMessage(msg);
		        
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		private void okHttpget() {
			OkHttpClient mOkHttpClient = new OkHttpClient();
			Baiduurl = Baiduurl + "?" + Phone;
			Request request = new Request.Builder()
			                .url(Baiduurl)
			                .addHeader("apikey","6c36e1ebba98b1c157d34cfe81c5ef3e")
			                .build();
			Call call = mOkHttpClient.newCall(request); 
			call.enqueue(new Callback() {
				@Override
				public void onResponse(Response response) throws IOException {
					String result = response.body().string().toString();
					Log.e("PhoneBean", result);
					PhoneBean phoneBean = JSON.parseObject(result, PhoneBean.class);
					Log.e("PhoneBean", phoneBean.toString());

					Message msg = Message.obtain();
					msg.what = kURLCONNECTION_GET_RESPONSE;
					msg.obj = phoneBean.toString();
					mHandler.sendMessage(msg);
				}

				@Override
				public void onFailure(Request arg0, IOException arg1) {
					// TODO Auto-generated method stub
				}
			});
		}

		private void okHttppost() {
			final MediaType MEDIA_TYPE_MARKDOWN
					= MediaType.parse("text/x-markdown; charset=utf-8");
			OkHttpClient mOkHttpClient = new OkHttpClient();
			Request request = new Request.Builder()
					.url(Baiduurl)
					.addHeader("apikey", "6c36e1ebba98b1c157d34cfe81c5ef3e")
			        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, Phone))
			        .build();
			Call call = mOkHttpClient.newCall(request);
			call.enqueue(new Callback() {

				@Override
				public void onFailure(Request request, IOException e) {
				}
				@Override
				public void onResponse(Response response) throws IOException {
					String result = response.body().string().toString();


					Message msg = Message.obtain();
					msg.what = kURLCONNECTION_GET_RESPONSE;
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			});

		}
	
}
	
