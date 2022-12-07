package main.java;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;

public class MenuFrame extends JFrame {
    private JLabel label;
    private JPanel menuPanel;

    public Dimension getDimension(int width, int height) {
        return new Dimension(width, height);
    }

    public FlowLayout getFlowLayout(int align, int hgap, int vgap) {
        return new FlowLayout(align, hgap, vgap);
    }

    public BorderLayout getBorderLayout() {
        return new BorderLayout();
    }

    public Font getFont(String name, int style, int size) {
        return new Font(name, style, size);
    }

    public MenuFrame() {
        menuPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JButton newGame = new JButton();
        JButton exit = new JButton();
        AbstractBorder brdrLeft = new TextBubbleBorder(Color.BLACK,2,16,0);
                AbstractBorder brdrRight = new TextBubbleBorder(Color.BLACK,2,16,0,false);
        label = new JLabel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(getFlowLayout(1, 0, 30));
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("AdaShip");
        setResizable(true);
        setPreferredSize(getDimension(600, 600));
        getContentPane().setBackground(Color.cyan);

        menuPanel.setPreferredSize(getDimension(500,500));
        menuPanel.setLayout(getFlowLayout(1, 0, 30));
        menuPanel.setBackground(Color.red);
        // menuPanel.setVisible(false);
        menuPanel.setBorder(brdrLeft);
        label.setFont(getFont("Calibri", Font.PLAIN, 50));
        label.setText("AdaShips");
        

        buttonPanel.setLayout(getFlowLayout(1, 0, 40));
        buttonPanel.setBackground(Color.red);
        buttonPanel.setPreferredSize(getDimension(400, 400));
        
        newGame.setText("New Game");
        newGame.setFont(getFont("Calibiri", Font.BOLD, 20));
        newGame.setBackground(Color.white);
        newGame.setPreferredSize(getDimension(300, 50));
        newGame.setBorder(brdrRight);
        newGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BoardFrame boardFrame = new BoardFrame(AdaShipConfig.getInstance());
                boardFrame.render();
            }
            
        });
        
        exit.setText("Exit");
        exit.setFont(getFont("Calibri", Font.BOLD, 20));
        exit.setBackground(Color.white);
        exit.setPreferredSize(getDimension(300, 50));
        exit.setBorder(brdrRight);
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);                
            }
            
        });

        
        buttonPanel.add(newGame);
        buttonPanel.add(exit);
        menuPanel.add(label);
        menuPanel.add(buttonPanel);
        getContentPane().add(menuPanel);
        pack();
    }

    class TextBubbleBorder extends AbstractBorder {

        private Color color;
        private int thickness = 4;
        private int radii = 8;
        private int pointerSize = 7;
        private Insets insets = null;
        private BasicStroke stroke = null;
        private int strokePad;
        private int pointerPad = 4;
        private boolean left = true;
        RenderingHints hints;
    
        TextBubbleBorder(
                Color color) {
            this(color, 4, 8, 7);
        }
    
        TextBubbleBorder(
                Color color, int thickness, int radii, int pointerSize) {
            this.thickness = thickness;
            this.radii = radii;
            this.pointerSize = pointerSize;
            this.color = color;
    
            stroke = new BasicStroke(thickness);
            strokePad = thickness / 2;
    
            hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
    
            int pad = radii + strokePad;
            int bottomPad = pad + pointerSize + strokePad;
            insets = new Insets(pad, pad, bottomPad, pad);
        }
    
        TextBubbleBorder(
                Color color, int thickness, int radii, int pointerSize, boolean left) {
            this(color, thickness, radii, pointerSize);
            this.left = left;
        }
    
        @Override
        public Insets getBorderInsets(Component c) {
            return insets;
        }
    
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            return getBorderInsets(c);
        }
    
        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x, int y,
                int width, int height) {
    
            Graphics2D g2 = (Graphics2D) g;
    
            int bottomLineY = height - thickness - pointerSize;
    
            RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                    0 + strokePad,
                    0 + strokePad,
                    width - thickness,
                    bottomLineY,
                    radii,
                    radii);
    
            Polygon pointer = new Polygon();
    
            if (left) {
                // left point
                pointer.addPoint(
                        strokePad + radii + pointerPad,
                        bottomLineY);
                // right point
                pointer.addPoint(
                        strokePad + radii + pointerPad + pointerSize,
                        bottomLineY);
                // bottom point
                pointer.addPoint(
                        strokePad + radii + pointerPad + (pointerSize / 2),
                        height - strokePad);
            } else {
                // left point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad),
                        bottomLineY);
                // right point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad + pointerSize),
                        bottomLineY);
                // bottom point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad + (pointerSize / 2)),
                        height - strokePad);
            }
    
            Area area = new Area(bubble);
            area.add(new Area(pointer));
    
            g2.setRenderingHints(hints);
    
            // Paint the BG color of the parent, everywhere outside the clip
            // of the text bubble.
            Component parent  = c.getParent();
            if (parent!=null) {
                Color bg = parent.getBackground();
                Rectangle rect = new Rectangle(0,0,width, height);
                Area borderRegion = new Area(rect);
                borderRegion.subtract(area);
                g2.setClip(borderRegion);
                g2.setColor(bg);
                g2.fillRect(0, 0, width, height);
                g2.setClip(null);
            }
    
            g2.setColor(color);
            g2.setStroke(stroke);
            g2.draw(area);
        }
    }
}
