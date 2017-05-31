package mainPkg;

/*
 * #%L
 * LipskiyAssignment
 * %%
 * Copyright (C) 2016 Debreceni Egyetem
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.JOptionPane;

/**
 * Main class of the project, containing all links for other methods and frames.
 *
 * @version 1.1
 * @author MustDy
 */
public class Chess extends javax.swing.JFrame {

    /**
     * Instantiating {@link ScoreBoard} class, which contains the board itself
     * and some methods to work with database.
     */
    private final ScoreBoard ScoreBoard = new ScoreBoard();

    /**
     * Variable {@code Logger log} is used for printing logging messages.
     */
    private static final Logger log = LoggerFactory.getLogger(Chess.class);

    /**
     * Variable {@code int moves} is used for counting player moves, is
     * increased each time when method {@link #ChessPrint() ChessPrint} is
     * called. Is -1 by default, because method {@link #ChessPrint() ChessPrint}
     * is called once in {@link #Chess() constructor}.
     */
    public static int moves = -1;

    /**
     * Variable {@code String playerName} is used for storing player name, get
     * from {@code JOptionPane} inside {@link #Chess() constructor}.
     */
    public static String playerName = null;

    /**
     * Variable {@code String defaultPlayerName} is needed only for jUnit.
     */
    public static String defaultPlayerName = null;

    /**
     * Variable {@code int ChessOverlay[][]} is a two-dimensional array, which
     * is used to determine the layout of chess pieces. Has additional rows and
     * columns at each side (top, bottom, left and right), to ease searching of
     * empty square in {@link NeghbourChecker#Checker(int, int) Checker()}
     * method. It checks all the possible neighbors, regardless of its position,
     * so these additional rows and columns helps not to receive
     * {@code ArrayOutOfBoundExeption}. 4 for borders, 0 for empty square, 1 for
     * King, 2 for Officer, 3 for Tower
     */
    public static int ChessOverlay[][] = {{4, 4, 4, 4, 4}, {4, 1, 2, 2, 4}, {4, 3, 3, 0, 4}, {4, 4, 4, 4, 4}};

    /**
     * Prints out all the chess pieces according to layout; adds +1 to
     * {@link #moves moves} each time called; calls
     * {@link ScoreBoard#storeScoreboard() StoreScoreboard} if overlay of chess
     * pieces is equal to goal state, shows winning dialog and terminates
     * program. For each {@code jLabel} sets an icon of chess piece, according
     * to {@code int ChessOverlay[][]} by calling
     * {@link ChessBackground#Background(int, int) Background(int, int)} with
     * its coordinates. Receives back an URL of image, which should be printed
     * inside.
     */
    public void ChessPrint() {
        //Print requered chess pieces on squares
        Chess1_1.setIcon(ChessBackground.Background(1, 1));
        Chess1_2.setIcon(ChessBackground.Background(1, 2));
        Chess1_3.setIcon(ChessBackground.Background(1, 3));
        Chess2_1.setIcon(ChessBackground.Background(2, 1));
        Chess2_2.setIcon(ChessBackground.Background(2, 2));
        Chess2_3.setIcon(ChessBackground.Background(2, 3));
        log.trace("Chess pieces printed");

        // Records that player moved a piece (used later for scoreboard)
        moves = 1 + moves;
        log.trace("Moves increased");

        //Checks if user reached goal state - if so, gives winning msg and terminates the programm
        if (Chess.ChessOverlay[1][1] == 2
                && Chess.ChessOverlay[1][2] == 2
                && Chess.ChessOverlay[2][1] == 3
                && Chess.ChessOverlay[2][2] == 3
                && Chess.ChessOverlay[2][3] == 1) {
            ScoreBoard.storeScoreboard();
            JOptionPane.showMessageDialog(Board, "Congratulations, you win!");
            System.exit(0);
        }
    }

    /**
     * Asks user for setting {@code playerName}.
     * try-catch block can be uncommented - in this way when user input
     * null value for {@link #playerName} {@code IllegalArgumentException}
     * will be cached and program will proceed further, however test 
     * {@code IconTest} will fail. 
     */
    public void PlayerName() {  
//        try {
        if (!"test".equals(defaultPlayerName)) {
            playerName = JOptionPane.showInputDialog(Board, "What's your name?");
        }
        if ("test".equals(defaultPlayerName)) {
            playerName = null;
        }
        if (playerName == null || playerName.isEmpty()) {
            throw new IllegalArgumentException();
        }
//        } catch (IllegalArgumentException ex) {
//            System.out.println("Name is null; score will not be saved");
//            log.error("Name is null; score will not be saved");
//            
//        }  
    }

