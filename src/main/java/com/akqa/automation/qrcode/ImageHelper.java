package com.akqa.automation.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 8/11/14
 * Time: 5:21 PM
 */
public final class ImageHelper {

    public static String extractContentFromQRCode(BufferedImage bufferedImage) throws IOException, NotFoundException, FormatException, ChecksumException {
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        QRCodeMultiReader reader = new QRCodeMultiReader();
        Result[] result = reader.decodeMultiple(binaryBitmap);

        return result[0].getText();
    }
}
