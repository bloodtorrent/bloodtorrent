import java.io.*;
import java.net.*;

public class SmokeTest {
	public static void main(String[] args) {
		Socket soc = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			soc = new Socket("localhost", 8080);
			is = soc.getInputStream();
			os = soc.getOutputStream();
			os.write("GET / HTTP/1.0\r\n\r\n".getBytes());
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);
			String response = br.readLine();
			if (response.startsWith("HTTP/1.1 200")) {
				System.out.println("Smoke test success!");
			} else {
				System.err.println("Smoke test failed!");
				System.err.println("Response was: " + response);
				System.exit(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {}
			}
			if (soc != null) {
				try {
					soc.close();
				} catch (Exception e) {}
			}
		}
	}
}
