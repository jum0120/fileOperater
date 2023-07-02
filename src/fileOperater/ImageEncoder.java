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
		String srcFilePath = "D:\\Google�U��\\testInput.jpg";
		String newFilePath = "D:\\Google�U��\\_reEncode\\testInput.jpg";

		ImageEncoder imageEncoder = new ImageEncoder();
		try {
			imageEncoder.imageReEncode(srcFilePath, newFilePath);
			System.out.println(new File(newFilePath).getName() + " �Ϥ����s�s�X����");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void imageReEncode(String srcFilePath, String newFilePath) throws IOException {
		try {
			// Ū����l�Ϥ�
			// BufferedImage originalImage = ImageIO.read(new File(srcFilePath));
			BufferedImage originalImage = ImageIO.read(new File(srcFilePath));

			// JPEG�~��d��0.00f-1f�A1f��̰ܳ��~��
			float quality = 1f;
			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(quality);

			// �x�s���s�s�X�᪺�Ϥ�
			File newFile = new File(newFilePath);
			ImageOutputStream outputStream = ImageIO.createImageOutputStream(newFile);
			writer.setOutput(outputStream);
			writer.write(null, new IIOImage(originalImage, null, null), param);

			// ����귽
			outputStream.close();
			writer.dispose();
		} catch (IOException e) {
			throw new IOException("�L�kŪ�����x�s�Ϥ� src:" + srcFilePath + " new:" + newFilePath);
		}
	}
}
