package com.hjwylde.uni.swen221.assignment03.chessview.viewer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.hjwylde.uni.swen221.assignment03.chessview.Board;
import com.hjwylde.uni.swen221.assignment03.chessview.Round;
import com.hjwylde.uni.swen221.assignment03.chessview.moves.Move;

/*
 * Code for Assignment 3, SWEN 221 Name: Henry J. Wylde Usercode: wyldehenr ID: 300224283
 */

public class RoundCanvas extends Canvas {

    private static final long serialVersionUID = 1L;

    private ArrayList<Round> rounds;
    private ArrayList<Board> boards;
    private int index = 0;
    private static String[] preferredFonts = {"Arial", "Times New Roman"};
    private Font font;

    public RoundCanvas(List<Round> rounds, List<Board> boards) {
        this.rounds = new ArrayList<>(rounds);
        this.boards = new ArrayList<>(boards);
        setBounds(0, 0, 200, 400);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        HashSet<String> availableNames = new HashSet<>();

        for (String name : env.getAvailableFontFamilyNames())
            availableNames.add(name);

        for (String pf : RoundCanvas.preferredFonts)
            if (availableNames.contains(pf)) {
                font = new Font(pf, Font.PLAIN, 20);
                break;
            }
    }

    public void bwd(int amount) {
        index = Math.max(0, index - amount);
        repaint();
    }

    public void fwd(int amount) {
        index = Math.min(boards.size() - 1, index + amount);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();
        int height = metrics.getHeight();

        int ypos = metrics.getAscent();

        for (int i = 0; i != rounds.size(); ++i) {
            if ((((i * 2) + 1) >= boards.size()) || (boards.get(i * 2) == null))
                g.setColor(Color.RED);
            else if ((i * 2) < index)
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.LIGHT_GRAY);
            Move white = rounds.get(i).white();
            char[] chars = white.toString().toCharArray();
            g.drawChars(chars, 0, chars.length, 10, ypos);
            ypos += height;
        }

        int midx = (getWidth() - 20) / 2;
        ypos = metrics.getAscent();

        for (int i = 0; i != rounds.size(); ++i) {
            if (((i * 2) + 2) >= boards.size())
                g.setColor(Color.RED);
            else if (((i * 2) + 1) < index)
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.LIGHT_GRAY);
            Move black = rounds.get(i).black();
            if (black != null) {
                char[] chars = black.toString().toCharArray();
                g.drawChars(chars, 0, chars.length, midx, ypos);
                ypos += height;
            }
        }
    }
}
