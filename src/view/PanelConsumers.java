package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class PanelConsumers extends JPanel {

  private Controller controller;
    public PanelConsumers(Controller controller)
    {
        this.controller = controller;
        createConsumer1();
        createConsumer2();
        createConsumer3();
   }

    private JLabel lblIcaItems;			// Ica limits
    private JLabel lblIcaStatus;		// load status
    private JTextArea lstIca;			// The cargo list
    private JButton btnIcaStart;		// The buttons
    private JButton btnIcaStop;
    private JCheckBox chkIcaCont;		// Continue checkbox

    private void createConsumer1()
    {
       setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"  Consumers  "));
       setBounds(266, 13, 440, 379);
       setLayout(null);

       JPanel pnlICA = new JPanel();
       pnlICA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: ICA "));
       pnlICA.setBounds(19, 19,415, 100);
       pnlICA.setLayout(null);

       //Status
       lblIcaStatus = new JLabel("Status");
       lblIcaStatus.setBounds(7, 16, 130, 17);
       pnlICA.add(lblIcaStatus);

       //items
       lblIcaItems = new JLabel("Items");
       lblIcaItems.setBounds(150, 20, 120, 17);
       pnlICA.add(lblIcaItems);

       //checkbox, continue load
       chkIcaCont = new JCheckBox("Continue load");
       chkIcaCont.setBounds(7, 39, 130, 23);
       pnlICA.add(chkIcaCont);

       // start loading
        btnIcaStart = new JButton("Start Loading");
        btnIcaStart.setBounds(7, 64, 120, 23);
        btnIcaStart.addActionListener(l -> {
            controller.startConsumer(0);    //TO DO
            btnIcaStart.setEnabled(true);
        }); //actionlistener to controller method
        pnlICA.add(btnIcaStart);

        btnIcaStop = new JButton("Stop");
        btnIcaStop.setBounds(130, 64, 60, 23);
        btnIcaStop.addActionListener(l -> {
            controller.stopConsumer(0);     //TO DO
            btnIcaStart.setEnabled(true);
        }); //actionlistener to controller method
        pnlICA.add(btnIcaStop);

        lstIca = new JTextArea();
        lstIca.setEditable(false);

        JScrollPane spane = new JScrollPane(lstIca);
        spane.setBounds(200, 16, 200, 69);
        spane.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlICA.add(spane);

        add(pnlICA);

   }

    private JLabel lblCoopItems;
    private JLabel lblCoopStatus;		// load status
    private JTextArea lstCoop;			// The cargo list
    private JButton btnCoopStart;		// The buttons
    private JButton btnCoopStop;
    private JCheckBox chkCoopCont;

    // Continue checkbox
   private void createConsumer2()
   {
       JPanel pnlCOOP = new JPanel();
       pnlCOOP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: Coop"));
       pnlCOOP.setBounds(19, 139, 415, 100);
       pnlCOOP.setLayout(null);
       add(pnlCOOP);

       //Status
       lblCoopStatus = new JLabel("Status");
       lblCoopStatus.setBounds(7, 16, 150, 23);
       pnlCOOP.add(lblCoopStatus);

       //Items
       lblCoopItems = new JLabel("Items");
       lblCoopItems.setBounds(150, 20, 120, 17);
       pnlCOOP.add(lblCoopItems);

       //Continue load
       chkCoopCont = new JCheckBox("Continue load");
       chkCoopCont.setBounds(7, 39, 130, 17);
       pnlCOOP.add(chkCoopCont);

       // Start button
       btnCoopStart = new JButton("Start Loading");
       btnCoopStart.setBounds(7, 64, 120, 23);
       btnCoopStart.addActionListener(l -> {
           controller.startConsumer(1);   //TO DO
           btnCoopStart.setEnabled(false);
       }); //actionlistener to controller method
       pnlCOOP.add(btnCoopStart);

       //Stop button
       btnCoopStop = new JButton("Stop");
       btnCoopStop.setBounds(130, 64, 60, 23);
       btnCoopStop.addActionListener(l -> {
           controller.stopConsumer(1);      //TO DO
           btnCoopStart.setEnabled(true);
       }); //actionlistener to controller method
       pnlCOOP.add(btnCoopStop);

       //listbox
       lstCoop = new JTextArea();
       lstCoop.setEditable(false);
       JScrollPane spaneC = new JScrollPane(lstCoop);
       spaneC.setBounds(200, 16, 200, 69);
       spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
       pnlCOOP.add(spaneC);

       // All done, add to consumers panel
       add(pnlCOOP);


   }
    private JLabel lblCGItems;
    private JLabel lblCGWeight;
    private JLabel lblCGVolume;
    private JLabel lblCGStatus;			// load status
    private JTextArea lstCG;			// The cargo list
    private JButton btnCGStart;			// The buttons
    private JButton btnCGStop;
    private JCheckBox chkCGCont;

    // Continue load
    private void createConsumer3()
    {
        JPanel pnlCG = new JPanel();
        pnlCG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: City Gross"));
        pnlCG.setBounds(19, 262, 415, 100);
        pnlCG.setLayout(null);
        add(pnlCG);

        //Status
        lblCGStatus = new JLabel("Status");
        lblCGStatus.setBounds(7, 16, 150, 23);
        pnlCG.add(lblCGStatus);

        //items
        lblCGItems = new JLabel("Items");
        lblCGItems.setBounds(150, 20, 120, 17);
        pnlCG.add(lblCGItems);

        // continue load
        chkCGCont = new JCheckBox("Continue load");
        chkCGCont.setBounds(7, 39, 130, 17);
        pnlCG.add(chkCGCont);// All done, add to consumers panel

        //Start button
        btnCGStart = new JButton("Start Loading");
        btnCGStart.setBounds(7, 64, 120, 23);

        btnCGStart.addActionListener(l -> {
            controller.startConsumer(2);        //TO DO
            btnCGStart.setEnabled(false);
        }); //actionlistener to controller method
        pnlCG.add(btnCGStart);

        //stop button
        btnCGStop = new JButton("Stop");
        btnCGStop.setBounds(130, 64, 60, 23);

        btnCGStop.addActionListener(l -> {
            controller.stopConsumer(2);            //TO DO
            btnCGStart.setEnabled(true);
        }); //actionlistener to controller method
        pnlCG.add(btnCGStop);

        lstCG = new JTextArea();
        lstCG.setEditable(false);
        JScrollPane spaneG = new JScrollPane(lstCG);
        spaneG.setBounds(200, 16, 200, 69);
        spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlCG.add(spaneG);
      }

    public void updateConInfo(int type, int items) {

        switch (type) {
            case 0:
                lblIcaItems.setText(String.valueOf(items));
                 break;
          case 1:
                lblCoopItems.setText(String.valueOf(items));
                break;
            case 2:
                lblCGItems.setText(String.valueOf(items));
                break;
        }
    }
    public void updateConsumerList(String product, String name) {

        switch (name) {
            case "ICA":
                lstIca.append(String.format("%s%n", product));
                break;
            case "Coop":
                lstCoop.append(String.format("%s%n", product));
                break;
            case "City Gross":
                lstCG.append(String.format("%s%n", product));
                break;

        }


    }
    public void updateConsumerStatus(String status, String consumerName) {

        switch (consumerName) {
            case "ICA":
                lblIcaStatus.setText(String.format("Status: %s", status));
                break;
            case "Coop":
                lblCoopStatus.setText(String.format("Status: %s", status));
                break;
            case "City Gross":
                lblCGStatus.setText(String.format("Status: %s", status));
                break;
        }

    }

    public boolean getContinueLoading(String consumerName) {
        switch (consumerName) {
            case "ICA":
                return chkIcaCont.isSelected();
           case "Coop":
                return chkCoopCont.isSelected();
            case "City Gross":
                return chkCGCont.isSelected();
            default:
                return false;
        }
    }


}
