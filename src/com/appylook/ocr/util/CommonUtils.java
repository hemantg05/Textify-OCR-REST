package com.appylook.ocr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CommonUtils {

	public static File getFile(String base64EncodedImage, String extention)
			throws IOException {
		byte[] btDataFile = new sun.misc.BASE64Decoder()
				.decodeBuffer(base64EncodedImage);
		File of = new File("temp." + extention);
		FileOutputStream osf = new FileOutputStream(of);
		osf.write(btDataFile);
		osf.flush();
		return of;
	}
}
