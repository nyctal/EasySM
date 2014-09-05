package calculators;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import store.*;
import executors.Main;
import java.net.MalformedURLException;
import java.net.URL;

public class Serialization {
	/**
	 * 
	 * @param f
	 * @return 
	 */

	public static Project deserialize(File file) {
		//throw new UnsupportedOperationException();
            XStream xs = new XStream();
            String xml="";

            Project pr=null;

             // definiamo il percorso al file da leggere
            //File doc=new File(fileName);
            URL path=null;

            // creaiamo un blocco try-catch per intercettare le eccezioni
            try
            {
              // mostriamo il percorso al file
              path=file.toURL();
              System.out.println("Il doc si trova nel percorso" + path);

              //mostriamo il nome del file
              //doc=new File(path.getFile());
              System.out.println("Nome del file " + file.getName());
              int i;

              // apriamo lo stream di input...
              InputStream is=path.openStream();
              BufferedReader br=new BufferedReader(new InputStreamReader(is));

              // ...e avviamo la lettura del file con un ciclo
              do
              {
                i=br.read();
                //System.out.println((char)i);
                xml += (char)i;
              }
              while (i!=-1);
              is.close();
            }

            // intercettiamo eventuali eccezioni
            catch (MalformedURLException e)
            {
              System.out.println("Attenzione:" + e);
            }
            catch (IOException e)
            {
              System.out.println(e.getMessage());
            }

            return pr = (Project)xs.fromXML(xml);
	}

	/**
	 *
	 * @param f
	 * @return
	 */
	public static void serialize(Project f) {
            XStream xs = new XStream();
            String xml = xs.toXML(f);

            try {
                  FileOutputStream file = new FileOutputStream(f.getCurrentFile()+".esm");
                  PrintStream Output = new PrintStream(file);


                  Output.print(xml);

                } catch (IOException e) {
                  System.out.println("Errore: " + e);
                  System.exit(1);
                }
        }
}
