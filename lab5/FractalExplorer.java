package lab5;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileFilter;

public class FractalExplorer
{
    /**
     * Необходимые для отслеживания поля
     */
    private int displaySize; // Размер изображения

    private JImageDisplay display;

    private FractalGenerator fractal;

    private Rectangle2D.Double range;

    public FractalExplorer(int size) {
        displaySize = size;

        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);

    }

    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        JFrame myframe = new JFrame("Изображение фрактала");

        /**
         * Создание списка фракталов
         */
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Выберите фрактал");

        myframe.add(display, BorderLayout.CENTER);

        JComboBox radioBox = new JComboBox();
        FractalGenerator mandelbrotFractal = new Mandelbrot();
        radioBox.addItem(mandelbrotFractal);
        FractalGenerator bsFractal = new BurningShip();
        radioBox.addItem(bsFractal);
        FractalGenerator tFractal = new Tricorn();
        radioBox.addItem(tFractal);

        panel.add(label);
        panel.add(radioBox);


        /**
         * Создаем кнопку сохранения и крепим к панели и к фрейму
         */
        ButtonHandler saveHandler = new ButtonHandler();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(saveHandler);

        JPanel myBottomPanel = new JPanel();

        myBottomPanel.add(saveButton);



        /** Создание кнопки сброса */
        JButton resetButton = new JButton("Reset");
        ButtonHandler handler = new ButtonHandler();
        resetButton.addActionListener(handler);
        myBottomPanel.add(resetButton);

        myframe.add(myBottomPanel, BorderLayout.SOUTH);


        /**
         * top panel
         */
        myframe.add(panel, BorderLayout.NORTH);
        /**
        myframe.add(display, BorderLayout.CENTER);

        /** Instance of ButtonHandler on the combo box. **/
        ButtonHandler fractalChooser = new ButtonHandler();
        radioBox.addActionListener(fractalChooser);

        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }

    /**
     * Метод проходится по каждому пиксели и вызывает функциою для рассчета количества
     * итераций для определенной координаты.
     */
    private void drawFractal()
    {
        /** Итерируемся по каждому пикселю */
        for (int x=0; x < displaySize; x++) {
            for (int y=0; y < displaySize; y++) {

                /**
                 * Find the corresponding coordinates xCoord and yCoord
                 * in the fractal's display area.
                 */
                double xCoord = fractal.getCoord(range.x,
                        range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y,
                        range.y + range.height, displaySize, y);

                /**
                 * Compute the number of iterations for the coordinates in
                 * the fractal's display area.
                 */
                int iteration = fractal.numIterations(xCoord, yCoord);

                /** If number of iterations is -1, set the pixel to black. */
                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }

                else {
                    /**
                     * Otherwise, choose a hue value based on the number
                     * of iterations.
                     */
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    /** Update the display with the color for each pixel. */
                    display.drawPixel(x, y, rgbColor);
                }

            }
        }
        /**
         * When all the pixels have been drawn, repaint JImageDisplay to match
         * current contents of its image.
         */
        display.repaint();
    }

    /**
     * Обработчик MouseListener
     */
    private class MouseHandler extends MouseAdapter
    {
        /**
         * When the handler receives a mouse-click event, it maps the pixel-
         * coordinates of the click into the area of the fractal that is being
         * displayed, and then calls the generator's recenterAndZoomRange()
         * method with coordinates that were clicked and a 0.5 scale.
         */
        @Override
        public void mouseClicked(MouseEvent e)
        {
            /** Get x coordinate of display area of mouse click. */
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,
                    range.x + range.width, displaySize, x);

            /** Get y coordinate of display area of mouse click. */
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,
                    range.y + range.height, displaySize, y);

            /**
             * Call the generator's recenterAndZoomRange() method with
             * coordinates that were clicked and a 0.5 scale.
             */
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            /**
             * Redraw the fractal after the area being
             * displayed has changed.
             */
            drawFractal();
        }
    }

    /**
     * Класс для ообработки событий кнопок и селекта
     */
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            /** Get the source of the action. **/
            String command = e.getActionCommand();
            System.out.println(command);


            /**
             * If the source is the combo box, get the fractal the user
             * selected and display it.
             */
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();

            }
            /**
             * If the source is the reset button, reset the display and draw
             * the fractal.
             */
            else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            /**
             * If the source is the save button, save the current fractal
             * image.
             */
            else if (command.equals("Save")) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);

                /**
                 * Pops up a "Save file" window which lets the user select a
                 * directory and file to save to.
                 */
                int userSelection = chooser.showSaveDialog(display);

                /**
                 * If the outcome of the file-selection operation is
                 * APPROVE_OPTION, continue with the file-save operation.
                 */
                if (userSelection == JFileChooser.APPROVE_OPTION) {

                    /** Get the file and file name. **/
                    java.io.File file = chooser.getSelectedFile();
                    String file_name = file.toString();

                    /** Try saving the fractal image to disk. **/
                    try {
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }
                    /**
                     * Catches all exceptions and prints a message with the
                     * exception.
                     */
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display,
                                exception.getMessage(), "Cannot Save Image",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**
                 * If the file-save operation is not APPROVE_OPTION, return.
                 */
                else return;
            }
        }
    }

    /**
     * A static main() method to launch FractalExplorer.  Initializes a new
     * FractalExplorer instance with a display-size of 600, calls
     * createAndShowGUI() on the explorer object, and then calls
     * drawFractal() on the explorer to see the initial view.
     */
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(700);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}