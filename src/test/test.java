package test;

import com.xbin.tank.ResourceMan;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName test.test
 * @Descrption
 * @Author xiebin
 * @Date 2020/9/10 22:52
 * @Version 1.0
 **/
public class test {

    public static void main(String[] args) throws IOException {
        BufferedImage read = ImageIO.read(ResourceMan.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
        System.out.println(read);
    }
}
