package csvchallenge;

import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.*;

public class FileProcessorStreamsTest {

    @Test
    public void processLineNotValid() {
        Writer writer = new StringWriter();
        FileProcessorStreams.processLine(writer,"".split("\t"));
        assertEquals("Writer should be empty","",writer.toString());
    }

    @Test
    public void processLineValid() {
        Writer writer = new StringWriter();
        FileProcessorStreams.processLine(writer,"Samantha\tSavage\tsavage@gmail.com\t29/01/1940\t037-304-0105\tssavage\t95 Stillwell Avenue APT 280\tNew York 23112\tNew York\t23112".split("\t"));
        assertEquals("Writer should be Samantha,Savage,savage@gmail.com\n","Samantha,Savage,savage@gmail.com\n",writer.toString());
    }

    @Test
    public void processLineInvalidDate() {
        Writer writer = new StringWriter();
        FileProcessorStreams.processLine(writer,"Samantha\tSavage\tsavage@gmail.com\tbad_data\t037-304-0105\tssavage\t95 Stillwell Avenue APT 280\tNew York 23112\tNew York\t23112".split("\t"));
        assertEquals("Writer should be empty","",writer.toString());
    }

    @Test
    public void processLineNotOldEnough() {
        Writer writer = new StringWriter();
        FileProcessorStreams.processLine(writer,"Samantha\tSavage\tsavage@gmail.com\t01/01/2000\t037-304-0105\tssavage\t95 Stillwell Avenue APT 280\tNew York 23112\tNew York\t23112".split("\t"));
        assertEquals("Writer should be empty","",writer.toString());
    }

    @Test
    public void processLineNotNewYork() {
        Writer writer = new StringWriter();
        FileProcessorStreams.processLine(writer,"Samantha\tSavage\tsavage@gmail.com\t29/01/1940\t037-304-0105\tssavage\t95 Stillwell Avenue APT 280\tNew York 23112\tNew Pork\t23112".split("\t"));
        assertEquals("Writer should be empty","",writer.toString());
    }

}