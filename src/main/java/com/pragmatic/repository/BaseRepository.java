package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;

import java.util.List;
import java.util.Map;

public class BaseRepository {
    protected static String tableImportName;
    protected FileDataProvider file;
    protected Integer lastId;
    protected Map<Integer, Object> repoMap;
    protected List<String> repoSchema ;

    public BaseRepository(FileDataProvider file, Integer lastId, Map<Integer, Object> repoMap, List<String> repoSchema) {
        this.file = file;
        this.lastId = lastId;
        this.repoMap = repoMap;
        this.repoSchema = repoSchema;
    }
}
