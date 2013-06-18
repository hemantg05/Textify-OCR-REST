/**
 * Copyright @ 2013 Hemant Gondane
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.appylook.ocr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CommonUtils {

	public static File getFile(String base64EncodedImage, String extention)			throws IOException {
		byte[] btDataFile = new sun.misc.BASE64Decoder()
				.decodeBuffer(base64EncodedImage);
		File of = new File("temp." + extention);
		System.out.println("*************************" +of.getAbsolutePath());
		FileOutputStream osf = new FileOutputStream(of);
		osf.write(btDataFile);
		osf.flush();
		return of;
	}
}
