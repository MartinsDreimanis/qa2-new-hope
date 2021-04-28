import org.junit.jupiter.api.Test;

public class myFirstTest {

    @Test
    public void firstTest() {
        System.out.println("Hello, corona!");

        String firstName = "Dimtro";
        String lastName = "Coronovich";
        String email = "best@colona.lv";
        int age = 34;

        System.out.println("First name: " + firstName + "\nLast name: " + lastName);

        int commentCount = 36;
        int newComments = 22;

        String stringCommentCount = "36";
        String stringNewComments = "22";

        System.out.println(commentCount + newComments);
        System.out.println(stringCommentCount + stringNewComments);

        printSum("15","3");
        printSum("268", "523");
    }

    private void printSum(String a, String b) {
        int first = Integer.parseInt(a);
        int second = Integer.parseInt(b);

        System.out.println("Text is: " + (first + second));
    }
}
