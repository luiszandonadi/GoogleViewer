package googleviewer;

import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import googleviewer.services.UtilService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.xml.bind.annotation.XmlElement.DEFAULT;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class TableModel extends AbstractTableModel {

    private String[] colunas;
    private List<CalendarEventEntry> eventos;
    private UtilService service;

    public TableModel(List<CalendarEventEntry> eventos, String[] colunas, UtilService service) {
        this.eventos = eventos;
        this.colunas = colunas;
        this.service = service;
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
                    DateTime startTime = string.getStartTime();
                    boolean dateOnly = startTime.isDateOnly();
                    inicio = service.getDateFormat(!dateOnly).format(new Date(startTime.getValue()));
                }
                return inicio;
            case 2:
                String s = "";
                for (When string : times) {
                    DateTime endTime = string.getEndTime();
                    boolean dateOnly = endTime.isDateOnly();
                    s = service.getDateFormat(!dateOnly).format(new Date(endTime.getValue()));
                }
                return s;
            case 3:
                boolean b = false;
                for (When string : times) {
                    DateTime dateTime = string.getEndTime();
                    b = dateTime.isDateOnly();
                }
                return b;
            default:
                return "----";
        }


    }

    @Override
    public Class getColumnClass(int c) {
        switch (c) {
            case 3:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    public CalendarEventEntry getCalendarEventEntry(int index) {
        return eventos.get(index);
    }
}
