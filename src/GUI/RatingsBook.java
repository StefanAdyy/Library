package GUI;

import Database.DatabaseOperations;
import Utility.UtilityFunctions;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class RatingsBook {
    private JTable ratingsTable;
    private JPanel mainPanel;
    private JScrollPane scrollPane;

    public RatingsBook(Vector<String> usernames, Vector<Integer> ratings) {
        DefaultTableModel t = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                if(column==2)
                    return true;
                return false;
            }
        };
        t.addColumn(UtilityFunctions.Get_Usernames_31(),usernames);
        t.addColumn(UtilityFunctions.Get_Ratings_32(),ratings);
        Vector<String> deleteButtons=new Vector<>(100);
        for(String s:deleteButtons)
            s=UtilityFunctions.Get_Delete_33();
        t.addColumn(UtilityFunctions.Get_Delete_33(),deleteButtons);
        ratingsTable.setModel(t);
        SetAspect();
    }

    private void SetAspect(){
        UtilityFunctions.SetScrollbarAspect(scrollPane);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setBackground(new Color(49,52,59));
        centerRenderer.setForeground(new Color(67,179,224));
        ratingsTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        ratingsTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        ratingsTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        ratingsTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
        ratingsTable.getTableHeader().setReorderingAllowed(false);
        ratingsTable.getTableHeader().setBackground(new Color(16,14,21));
        ratingsTable.getTableHeader().setForeground(new Color(67,179,224));
        ratingsTable.setBorder(new SoftBevelBorder(0,new Color(67,179,224), new Color(67,179,224), new Color(67,179,224), new Color(67,179,224)));
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(new Color(67,179,224));
                setBackground(new Color(30,26,35));
            } else {
                setForeground(new Color(67,179,224));
                setBackground(new Color(30,26,35));
            }
            setText((value == null) ? UtilityFunctions.Get_Delete_33() : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.row=row;
            if (isSelected) {
                button.setForeground(new Color(67,179,224));
                button.setBackground(new Color(30,26,35));
            } else {
                button.setForeground(new Color(67,179,224));
                button.setBackground(new Color(30,26,35));
            }
            label = (value == null) ? UtilityFunctions.Get_Delete_33() : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                DatabaseOperations.setKeyword(String.valueOf(row));
                DatabaseOperations.ExecuteQuery(16);
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}