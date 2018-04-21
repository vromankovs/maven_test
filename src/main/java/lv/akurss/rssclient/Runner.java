package lv.akurss.rssclient;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class Runner {

    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws IOException, FeedException {

        String url = "https://bash.im/rss/";
        SyndFeedInput feedInput = new SyndFeedInput();
        SyndFeed feed = feedInput.build(new XmlReader(new URL(url)));

        log.info("RSS feed from {}", feed.getTitle());

        feed.getEntries().stream()
                .map(SyndEntry::getDescription)
                .forEach(it->log.info(format(it.getValue())));

    }

    protected static String format(String entry){

        StringBuilder sb = new StringBuilder();
        sb.append("\n\n ==================== \n");
        sb.append(StringEscapeUtils.unescapeHtml4(entry).replace("<br>", "\n"));
        sb.append("\n ==================== \n");
        return sb.toString();

    }

}
