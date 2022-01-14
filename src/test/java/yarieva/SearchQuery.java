package yarieva;

public enum SearchQuery {
    CURTAIN("curtain"),
    LAMP("lamp");

    private  final String searchQuery;


    SearchQuery(String searchQuery) {
        this.searchQuery=searchQuery;

    }
    public  String getSearchQuery() {
        return searchQuery;
    }
}
