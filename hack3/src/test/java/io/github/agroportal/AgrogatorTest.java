package io.github.agroportal;

import io.github.agroportal.data.AgroAPIDatasetExtractor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.net.URL;

/**
 * Unit test for simple Agrogator.
 */
public class AgrogatorTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AgrogatorTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AgrogatorTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * Test download CSV
     */
    public void testExtract()
    {
        // https://plateforme.api-agro.fr/explore/dataset/codes-race-des-ruminants/download/?format=csv&timezone=Europe/Berlin&use_labels_for_header=true
        AgroAPIDatasetExtractor extractor = new AgroAPIDatasetExtractor("https://plateforme.api-agro.fr/", "codes-race-des-ruminants");
        extractor.extract();
        assertTrue( true );
    }
}
