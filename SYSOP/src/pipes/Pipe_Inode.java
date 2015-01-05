package pipes;

import java.io.UnsupportedEncodingException;

public class Pipe_Inode {
							/* kolejka dla procesow,ktore chca 
                               pisac do pelnego lacza oraz tych,
                               ktore chca czytac z pustego lacza */
	
         String buff;         // bufor danych

         int start;         // poczatek danych w buforze
         int len;           // dlugosc danych
         int lock;          // blokada lacza
         
         /*int rd_openers;     ilosc czytelnikow, ktorzy otworzyli
                               lacze (tylko dla lacza nazwanego) */ 
         /*int wr_openers;     ilosc pisarzy, ktorzy otworzyli
                               lacze (tylko dla lacza nazwanego) */
         
         int readers;       /* ilosc czytelnikow */
         int writers;       /* ilosc pisarzy */
         
         public Pipe_Inode()
         {
        	buff = null;
        	start = 0;
        	len = 0;
        	lock = 0;
        	readers = 0;
        	writers = 0;
         }
}
