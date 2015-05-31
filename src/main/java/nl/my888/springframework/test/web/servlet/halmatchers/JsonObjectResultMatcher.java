package nl.my888.springframework.test.web.servlet.halmatchers;

/**
 *
 */
public final class JsonObjectResultMatcher extends JsonObjectResultMatcherTemplate<JsonObjectResultMatcher> {

    public static JsonObjectResultMatcher jsonObjectResultMatcher(String path) {
        return new JsonObjectResultMatcher(path);
    }

    protected JsonObjectResultMatcher(String path) {
        super(path);
    }
}
