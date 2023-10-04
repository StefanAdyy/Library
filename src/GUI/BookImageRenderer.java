package GUI;

import Utility.UtilityFunctions;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BookImageRenderer extends DefaultListCellRenderer {

    public Map<String, ImageIcon> getImageMap() {
        return imageMap;
    }

    private Map<String, ImageIcon> imageMap;

    BookImageRenderer(Vector<String> nameList, Vector<String> imageLinks) {
        imageMap = createImageMap(nameList, imageLinks);
    }

    Font font = new Font(UtilityFunctions.Get_Helvetica_0(), Font.BOLD, 24);

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        label.setIcon(imageMap.get((String) value));
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        label.setFont(font);
        return label;
    }

    private Map<String, ImageIcon> createImageMap(Vector<String> nameList, Vector<String> imageLinks) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {
//                map.put("Mario", new ImageIcon(new URL("http://i.stack.imgur.com/NCsHu.png")));
//                map.put("Luigi", new ImageIcon(new URL("http://i.stack.imgur.com/UvHN4.png")));
//                map.put("Bowser", new ImageIcon(new URL("http://i.stack.imgur.com/s89ON.png")));
//                map.put("Koopa", new ImageIcon(new URL("http://i.stack.imgur.com/QEK2o.png")));
//                map.put("Princess", new ImageIcon(new URL("http://i.stack.imgur.com/f4T4l.png")));
            for(int i=0;i<nameList.size();i++)
                map.put(nameList.get(i),new ImageIcon(new URL(imageLinks.get(i))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
}
