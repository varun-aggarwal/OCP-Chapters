package datasource.factory;

import datasource.DataSource;
import datasource.impl.DataSourceImpl;

public class DataSourceFactory {

    public DataSource getDataSource() {
        return DataSourceImpl.getInstance();
    }

}
