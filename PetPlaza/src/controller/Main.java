package controller;

import view.JFrameInterface;

/**
 *
 * @author Ricky Hayes 15888703
 */
public class Main {
    
    public static void main(String[] args) {
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            new JFrameInterface().setVisible(true);
            }
        });
    }
    
}
