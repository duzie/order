package com.p.order.controller;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class BarcodeController {

    @GetMapping("/barcode/{data}")
    public void barCode(@PathVariable String data, HttpServletResponse response) throws IOException {
        if ("a".equals(data)) {
            BufferedImage image = new BufferedImage(200, 30, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2D = image.createGraphics();
            graphics2D.setBackground(new Color(0, true));
            graphics2D.dispose();
            ImageIO.write(image, "png", response.getOutputStream());
            return;
        }
        Code128Bean code128Bean = new Code128Bean();
        code128Bean.setQuietZone(1);
        code128Bean.setVerticalQuietZone(1);
        code128Bean.setHeight(10);
        BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(response.getOutputStream(),
                "image/png", 2000, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        code128Bean.generateBarcode(canvasProvider, data);
        canvasProvider.finish();
    }
}
