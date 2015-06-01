package nl.my888.springframework.test.web.servlet.halmatchers;

/**
 * Created by ejl on 01/06/15.
 */
class MatcherUtils {

    public static String pathToLink(String relationType) {
        return String.format("_links['%s']", relationType);
    }
    public static String pathToEmbedded(String relationType) {
        return String.format("_embedded['%s']", relationType);
    }
    public static String pathToProfileLink() {
        return pathToLink("profile");
    }
    public static String pathToSelfLink() {
        return pathToLink("self");
    }

}
