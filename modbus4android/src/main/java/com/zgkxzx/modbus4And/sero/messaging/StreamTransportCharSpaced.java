/**
 * Copyright (C) 2014 Infinite Automation Software. All rights reserved.
 * @author Terry Packer
 */
package com.zgkxzx.modbus4And.sero.messaging;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Terry Packer
 *
 */
public class StreamTransportCharSpaced extends StreamTransport{

	private static final String TAG = StreamTransportCharSpaced.class.getSimpleName();
	private static final boolean DEBUG = true;

	private final long charSpacing;
	
	/**
	 * @param in
	 * @param out
	 */
	public StreamTransportCharSpaced(InputStream in, OutputStream out, long charSpacing) {
		super(in, out);
		this.charSpacing = charSpacing;
	}

	/**
	 * Perform a write, ensure space between chars
	 */
	@Override
    public void write(byte[] data) throws IOException {
		if (DEBUG)
			Log.d(TAG, "write: start send the request one byte by one byte 123");
		try{
			long waited = 0,writeStart,writeEnd, waitRemaining;
			for(byte b : data){
				writeStart = System.nanoTime();
				out.write(b);
				if (DEBUG) android.util.Log.d(TAG, "out.write(b): " + Integer.toHexString(b));
				writeEnd = System.nanoTime();
				waited = writeEnd - writeStart;
				if(waited < this.charSpacing){
					waitRemaining = this.charSpacing - waited;
					if (DEBUG) android.util.Log.d(TAG, "waitRemaining time: " + waitRemaining);
					Thread.sleep(waitRemaining / 1000000, (int)(waitRemaining % 1000000));
				}
					
			}
			if (DEBUG) android.util.Log.d(TAG, "end of writing");
		}catch(Exception e){
			if (DEBUG)
				android.util.Log.e(TAG, "send the request failed", e);
			throw new IOException(e);
		}
        out.flush();
    }

    public void write(byte[] data, int len) throws IOException {
		try{
		long waited = 0,writeStart,writeEnd, waitRemaining;
			for(int i=0; i< len; i++){
				writeStart = System.nanoTime();
				out.write(data[i]);
				writeEnd = System.nanoTime();
				waited = writeEnd - writeStart;
				if(waited < this.charSpacing){
					waitRemaining = this.charSpacing - waited;
					Thread.sleep(waitRemaining / 1000000, (int)(waitRemaining % 1000000));
				}
					
			}
		}catch(Exception e){
			throw new IOException(e);
		}
        out.flush();
    }
	
	
}
