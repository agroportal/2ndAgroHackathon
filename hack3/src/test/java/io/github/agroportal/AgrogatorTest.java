package io.github.agroportal;

import io.github.agroportal.api.data.Dataset;
import io.github.agroportal.api.data.DatasetExtractor;
import io.github.agroportal.data.AgroAPIDatasetExtractor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

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
        Dataset agroDataset = null;
        final DatasetExtractor extractor = new AgroAPIDatasetExtractor("https://plateforme.api-agro.fr/", null);
        try {
            agroDataset = extractor.extract( "codes-race-des-ruminants");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue( true );
    }
}
