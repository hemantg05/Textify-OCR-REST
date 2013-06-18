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

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrUtils {

	public static String doOCR(File imageFile) throws TesseractException {
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		//Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		ImageIO.scanForPlugins();
		String result = instance.doOCR(imageFile);
		return result;
	}
}