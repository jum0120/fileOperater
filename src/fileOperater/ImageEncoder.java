package fileOperater;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class ImageEncoder {
	public static void main(String[] args) {
		String srcFilePath = "D:\\Google下載\\testInput.jpg";
		String newFilePath = "D:\\Google下載\\_reEncode\\testInput.jpg";

		ImageEncoder imageEncoder = new ImageEncoder();
		try {
			imageEncoder.imageReEncode(srcFilePath, newFilePath);
			System.out.println(new File(newFilePath).getName() + " 圖片重新編碼完成");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void imageReEncode(String srcFilePath, String newFilePath) throws IOException {
		try {
			// 讀取原始圖片
			// BufferedImage originalImage = ImageIO.read(new File(srcFilePath));
			BufferedImage originalImage = ImageIO.read(new File(srcFilePath));

			// JPEG品質範圍為0.00f-1f，1f表示最高品質
			float quality = 1f;
			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(quality);

			// 儲存重新編碼後的圖片
			File newFile = new File(newFilePath);
			ImageOutputStream outputStream = ImageIO.createImageOutputStream(newFile);
			writer.setOutput(outputStream);
			writer.write(null, new IIOImage(originalImage, null, null), param);

			// 釋放資源
			outputStream.close();
			writer.dispose();
		} catch (IOException e) {
			throw new IOException("無法讀取或儲存圖片 src:" + srcFilePath + " new:" + newFilePath);
		}
	}
}
