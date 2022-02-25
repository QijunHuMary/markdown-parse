import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


//javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java
//java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest
public class MarkdownParseTest {
    @Test
    public void addition() throws IOException {
        // assertEquals(2, 1 + 1);

        String contentsTest = Files.readString(Path.of("test-file.md"));
        String contentsTest1 = Files.readString(Path.of("test1.md"));
        String contentsTest2 = Files.readString(Path.of("test2.md"));
        String contentsTest3 = Files.readString(Path.of("test3.md"));

        assertEquals(List.of("https://something.com", "some-page.html"), 
            MarkdownParse.getLinks(contentsTest));
        assertEquals(List.of("https://something.com"), 
            MarkdownParse.getLinks(contentsTest1));
        assertEquals(List.of("someLink.html"), 
            MarkdownParse.getLinks(contentsTest2));
        assertEquals(List.of("someLink.html"), 
            MarkdownParse.getLinks(contentsTest3));
    }

    @Test
    public void snippet1() throws IOException{
        String contentsTest = Files.readString(Path.of("test6.md"));
        assertEquals(List.of("`google.com", "google.com", "ucsd.edu", 
            "ucsd.edu"), MarkdownParse.getLinks(contentsTest));
    }

    @Test
    public void snippet2() throws IOException{
        String contentsTest = Files.readString(Path.of("test7.md"));
        assertEquals(List.of("a.com", "a(()).com", "example.com"), 
            MarkdownParse.getLinks(contentsTest));
    }

    @Test
    public void snippet3() throws IOException{
        String contentsTest = Files.readString(Path.of("test8.md"));
        assertEquals(List.of("https://ucsd-cse15l-w22.github.io/"), 
            MarkdownParse.getLinks(contentsTest));
    }
}
