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

import com.appylook.ocr.model.ImageModel;
import com.appylook.ocr.util.CommonUtils;
import com.appylook.ocr.util.OcrUtils;

@Path("/ocr")
public class OcrResource {

	@GET
	public Response get() {
		File imageFile = new File("phototest.tif");
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		// Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		try {
			ImageIO.scanForPlugins();
			String result = instance.doOCR(imageFile);
			System.out.println(result);
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
			File imageFile = CommonUtils.getFile(imageModel.getImage(),
					imageModel.getExtention());
			String result = OcrUtils.doOCR(imageFile);
			imageModel.setText(result);
			imageModel.setImage("");
			return Response.ok(imageModel)
					.header("Content-Type", "application/json").build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().header("Content-Type", "application/json").build();
		} catch (TesseractException e) {
			e.printStackTrace();
			//throw e;
			return Response.serverError().header("Content-Type", "application/json").build();
		}
	}
}
