package cn.everythinggrows.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDateSource extends AbstractRoutingDataSource{
    public Integer getMapSize() {
        return mapSize;
    }

    public void setMapSize(Integer mapSize) {
        this.mapSize = mapSize;
    }

    private Integer mapSize;
    @Override
    protected Object determineCurrentLookupKey() {
        Long dbkey = DBContextHolder.getDBKey() == null ? System.currentTimeMillis() : DBContextHolder.getDBKey();
        Long key = dbkey % (long)this.getMapSize();
        return key.intValue();
    }
}
