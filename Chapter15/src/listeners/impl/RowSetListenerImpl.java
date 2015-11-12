package listeners.impl;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;

public class RowSetListenerImpl implements RowSetListener {

    @Override
    public void rowChanged(RowSetEvent event) {
        if (event.getSource() instanceof RowSet) {
            try {
                ((RowSet) event.getSource()).execute();
            } catch (SQLException se) {
                System.out.println("SQLException during execute");
            }
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {
    }

    @Override
    public void rowSetChanged(RowSetEvent event) { 
    }
}
