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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

/**
 * Class for creating and managing score board.
 *
 * @author MustDy
 */
public class ScoreBoard extends javax.swing.JFrame {

    /**
     * Variable {@code Logger log} is used for printing logging messages.
     */
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ScoreBoard.class);

    /**
     * Takes players data from Oracle Database and inserts them into table. Uses
     * one additional {@link #sorting(java.util.ArrayList) method for sorting}
     * players by score, and three methods {@link #inputMoves(java.util.ArrayList) inputMoves}
     * {@link #inputNames(java.util.ArrayList)  inputNames}
     * {@link #inputDates(java.util.ArrayList)  inputDates}) for taking data from
     * ArrayList and inserting it into the table.
     */
    public void table() {

        ArrayList<Players> playersList = new ArrayList();

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            log.debug("Driver cannot be registered." + ex);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g", "ENG_JC1VW4", "kassai");
                Statement st = conn.createStatement();) {
            log.info("Connection to database: successful");
            try {
                st.executeUpdate("CREATE TABLE SCOREBOARD (name VARCHAR2(12)"
                        + " ,moves NUMBER(12)"
                        + " ,dateofgame VARCHAR2(12))");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 955) {
                    log.debug("Table already exists; no need to create a new one");
                }
            }
            try (ResultSet rs = st.executeQuery("SELECT * FROM SCOREBOARD")) {
                while (rs.next()) {
                    String tPlayerName = rs.getString(1);
                    if (tPlayerName == null) {
                        tPlayerName = "null";
                    }
                    int tMoves = rs.getInt(2);
                    String tDate = rs.getString(3);
                    playersList.add(new Players(tPlayerName, tMoves, tDate));
                }
                log.info("Players data was successfuly received");
            }
            playersList = sorting(playersList);
            inputNames(playersList);
            inputMoves(playersList);
            inputDates(playersList);
        } catch (SQLException ex) {
            log.debug("Connection to database: fail." + ex);
        }
    }

    /**
     * Sorts players by their score.
     *
     * @param pList list of players
     * @return sorted array list
     */
    public ArrayList<Players> sorting(ArrayList<Players> pList) {

        List<Players> tempList = pList.stream()
                .sorted((p1, p2) -> p1.getMoves() - (p2.getMoves()))
                .collect(Collectors.toList());
        log.trace("Players sorted");
        return new ArrayList<>(tempList);
    }

    /**
     * Inserts names into table.
     *
     * @param pList list of players
     */
    public void inputNames(ArrayList<Players> pList) {

        Object names[] = pList.stream()
                .map(n -> n.getName())
                .toArray();

        for (int i = 0; i < names.length && i < 10; i++) {
            SBoard.getModel().setValueAt(names[i].toString(), i, 0);
        }
    }

    /**
     * Inserts moves into table.
     *
     * @param pList list of players
     */
    public void inputMoves(ArrayList<Players> pList) {
        int moves[] = pList.stream()
                .mapToInt(p -> p.getMoves())
                .toArray();

        for (int i = 0; i < moves.length && i < 10; i++) {

            SBoard.getModel().setValueAt(moves[i], i, 1);
        }
    }

    /**
     * Inserts dates into table.
     *
     * @param pList list of players
     */
    public void inputDates(ArrayList<Players> pList) {
        Object dates[] = pList.stream()
                .map(n -> n.getDate())
                .toArray();

        for (int i = 0; i < dates.length && i < 10; i++) {
            SBoard.getModel().setValueAt(dates[i].toString(), i, 2);
        }
    }

    /**
     * Adds {@link Chess#playerName Player name}, its number of
     * {@link Chess#moves moves} and the {@code LocalDate} to the database.
     */
    public void storeScoreboard() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            log.debug("Driver cannot be registered." + ex);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g", "ENG_JC1VW4", "kassai");
                Statement st = conn.createStatement();
                PreparedStatement pst = conn.prepareStatement("INSERT INTO SCOREBOARD (name, moves, dateofgame) VALUES (?,?,?)");) {
            log.info("Connection to database: successful");
            try {
                st.executeUpdate("CREATE TABLE SCOREBOARD (name VARCHAR2(12)"
                        + " ,moves NUMBER(12)"
                        + " ,dateofgame VARCHAR2(12))");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 955) {
                    log.debug("Table already exists; no need to create a new one");
                }
            }

            if (!(Chess.playerName == null || Chess.playerName.isEmpty())) {
                // Stores player's name
                pst.setString(1, Chess.playerName);

                // Stores player's moves
                pst.setInt(2, Chess.moves);

                // Stores the current date via string
                LocalDate date = LocalDate.now();
                String dateInString = date.toString();
                pst.setString(3, dateInString);

                pst.executeUpdate();
                log.info("Players data was successfuly stored");
            }
        } catch (SQLException ex) {
            log.debug("Connection to database: fail." + ex);
        }
    }

    /**
     * Deletes the score board table from database.
     */
    public void resetScoreboard() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            log.debug("Driver cannot be registered." + ex);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g", "ENG_JC1VW4", "kassai");
                Statement st = conn.createStatement();) {
            log.info("Connection to database: successful");
            try {
                st.executeUpdate("DROP TABLE SCOREBOARD");
                log.info("All data was deleted from the database!");
            } catch (SQLException ex) {
                log.debug("Table do not exists." + ex);
            }
        } catch (SQLException ex) {
            log.debug("Connection to database: fail." + ex);
        }
        table();
    }

    /**
     * Creates new form ScoreBoard.
     */
    public ScoreBoard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SWindow = new javax.swing.JScrollPane();
        SBoard = new javax.swing.JTable();

        setResizable(false);

        SBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Moves", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SBoard.getTableHeader().setReorderingAllowed(false);
        SWindow.setViewportView(SBoard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
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
            java.util.logging.Logger.getLogger(ScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ScoreBoard().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable SBoard;
    private javax.swing.JScrollPane SWindow;
    // End of variables declaration//GEN-END:variables
}
