package dt.developer.test.OMelnichenko;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;


    public void run() {
        JFrame frame = new JFrame("DT Developer Test"); // создаем новую форму и сразу задаем ему название  - было со старта
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // позволяем форме закрываться - было со старта
        frame.setMinimumSize(new Dimension(WIDTH-10, HEIGHT-10)); // задаем размеры формы - было со старта
        frame.setVisible(true); // делаем форму видимой  - было со старта
        frame.setLocationRelativeTo(null); // распологаем форму по центру экрана при запуске - было со старта

        frame.setLayout(new BorderLayout()); // создаем конструктор отображения

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(centerPanel, BorderLayout.CENTER);

        northPanel.setLayout(new BorderLayout());
        JPanel northNorthPanel = new JPanel();
        northPanel.add(northNorthPanel, BorderLayout.NORTH);
        JPanel southNorthPanel = new JPanel();
        northPanel.add(southNorthPanel, BorderLayout.SOUTH);


        JButton upLoadButton = new JButton("Add File"); //создаем кнопку для загрузки и задаем её имя
        southPanel.add(upLoadButton, BorderLayout.CENTER); //помещаем кнопку для загрузки на южное (нижнее поле)
        upLoadButton.addActionListener(new ActionListener() {
            private ActionEvent e;

            @Override
            public void actionPerformed(ActionEvent e) {
                this.e = e; //прикрепляем к кнопке "слушатель" (реакцию на нажатие)
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("."));
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                fileChooser.setFileFilter(new FileFilter() {
                    @Override // ставим фильтры на файлы определенного формата
                    public boolean accept(File f) {
                        if (f.getName().endsWith(".png")||(f.isDirectory())) {
                            return true;
                        }
                        return false;
                    }
                    @Override
                    public String getDescription() {
                        return "Image files .png";
                    }
                });
                fileChooser.showOpenDialog(southPanel);

                String fpath = fileChooser.getSelectedFile().getPath();
                String fname = fileChooser.getSelectedFile().getName();

                Path copied = Paths.get(fpath);
                Path originalPath = Paths.get("assets\\"+fname);

                try {
                    Files.copy(copied, originalPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Done");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        JTextField searchField = new JTextField(60); //создаем текстовое поледля поиска и задаем его размер
        southNorthPanel.add(searchField, BorderLayout.EAST); //добавляем текстовое поле для поиска на поле

        //создаем коллекцию в которой хранятся имена файлов находящихся в папке "assets"
        List<String> pic = null;
        try {
            pic = MyFailsArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        centerPanel.setLayout(new GridLayout(5, 5));
        // для каждого файла создаем кнопку с отображжением картинки на кнопке
        for (int i = 0; i < pic.size(); i++) {
            String pictureName = "assets/"+pic.get(i);
            JButton paintButton = new JButton(new ImageIcon(pictureName));

            paintButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    File f = new File(pictureName);
                    Desktop dt = Desktop.getDesktop();
                    try {
                        dt.open(f);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            centerPanel.add(paintButton);
        }

        JButton searchButton = new JButton("Search"); //создаем кнопку поиска и задаем её имя
        southNorthPanel.add(searchButton, BorderLayout.WEST); //помещаем кнопку поиска справа от полея поиска
        List<String> finalPic = pic;
  //      List<String> finalPic1 = pic;
        searchButton.addActionListener(new ActionListener() { // добавляем к полю "слушатель"
            @Override
            public void actionPerformed(ActionEvent e) {
                String request = searchField.getText(); //создаем переменную "запрос" и сохраням в нее текст веденный в поле для поиска

//                find match and build new collection
                List<String> searchPic = new ArrayList<>();
                for (String elem:
                        finalPic)
                    if (elem.startsWith(request)) {
                        searchPic.add(elem);
                        System.out.println(elem);
                    }

                JFrame frameForSearchPic = new JFrame(request);
                frameForSearchPic.setMinimumSize(new Dimension(500,500)); // задаем размеры формы - было со старта
                frameForSearchPic.setVisible(true); // делаем форму видимой  - было со старта
                frameForSearchPic.setLocationRelativeTo(null); // распологаем форму по центру экрана при запуске - было со старта

                frameForSearchPic.setLayout(new GridLayout(5, 5));
                for (int i = 0; i < searchPic.size(); i++) {
                    String pictureName = "assets/" + searchPic.get(i);
                    JButton paintButton = new JButton(new ImageIcon(pictureName));

                    paintButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            File f = new File(pictureName);
                            Desktop dt = Desktop.getDesktop();
                            try {
                                dt.open(f);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    });

                    frameForSearchPic.add(paintButton);
                }
                frameForSearchPic.show();

                searchField.setText("");

            }
        });


        JLabel heading = new JLabel("Image Galery"); // создаем злейбл (нередактируемый текст)
        Font fontHeading = new Font("Verdana", Font.ROMAN_BASELINE, 20); // создали стиль текста для заголовка
        heading.setFont(fontHeading); // присваеваем созданному заголовку созданный стиль текста
        northNorthPanel.add(heading, BorderLayout.CENTER);// размещаем лейбл на северной панеле


    }

    public static List<String> MyFailsArray() throws IOException {
        List<String> pic = new ArrayList();
        File dir = new File("assets");
        for (String path : dir.list()) {
            pic.add(path);
        }
        return pic;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main()::run);
    }


}

