/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package staffsports;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Karabo
 */
public class SportsSelectionSystem extends JFrame {
    
     // Data storage
    private ArrayList<String[]> staffSelections;
    private HashMap<String, Integer> categoryCounts;
    
    // UI Components
    //private JComboBox<String> ;
    private JTextField nameField, nameDept;
    private JCheckBox individualCheckBox, partnerCheckBox, teamCheckBox;
    private JButton okButton;
    private JTextField individualCountField, partnerCountField, teamCountField;
  
    public SportsSelectionSystem() {
        initializeData();
        initializeUI();
        setupEventHandlers();
    }
    
    private void initializeData() {
        staffSelections = new ArrayList<>();
        categoryCounts = new HashMap<>();
        categoryCounts.put("Individual", 0);
        categoryCounts.put("Partner", 0);
        categoryCounts.put("Team", 0);
    }
    
    private void initializeUI() {
        setTitle("Staff Sports Selection System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Selection panel
        mainPanel.add(createSelectionPanel(), BorderLayout.CENTER);
        
        // Summary panel
        mainPanel.add(createSummaryPanel(), BorderLayout.SOUTH);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        headerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), 
            "Staff Sports Selection Module", 
            TitledBorder.CENTER, 
            TitledBorder.TOP
        ));
        
        // Department selection
        JPanel deptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deptPanel.add(new JLabel("Dept:"));
        nameDept = new JTextField(" ", 15);
        deptPanel.add(nameDept);
        
        
        // Name field
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Name:"));
        nameField = new JTextField(" ", 15);
        namePanel.add(nameField);
        
        headerPanel.add(deptPanel);
        headerPanel.add(namePanel);
        
        return headerPanel;
    }
    
    private JPanel createSelectionPanel() {
       JPanel selectionPanel = new JPanel(new BorderLayout(10, 10));
       selectionPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "----Please choose a maximum of two options---"   
        )); 
        
        // Checkbox panel
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        
        individualCheckBox = new JCheckBox("Individual");
        partnerCheckBox = new JCheckBox("Partner");
        teamCheckBox = new JCheckBox("Team");
        
        
        checkboxPanel.add(individualCheckBox);
        checkboxPanel.add(partnerCheckBox);
        checkboxPanel.add(teamCheckBox);
        
        // OK button
        okButton = new JButton("OK");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        
        selectionPanel.add(checkboxPanel, BorderLayout.CENTER);
        selectionPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return selectionPanel;
    }
    
    private JPanel createSummaryPanel() {
        JPanel summaryPanel = new JPanel(new BorderLayout(10, 10));
        summaryPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Summary of Selections so far..."
            
        ));
        
        JPanel countPanel = new JPanel(new GridLayout(1, 3, 10, 5));
        
        // Individual count
        JPanel individualPanel = new JPanel(new BorderLayout());
        individualPanel.add(new JLabel("Individual", JLabel.CENTER), BorderLayout.NORTH);
        individualCountField = new JTextField(" ", 5);
        individualCountField.setEditable(false);
        individualCountField.setHorizontalAlignment(JTextField.CENTER);
        individualPanel.add(individualCountField, BorderLayout.CENTER);
        
        // Partner count
        JPanel partnerPanel = new JPanel(new BorderLayout());
        partnerPanel.add(new JLabel("Partner", JLabel.CENTER), BorderLayout.NORTH);
        partnerCountField = new JTextField(" ", 5);
        partnerCountField.setEditable(false);
        partnerCountField.setHorizontalAlignment(JTextField.CENTER);
        partnerPanel.add(partnerCountField, BorderLayout.CENTER);
        
        // Team count
        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.add(new JLabel("Team", JLabel.CENTER), BorderLayout.NORTH);
        teamCountField = new JTextField(" ", 5);
        teamCountField.setEditable(false);
        teamCountField.setHorizontalAlignment(JTextField.CENTER);
        teamPanel.add(teamCountField, BorderLayout.CENTER);
        
        countPanel.add(individualPanel);
        countPanel.add(partnerPanel);
        countPanel.add(teamPanel);
        
        summaryPanel.add(countPanel, BorderLayout.CENTER);
        
        return summaryPanel;
    }
    
    private void setupEventHandlers() {
        // Checkbox selection limit - maximum 2 selections
        ItemListener checkboxListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int selectedCount = getSelectedCheckboxCount();
                if (selectedCount > 2) {
                    // Deselect the current checkbox if more than 2 are selected
                    JCheckBox source = (JCheckBox) e.getSource();
                    source.setSelected(false);
                    JOptionPane.showMessageDialog(null,
                        "Maximum of 2 selections allowed!", 
                        "Selection Limit", 
                        JOptionPane.WARNING_MESSAGE);
                    }
            }
        };
        
        individualCheckBox.addItemListener(checkboxListener);
        partnerCheckBox.addItemListener(checkboxListener);
        teamCheckBox.addItemListener(checkboxListener);
        
        // OK button action
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSelection();
            }
        });
    }
    
    private int getSelectedCheckboxCount() {
        int count = 0;
        if (individualCheckBox.isSelected()) count++;
        if (partnerCheckBox.isSelected()) count++;
        if (teamCheckBox.isSelected()) count++;
        return count;
    }
    
    private void processSelection() {
        String name = nameField.getText().trim();
        String department = (String) nameDept.getText().trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter your name!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int selectedCount = getSelectedCheckboxCount();
        if (selectedCount == 0) {
            JOptionPane.showMessageDialog(this, 
                "Please select at least one sport category!", 
                "Selection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Store the selection
        String[] selection = new String[5]; // dept, name, individual, partner, team
        selection[0] = department;
        selection[1] = name;
        selection[2] = individualCheckBox.isSelected() ? "Yes" : "No";
        selection[3] = partnerCheckBox.isSelected() ? "Yes" : "No";
        selection[4] = teamCheckBox.isSelected() ? "Yes" : "No";
        
        staffSelections.add(selection);
        
        // Update category counts
        if (individualCheckBox.isSelected()) {
            categoryCounts.put("Individual", categoryCounts.get("Individual") + 1);
        }
        if (partnerCheckBox.isSelected()) {
            categoryCounts.put("Partner", categoryCounts.get("Partner") + 1);
        }
        if (teamCheckBox.isSelected()) {
            categoryCounts.put("Team", categoryCounts.get("Team") + 1);
        }
        
        // Update summary display
        updateSummaryDisplay();
        
        // Show confirmation
        StringBuilder message = new StringBuilder();
        message.append("Selection recorded for ").append(name).append("!\n");
        message.append("Department: ").append(department).append("\n");
        message.append("Selected categories: ");
        
        if (individualCheckBox.isSelected()) message.append("Individual ");
        if (partnerCheckBox.isSelected()) message.append("Partner ");
        if (teamCheckBox.isSelected()) message.append("Team ");
        
        JOptionPane.showMessageDialog(this, 
            message.toString(), 
            "Selection Confirmed", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Reset checkboxes for next entry 
        individualCheckBox.setSelected(false);
        partnerCheckBox.setSelected(true);
        teamCheckBox.setSelected(false);
        
        nameField.requestFocus();
    }
    
    private void updateSummaryDisplay() {
        individualCountField.setText(String.valueOf(categoryCounts.get("Individual")));
        partnerCountField.setText(String.valueOf(categoryCounts.get("Partner")));
        teamCountField.setText(String.valueOf(categoryCounts.get("Team")));
    }
    
    // Method to get detailed report 
    public String generateDetailedReport() {
        StringBuilder report = new StringBuilder();
        report.append("Staff Sports Selection Report\n");
        report.append("=============================\n\n");
        
        for (String[] selection : staffSelections) {
            report.append("Department: ").append(selection[0]).append("\n");
            report.append("Name: ").append(selection[1]).append("\n");
            report.append("Individual: ").append(selection[2]).append("\n");
            report.append("Partner: ").append(selection[3]).append("\n");
            report.append("Team: ").append(selection[4]).append("\n");
            report.append("---\n");
        }
        
        report.append("\nSummary:\n");
        report.append("Individual: ").append(categoryCounts.get("Individual")).append("\n");
        report.append("Partner: ").append(categoryCounts.get("Partner")).append("\n");
        report.append("Team: ").append(categoryCounts.get("Team")).append("\n");
        
        return report.toString();
    }

}
