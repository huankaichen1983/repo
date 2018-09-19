package eu.cloudifacturing.www.repo.format;

public class CFGCoordinatesHelper {
    public static String getGroup(String path) {
        StringBuilder group = new StringBuilder();
        if (!path.startsWith("/")) {
            group.append("/");
        }
        int i = path.lastIndexOf("/");
        if (i != -1) {
            group.append(path.substring(0, i));
        }
        return group.toString();
    }

    private CFGCoordinatesHelper() {
        // Don't instantiate
    }
}
