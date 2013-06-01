package com.appylook.ocr.util;

import java.io.File;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrUtils {

	public static String doOCR(File imageFile) throws TesseractException {
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		// Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		ImageIO.scanForPlugins();
		String result = instance.doOCR(imageFile);
		System.out.println(result);
		return result;
	}
}
