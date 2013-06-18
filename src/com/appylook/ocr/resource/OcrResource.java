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
package com.appylook.ocr.resource;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.log4j.Logger;

import com.appylook.ocr.model.ImageModel;
import com.appylook.ocr.util.CommonUtils;
import com.appylook.ocr.util.OcrUtils;

@Path("/ocr")
public class OcrResource {

	Logger LOGGER = Logger.getLogger(OcrResource.class);

	@GET
	public Response get() {
		File imageFile = new File("phototest.tif");
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		// Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		try {
			ImageIO.scanForPlugins();
			String result = instance.doOCR(imageFile);
			System.out.println(result);
			LOGGER.info("OCR Result = " + result);
			return Response.ok(result).build();
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
			return Response.serverError().build();
		}
	}

	public static void main(String[] args) {
		File imageFile = new File("phototest.tif");
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		// Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);

		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}

	@Path("/base64")
	@POST
	public Response doOcr(ImageModel imageModel) throws IOException, TesseractException {
		
		try {
			File imageFile = CommonUtils.getFile(imageModel.getImage(), imageModel.getExtention());
			String result = OcrUtils.doOCR(imageFile);
			LOGGER.info("OCR Result = " + result);
			imageModel.setText(result);
			imageModel.setImage("");
			return Response.ok(imageModel).header("Content-Type", "application/json").build();
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e.getCause());
			return Response.serverError().header("Content-Type", "application/json").build();
		} catch (TesseractException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e.getCause());
			return Response.serverError().header("Content-Type", "application/json").build();
		}
	}
}
