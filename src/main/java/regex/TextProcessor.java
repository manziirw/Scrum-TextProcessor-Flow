package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TextProcessor {

    public static boolean matchPattern(String text, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            return matcher.find();
        } catch (PatternSyntaxException e) {
            System.err.println("Invalid regex pattern: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An error occurred while matching the pattern: " + e.getMessage());
            return false;
        }
    }

    public static String replaceText(String text, String regex, String replacement) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            return matcher.replaceAll(replacement);
        } catch (PatternSyntaxException e) {
            System.err.println("Invalid regex pattern: " + e.getMessage());
            return text; // Return original text if replacement fails
        } catch (Exception e) {
            System.err.println("An error occurred while replacing text: " + e.getMessage());
            return text; // Return original text if replacement fails
        }
    }
}
