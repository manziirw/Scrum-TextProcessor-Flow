package regex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextProcessorTest {

    @Test
    public void testMatchPattern_ValidPattern_MatchFound() {
        String text = "Hello, world!";
        String regex = "Hello";
        assertTrue(TextProcessor.matchPattern(text, regex));
    }

    @Test
    public void testMatchPattern_ValidPattern_NoMatch() {
        String text = "Hello, world!";
        String regex = "Hi";
        assertFalse(TextProcessor.matchPattern(text, regex));
    }

    @Test
    public void testMatchPattern_InvalidPattern() {
        String text = "Hello, world!";
        String regex = "[";
        assertFalse(TextProcessor.matchPattern(text, regex));
    }

    @Test
    public void testReplaceText_ValidPattern_ReplacementMade() {
        String text = "Hello, world!";
        String regex = "world";
        String replacement = "Java";
        String expected = "Hello, Java!";
        assertEquals(expected, TextProcessor.replaceText(text, regex, replacement));
    }

    @Test
    public void testReplaceText_ValidPattern_NoReplacementMade() {
        String text = "Hello, world!";
        String regex = "Hi";
        String replacement = "Java";
        String expected = "Hello, world!";
        assertEquals(expected, TextProcessor.replaceText(text, regex, replacement));
    }

    @Test
    public void testReplaceText_InvalidPattern() {
        String text = "Hello, world!";
        String regex = "[";
        String replacement = "Java";
        String expected = "Hello, world!";
        assertEquals(expected, TextProcessor.replaceText(text, regex, replacement));
    }

    @Test
    public void testMatchPattern_EmailPattern() {
        String text = "Contact us at support@example.com for more information.";
        String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        assertTrue(TextProcessor.matchPattern(text, regex));
    }

    @Test
    public void testMatchPattern_PhoneNumberPattern() {
        String text = "Call us at (123) 456-7890.";
        String regex = "\\(\\d{3}\\) \\d{3}-\\d{4}";
        assertTrue(TextProcessor.matchPattern(text, regex));
    }

    @Test
    public void testReplaceText_EmailAnonymize() {
        String text = "Contact us at support@example.com for more information.";
        String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        String replacement = "****@****.com";
        String expected = "Contact us at ****@****.com for more information.";
        assertEquals(expected, TextProcessor.replaceText(text, regex, replacement));
    }

    @Test
    public void testReplaceText_RemoveHtmlTags() {
        String text = "<html><body><h1>Title</h1><p>This is a paragraph.</p></body></html>";
        String regex = "<[^>]+>";
        String replacement = "";
        String expected = "TitleThis is a paragraph.";
        assertEquals(expected, TextProcessor.replaceText(text, regex, replacement));
    }
}

