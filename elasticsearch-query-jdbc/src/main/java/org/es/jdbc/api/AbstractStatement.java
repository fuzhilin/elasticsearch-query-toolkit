package org.es.jdbc.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

public abstract class AbstractStatement extends AbstractFeatureNotSupportedStatement {

    private boolean isClosed = false;

    @Override
    public void close() throws SQLException {
        isClosed = true;
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return 0;
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        // ignore
    }

    @Override
    public int getMaxRows() throws SQLException {
        return 0;
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        // ignore
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        // ignore
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return 0;
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        // ignore
    }

    @Override
    public void cancel() throws SQLException {
        // ignore
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        // ignore
    }

    @Override
    public int getFetchSize() throws SQLException {
        return 0;
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return ResultSet.CONCUR_READ_ONLY;
    }

    @Override
    public int getResultSetType() throws SQLException {
        return ResultSet.TYPE_FORWARD_ONLY;
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return false;
    }


    @Override
    public int getResultSetHoldability() throws SQLException {
        return ResultSet.CLOSE_CURSORS_AT_COMMIT;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return isClosed;
    }


    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        // ignore
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <T> T unwrap(final Class<T> iface) throws SQLException {
        if (isWrapperFor(iface)) {
            return (T) this;
        }
        throw new SQLException(String.format("[%s] cannot be unwrapped as [%s]", getClass().getName(), iface.getName()));
    }

    @Override
    public final boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return iface.isInstance(this);
    }


    protected abstract ResultSet executeQuery(String sql, Object[] args) throws SQLException;
}