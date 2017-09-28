package ua.natl.jba.service;

import org.junit.Before;
import org.junit.Test;
import ua.natl.jba.entity.Item;
import ua.natl.jba.exception.RssException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RssServiceTest {

    private RssService rssService;

    @Before
    public void setUp() throws Exception {
        rssService = new RssService();
    }

    @Test
    public void testGetItemsFile() throws RssException {
        List<Item> items = rssService.getItems(new File("test-rss/javka.xml"));
        assertEquals(10, items.size());
        Item firstItem = items.get(0);
        assertEquals("How to solve Source not found error during debug in Eclipse", firstItem.getTitle());
        assertEquals("22 06 2014 23:35:49", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
    }

}
