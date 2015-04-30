import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import us.monoid.web.BinaryResource;
import us.monoid.web.Resty;

public class TTS {

	private static final String BASE_URL = "http://translate.google.com/translate_tts?ie=UTF-8&q={0}&tl={1}&prev=input";

	public static void main(String[] args) {
		
		say("lol");
	}
	
	public static void update(){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat ("HH:mm:ss");
		System.out.println( sdf.format(cal.getTime()));
	}

	public static void say(String text) {
		try {
			File f = new File("translate.mp3");
			String sentence = URLEncoder.encode(text, "UTF-8");
			String urlString = MessageFormat.format(BASE_URL, sentence, "de");
			BinaryResource res = new Resty().bytes(new URI(urlString));
			res.save(f);

			FileInputStream in = new FileInputStream(f);

			Player p = new Player(in);

			p.play();

			p.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}
}