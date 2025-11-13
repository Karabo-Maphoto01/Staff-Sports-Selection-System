# **Sports-Selection-System**

A comprehensive Swing-based Java application for managing staff sports preferences across different categories.


# **Overview**

The Sports Selection System allows staff members to select their preferred sports categories from three main types: Individual, Partner, and Team sports. The system tracks selections and provides real-time summary statistics.


# **Features**


*Core Features*

- Category Classification: Sports organized into Individual, Partner, and Team categories

- Staff Information: Capture department and name details

- Multiple Selection: Allow selection of up to 2 sport categories

- Real-time Summary: Dynamic count display for each category

- Data Persistence: Store selections in memory using ArrayList


*Additional Features*

- Input Validation: Ensures staff name is provided before submission

- Selection Limits: Enforces maximum of 2 category selections per staff member

- User Feedback: Informative error and confirmation messages

- Default Settings: Pre-configured with common defaults

- Professional UI: Clean, organized interface with proper spacing and borders


# **System Architecture**

*Data Structure*

- ArrayList<String[]>: Stores staff selections with department, name, and category preferences

- HashMap<String, Integer>: Tracks cumulative counts for each category

- String Arrays: Pre-defined sports lists for each category

*GUI Components*
- JFrame: Main application window

- JTextField: Department name input

- JTextField: Staff name input

- JCheckBox: Sport category selection

- JButton: Submission control

-JLabel: Informational text displays


