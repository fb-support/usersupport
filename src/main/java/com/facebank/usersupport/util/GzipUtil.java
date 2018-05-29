package com.facebank.usersupport.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
	
	public static final int BUFFER = 1024; 
	
	public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        byte[] output = null;
	    try{
             bais = new ByteArrayInputStream(data);
             baos = new ByteArrayOutputStream();
            // 压缩
            compress(bais, baos);

            output = baos.toByteArray();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != baos){
                baos.flush();
                baos.close();
            }
            if(null != bais){
                bais.close();
            }
        }
        return output;
    }  
	
	public static void compress(InputStream is, OutputStream os)  
            throws Exception {

        GZIPOutputStream gos = null;
        try {

            gos = new GZIPOutputStream(os);

            int count;
            byte data[] = new byte[BUFFER];
            while ((count = is.read(data, 0, BUFFER)) != -1) {
                gos.write(data, 0, count);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            gos.finish();
            gos.flush();
            gos.close();
        }
    }
}
