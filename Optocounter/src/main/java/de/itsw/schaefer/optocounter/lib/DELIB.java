package de.itsw.schaefer.optocounter.lib;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface DELIB extends Library {
	DELIB INSTANCE = (DELIB) Native.loadLibrary("delib64", DELIB.class);

	public long DapiDIGetCounter(long handle, long channel, long readMode);

	public long DapiOpenModule(long moduleID, long nr);
}
