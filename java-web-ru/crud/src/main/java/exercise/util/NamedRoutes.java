package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }

    public static String postsWithPaginationPath(int page) {
        return NamedRoutes.postsPath() + "?page=" + page;
    }
    public static String postsWithPaginationPath() {
        return NamedRoutes.postsWithPaginationPath(1);
    }

    public static String postsShowPath(String id) {
        return "/posts/" + id;
    }

    public static String postsShowPath(Long id) {
        return NamedRoutes.postsShowPath(String.valueOf(id));
    }
    // END
}
