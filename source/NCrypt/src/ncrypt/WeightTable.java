package ncrypt;

import javax.swing.table.AbstractTableModel;

/**
 * @descriptions
 *  A table for displaying the weight matrix in NCryptPanel table GUI
 *  just for displaying purpose only
 * @author phoenix
 */
public class WeightTable extends AbstractTableModel {
    private int row, col;
    private Integer[][] data;
    private String[] columnNames;

    public WeightTable(int r, int c) {
        this.row = r;
        this.col = c;
        data = new Integer[r][c];
        columnNames = new String[c];
        for (int i = 0; i < c; i++) {
            columnNames[i] = "";
        }
    }

    @Override
    public int getRowCount() {
        return row;
    }

    @Override
    public int getColumnCount() {
        return col;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = (Integer) aValue;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }
    
}