    /**
     * Constructor of the class. Instantiates Scoreboard Table by calling
     * {@link ScoreBoard#table() Table()} method. Also and calls
     * {@link #ChessPrint() ChessPrint()} method to print the initial state of
     * chess pieces.
     */
    public Chess() {
        initComponents();
        ChessPrint();
        //Asking user for name
        PlayerName();
        //Initializing the scoreboard
        ScoreBoard.table();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Win = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Board = new javax.swing.JLayeredPane();
        Chess1_1 = new javax.swing.JLabel();
        Chess1_2 = new javax.swing.JLabel();
        Chess1_3 = new javax.swing.JLabel();
        Chess2_1 = new javax.swing.JLabel();
        Chess2_2 = new javax.swing.JLabel();
        Chess2_3 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        Menu_Game = new javax.swing.JMenu();
        Highscore = new javax.swing.JMenuItem();
        Reset = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        jLabel1.setText("Congratulations!\\nYou win!");

        jButton1.setText("Ok");

        javax.swing.GroupLayout WinLayout = new javax.swing.GroupLayout(Win.getContentPane());
        Win.getContentPane().setLayout(WinLayout);
        WinLayout.setHorizontalGroup(
            WinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WinLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(WinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WinLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WinLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        WinLayout.setVerticalGroup(
            WinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Chess1_1.setBackground(new java.awt.Color(0, 0, 0));
        Chess1_1.setText("Chess1_1");
        Chess1_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chess1_1MouseClicked(evt);
            }
        });

        Chess1_2.setBackground(new java.awt.Color(204, 204, 204));
        Chess1_2.setText("Chess1_2");
        Chess1_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chess1_2MouseClicked(evt);
            }
        });

        Chess1_3.setBackground(new java.awt.Color(204, 204, 204));
        Chess1_3.setText("Chess1_3");
        Chess1_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chess1_3MouseClicked(evt);
            }
        });

        Chess2_1.setText("Chess2_1");
        Chess2_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chess2_1MouseClicked(evt);
            }
        });

        Chess2_2.setText("Chess2_2");
        Chess2_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chess2_2MouseClicked(evt);
            }
        });

        Chess2_3.setText("Chess2_3");
        Chess2_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chess2_3MouseClicked(evt);
            }
        });

        Board.setLayer(Chess1_1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Board.setLayer(Chess1_2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Board.setLayer(Chess1_3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Board.setLayer(Chess2_1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Board.setLayer(Chess2_2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Board.setLayer(Chess2_3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout BoardLayout = new javax.swing.GroupLayout(Board);
        Board.setLayout(BoardLayout);
        BoardLayout.setHorizontalGroup(
            BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BoardLayout.createSequentialGroup()
                        .addComponent(Chess1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Chess1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Chess1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(BoardLayout.createSequentialGroup()
                        .addComponent(Chess2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Chess2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Chess2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        BoardLayout.setVerticalGroup(
            BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Chess1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Chess1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Chess1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Chess2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Chess2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Chess2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Menu_Game.setText("Game");

        Highscore.setText("Highscore");
        Highscore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HighscoreActionPerformed(evt);
            }
        });
        Menu_Game.add(Highscore);

        Reset.setText("Reset Highscores");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });
        Menu_Game.add(Reset);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        Menu_Game.add(Exit);

        MenuBar.add(Menu_Game);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Board)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Board)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void Chess1_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chess1_1MouseClicked
        NeghbourChecker.Checker(1, 1);
        ChessPrint();
    }//GEN-LAST:event_Chess1_1MouseClicked

    private void Chess1_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chess1_2MouseClicked
        NeghbourChecker.Checker(1, 2);
        ChessPrint();
    }//GEN-LAST:event_Chess1_2MouseClicked

    private void Chess1_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chess1_3MouseClicked
        NeghbourChecker.Checker(1, 3);
        ChessPrint();
    }//GEN-LAST:event_Chess1_3MouseClicked

    private void Chess2_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chess2_1MouseClicked
        NeghbourChecker.Checker(2, 1);
        ChessPrint();
    }//GEN-LAST:event_Chess2_1MouseClicked

    private void Chess2_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chess2_2MouseClicked
        NeghbourChecker.Checker(2, 2);
        ChessPrint();
    }//GEN-LAST:event_Chess2_2MouseClicked

    private void Chess2_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chess2_3MouseClicked
        NeghbourChecker.Checker(2, 3);
        ChessPrint();
    }//GEN-LAST:event_Chess2_3MouseClicked

    private void HighscoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HighscoreActionPerformed
        ScoreBoard.setVisible(true);
    }//GEN-LAST:event_HighscoreActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        ScoreBoard.resetScoreboard();
    }//GEN-LAST:event_ResetActionPerformed

    /**
     * Uses only pre-generated code, nothing was changed.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Chess().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Board;
    private javax.swing.JLabel Chess1_1;
    private javax.swing.JLabel Chess1_2;
    private javax.swing.JLabel Chess1_3;
    private javax.swing.JLabel Chess2_1;
    private javax.swing.JLabel Chess2_2;
    private javax.swing.JLabel Chess2_3;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Highscore;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu Menu_Game;
    private javax.swing.JMenuItem Reset;
    private javax.swing.JDialog Win;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
