package googleviewer;


import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class TableModel extends AbstractTableModel {

    private SimpleDateFormat sdf;
    private String[] colunas;
    private List<CalendarEventEntry> eventos;

    public TableModel(List<CalendarEventEntry> eventos,String[] colunas,SimpleDateFormat sdf) {
        this.eventos = eventos;
        this.colunas = colunas;
        this.sdf = sdf;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return eventos.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        CalendarEventEntry object = eventos.get(row);
        List<When> times = object.getTimes();
        switch (column) {
            case 0:
                return object.getTitle().getPlainText();
            case 1:
                String inicio = "";
                for (When string : times) {
                    inicio = sdf.format(new Date(string.getStartTime().getValue()));
                }
                return inicio;
            case 2:
                String s = "";
                for (When string : times) {
                    s = sdf.format(new Date(string.getEndTime().getValue()));
                }
                return s;
            default:
                return "...";
        }


    }
}
