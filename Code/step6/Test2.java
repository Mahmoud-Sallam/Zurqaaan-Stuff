package step6;

import java.util.Deque;
import java.util.LinkedList;

public class Test2 {

    private static final String format = "%s is not valid ended for %s at position %d";

    public static void main(String[] args) {

        Deque dque = new LinkedList();

        String valid = "[{<>}]()()(())";
        String notValid = "[{<}>]()()(())";

        validate(dque, valid);
        validate(dque, notValid);

        System.out.println((int)'{');
        System.out.println((int)'}');
        System.out.println((int)'(');
        System.out.println((int)')');
        System.out.println((int)'<');
        System.out.println((int)'>');


    }

    private static void validate(Deque dque, String datetoCheck) {
        Result result = Result.success();
        for (int i = 0; i < datetoCheck.toCharArray().length; i++) {


            result = checkOrInsert(i,datetoCheck.toCharArray()[i], dque);
            if (result.isSuccess() == false) {
                break;
            }

        }

        if (result.isSuccess() == false) {
            System.err.println(datetoCheck+ "is not valid: " + result.getMessage());
        }else {
            System.out.println(datetoCheck+"is valid data");
        }


    }

    private static Result checkOrInsert(int position, char c, Deque<Character> dque) {

        switch (c) {
            case '{':
            case '<':
            case '[':
                dque.push(c);
                return Result.success();
            case '}':
                Character lastChar = dque.poll();
                return '{' == lastChar ? Result.success() : Result.failed(String.format(format, "}", lastChar, position));
            case '>':
                lastChar = dque.poll();
                return '<' == lastChar ? Result.success() : Result.failed(String.format(format, ">", lastChar, position));
            case ']':
                lastChar = dque.poll();
                return '[' == lastChar ? Result.success() : Result.failed(String.format(format, "]", lastChar, position));

            default:
                return Result.success();


        }
    }

    private static class Result {


        private final boolean success;
        private final String message;

        public Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public static Result success() {
            return new Result(true, "No Error");
        }

        public static Result failed(String msg) {
            return new Result(false, msg);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
