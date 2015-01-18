package general;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
public class CaptchaServlet extends HttpServlet {
 
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        try {
            int width = 300;
              int height = 100;
          String[] values = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                      "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                      "U", "V", "W", "X", "Y", "Z"};
                  int x = 25;
                  int y = 80;
              InputStream fin = this.getClass().getResourceAsStream("defused.ttf");
            Font newf = Font.createFont (    Font.PLAIN,    fin  ).deriveFont(40f);


              BufferedImage bufferedImage = new BufferedImage(width, height, 
                            BufferedImage.TYPE_INT_RGB);
              Color myColor = new Color (0, 153, 255);
              Graphics2D g2d = bufferedImage.createGraphics();
              g2d.fillRect(0, 0, 300, 100);
              
              g2d.setColor(myColor);
              Random r = new Random();
            g2d.setFont(newf);
              
          String a="";
          //Image aw;
             for (int i = 0; i < 4; i++) {
                      int idx = Math.abs(r.nextInt(values.length));
                      a += values[idx];
                     
                 
             g2d.drawString(values[idx], x, y);
               x += 70 + (Math.abs(r.nextInt()) % 15);
                  y = 30 + Math.abs(r.nextInt()) % 20;
                      //   out [i]= values[idx];
                  }

              g2d.dispose();
              String captcha = a.toUpperCase();
              req.getSession(true).setAttribute("captcha", captcha );
              response.setContentType("image/png");
              OutputStream os = response.getOutputStream();
              ImageIO.write(bufferedImage, "png", os);
              os.close();
        } catch (FontFormatException ex) {
        	response.getWriter().println(ex);
            Logger.getLogger(CaptchaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        	response.getWriter().println(ex);
            Logger.getLogger(CaptchaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
  } 


 
}
