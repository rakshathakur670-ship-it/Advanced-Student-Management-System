package AdvancedStudentManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdvancedStudentManagement extends JFrame {

    private static final long serialVersionUID = 1L;

    // =========================
    // STUDENT CLASS
    // =========================

    static class Student implements Serializable {

        private static final long serialVersionUID = 1L;

        int id;
        String name;
        String email;
        String phone;
        String course;
        String semester;
        double marks;
        double attendance;

        Student(int id, String name, String email, String phone,
                String course, String semester,
                double marks, double attendance) {

            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.course = course;
            this.semester = semester;
            this.marks = marks;
            this.attendance = attendance;
        }
    }

    // =========================
    // DATA
    // =========================

    static ArrayList<Student> students = new ArrayList<>();

    static final String DATA_FILE = "students.dat";

    static int nextId = 1001;

    // =========================
    // COLORS
    // =========================

    Color dark = new Color(31, 41, 55);
    Color sidebar = new Color(17, 24, 39);
    Color blue = new Color(37, 99, 235);
    Color green = new Color(22, 163, 74);
    Color red = new Color(220, 38, 38);
    Color purple = new Color(124, 58, 237);
    Color background = new Color(243, 244, 246);

    // =========================
    // COMPONENTS
    // =========================

    JPanel contentPanel;

    JLabel totalStudents;
    JLabel averageMarks;
    JLabel averageAttendance;

    JTable table;
    DefaultTableModel tableModel;

    JTextField searchField;

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            loadData();

            showLogin();

        });
    }

    // =========================
    // LOGIN
    // =========================

    static void showLogin() {

        JFrame login = new JFrame("Advanced Student Management - Login");

        login.setSize(450, 350);

        login.setLocationRelativeTo(null);

        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        login.setResizable(false);

        JPanel main = new JPanel(new GridBagLayout());

        main.setBackground(new Color(31, 41, 55));

        JPanel box = new JPanel();

        box.setPreferredSize(new Dimension(340, 260));

        box.setBackground(Color.WHITE);

        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        box.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("STUDENT MANAGEMENT");

        title.setFont(new Font("Arial", Font.BOLD, 20));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Administrator Login");

        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField();

        JPasswordField password = new JPasswordField();

        username.setMaximumSize(new Dimension(280, 40));

        password.setMaximumSize(new Dimension(280, 40));

        username.setBorder(
                BorderFactory.createTitledBorder("Username")
        );

        password.setBorder(
                BorderFactory.createTitledBorder("Password")
        );

        JButton loginButton = new JButton("LOGIN");

        loginButton.setMaximumSize(new Dimension(280, 40));

        loginButton.setBackground(new Color(37, 99, 235));

        loginButton.setForeground(Color.WHITE);

        loginButton.setFocusPainted(false);

        box.add(title);

        box.add(Box.createVerticalStrut(5));

        box.add(subtitle);

        box.add(Box.createVerticalStrut(20));

        box.add(username);

        box.add(Box.createVerticalStrut(10));

        box.add(password);

        box.add(Box.createVerticalStrut(15));

        box.add(loginButton);

        main.add(box);

        login.add(main);

        loginButton.addActionListener(e -> {

            String user = username.getText();

            String pass =
                    new String(password.getPassword());

            if (user.equals("admin")
                    && pass.equals("admin123")) {

                login.dispose();

                new AdvancedStudentManagement()
                        .setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        login,
                        "Invalid username or password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        login.setVisible(true);
    }

    // =========================
    // CONSTRUCTOR
    // =========================

    public AdvancedStudentManagement() {

        setTitle("Advanced Student Management System");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        createHeader();

        createSidebar();

        contentPanel = new JPanel(
                new BorderLayout()
        );

        contentPanel.setBackground(background);

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        showDashboard();
    }

    // =========================
    // HEADER
    // =========================

    void createHeader() {

        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setBackground(dark);

        header.setPreferredSize(
                new Dimension(0, 70)
        );

        JLabel title =
                new JLabel(
                        "  ADVANCED STUDENT MANAGEMENT SYSTEM"
                );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        JLabel admin =
                new JLabel(
                        "Administrator   "
                );

        admin.setForeground(Color.WHITE);

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                admin,
                BorderLayout.EAST
        );

        add(
                header,
                BorderLayout.NORTH
        );
    }

    // =========================
    // SIDEBAR
    // =========================

    void createSidebar() {

        JPanel panel = new JPanel();

        panel.setBackground(sidebar);

        panel.setPreferredSize(
                new Dimension(210, 0)
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel menu =
                new JLabel("MENU");

        menu.setForeground(Color.WHITE);

        menu.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        menu.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        panel.add(menu);

        JButton dashboard =
                menuButton("Dashboard");

        JButton students =
                menuButton("Students");

        JButton add =
                menuButton("Add Student");

        JButton attendance =
                menuButton("Attendance");

        JButton marks =
                menuButton("Marks & Grades");

        JButton reports =
                menuButton("Reports");

        JButton exit =
                menuButton("Exit");

        panel.add(dashboard);

        panel.add(students);

        panel.add(add);

        panel.add(attendance);

        panel.add(marks);

        panel.add(reports);

        panel.add(
                Box.createVerticalGlue()
        );

        panel.add(exit);

        add(
                panel,
                BorderLayout.WEST
        );

        dashboard.addActionListener(
                e -> showDashboard()
        );

        students.addActionListener(
                e -> showStudents()
        );

        add.addActionListener(
                e -> addStudent()
        );

        attendance.addActionListener(
                e -> showAttendance()
        );

        marks.addActionListener(
                e -> showMarks()
        );

        reports.addActionListener(
                e -> showReports()
        );

        exit.addActionListener(e -> {

            saveData();

            System.exit(0);

        });
    }

    // =========================
    // MENU BUTTON
    // =========================

    JButton menuButton(String text) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(210, 45)
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setBackground(sidebar);

        button.setForeground(Color.WHITE);

        button.setBorderPainted(false);

        button.setFocusPainted(false);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        10
                )
        );

        return button;
    }

    // =========================
    // DASHBOARD
    // =========================

    void showDashboard() {

        contentPanel.removeAll();

        JPanel panel = new JPanel();

        panel.setBackground(background);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Dashboard");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        20,
                        10
                )
        );

        panel.add(title);

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                20
                        )
                );

        cards.setBackground(background);

        cards.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        25,
                        25
                )
        );

        JPanel card1 =
                createCard(
                        "TOTAL STUDENTS",
                        blue
                );

        JPanel card2 =
                createCard(
                        "AVERAGE MARKS",
                        green
                );

        JPanel card3 =
                createCard(
                        "AVERAGE ATTENDANCE",
                        purple
                );

        totalStudents =
                (JLabel) card1.getClientProperty(
                        "value"
                );

        averageMarks =
                (JLabel) card2.getClientProperty(
                        "value"
                );

        averageAttendance =
                (JLabel) card3.getClientProperty(
                        "value"
                );

        cards.add(card1);

        cards.add(card2);

        cards.add(card3);

        panel.add(cards);

        JPanel welcome =
                new JPanel(
                        new BorderLayout()
                );

        welcome.setBackground(Color.WHITE);

        welcome.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        JLabel message =
                new JLabel(
                        "<html>" +
                        "<h2>Welcome to Student Management System</h2>" +
                        "<p>Manage students, attendance, marks and reports.</p>" +
                        "<p>All data is automatically stored locally.</p>" +
                        "</html>"
                );

        welcome.add(
                message,
                BorderLayout.CENTER
        );

        panel.add(welcome);

        contentPanel.add(panel);

        updateDashboard();

        refresh();
    }

    // =========================
    // CARD
    // =========================

    JPanel createCard(
            String title,
            Color color
    ) {

        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                color,
                                2
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel heading =
                new JLabel(title);

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JLabel value =
                new JLabel("0");

        value.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        value.setForeground(color);

        card.add(heading);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(value);

        card.putClientProperty(
                "value",
                value
        );

        return card;
    }

    // =========================
    // STUDENTS
    // =========================

    void showStudents() {

        contentPanel.removeAll();

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(background);

        JLabel title =
                new JLabel("Student Records");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        title.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        10,
                        10
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel toolbar =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        toolbar.setBackground(background);

        searchField =
                new JTextField(20);

        JButton search =
                new JButton("Search");

        JButton add =
                new JButton("Add");

        JButton update =
                new JButton("Update");

        JButton delete =
                new JButton("Delete");

        toolbar.add(
                new JLabel("Search:")
        );

        toolbar.add(searchField);

        toolbar.add(search);

        toolbar.add(add);

        toolbar.add(update);

        toolbar.add(delete);

        panel.add(
                toolbar,
                BorderLayout.CENTER
        );

        String[] columns = {
                "ID",
                "Name",
                "Email",
                "Phone",
                "Course",
                "Semester",
                "Marks",
                "Attendance"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        table =
                new JTable(tableModel);

        table.setRowHeight(28);

        panel.add(
                new JScrollPane(table),
                BorderLayout.SOUTH
        );

        contentPanel.add(panel);

        loadTable(students);

        search.addActionListener(
                e -> searchStudents()
        );

        add.addActionListener(
                e -> addStudent()
        );

        update.addActionListener(
                e -> updateStudent()
        );

        delete.addActionListener(
                e -> deleteStudent()
        );

        refresh();
    }

    // =========================
    // TABLE
    // =========================

    void loadTable(
            List<Student> list
    ) {

        if (tableModel == null)
            return;

        tableModel.setRowCount(0);

        for (Student s : list) {

            tableModel.addRow(
                    new Object[]{
                            s.id,
                            s.name,
                            s.email,
                            s.phone,
                            s.course,
                            s.semester,
                            String.format(
                                    "%.2f",
                                    s.marks
                            ),
                            String.format(
                                    "%.2f%%",
                                    s.attendance
                            )
                    }
            );
        }
    }

    // =========================
    // ADD STUDENT
    // =========================

    void addStudent() {

        JTextField name =
                new JTextField();

        JTextField email =
                new JTextField();

        JTextField phone =
                new JTextField();

        JTextField course =
                new JTextField();

        JTextField semester =
                new JTextField();

        JTextField marks =
                new JTextField();

        JTextField attendance =
                new JTextField();

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                7,
                                2,
                                10,
                                10
                        )
                );

        panel.add(
                new JLabel("Name:")
        );

        panel.add(name);

        panel.add(
                new JLabel("Email:")
        );

        panel.add(email);

        panel.add(
                new JLabel("Phone:")
        );

        panel.add(phone);

        panel.add(
                new JLabel("Course:")
        );

        panel.add(course);

        panel.add(
                new JLabel("Semester:")
        );

        panel.add(semester);

        panel.add(
                new JLabel("Marks:")
        );

        panel.add(marks);

        panel.add(
                new JLabel("Attendance:")
        );

        panel.add(attendance);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Add Student",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result != JOptionPane.OK_OPTION)
            return;

        try {

            if (name.getText().trim().isEmpty())
                throw new Exception(
                        "Name is required."
                );

            if (email.getText().trim().isEmpty())
                throw new Exception(
                        "Email is required."
                );

            double marksValue =
                    Double.parseDouble(
                            marks.getText()
                    );

            double attendanceValue =
                    Double.parseDouble(
                            attendance.getText()
                    );

            if (
                    marksValue < 0 ||
                    marksValue > 100
            )
                throw new Exception(
                        "Marks must be between 0 and 100."
                );

            if (
                    attendanceValue < 0 ||
                    attendanceValue > 100
            )
                throw new Exception(
                        "Attendance must be between 0 and 100."
                );

            Student student =
                    new Student(
                            nextId++,
                            name.getText(),
                            email.getText(),
                            phone.getText(),
                            course.getText(),
                            semester.getText(),
                            marksValue,
                            attendanceValue
                    );

            students.add(student);

            saveData();

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
            );

            if (tableModel != null)
                loadTable(students);

            updateDashboard();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid numbers for marks and attendance."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // =========================
    // UPDATE
    // =========================

    void updateStudent() {

        if (table == null)
            return;

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first."
            );

            return;
        }

        int id =
                (int) tableModel.getValueAt(
                        row,
                        0
                );

        Student s =
                findStudent(id);

        if (s == null)
            return;

        JTextField name =
                new JTextField(s.name);

        JTextField email =
                new JTextField(s.email);

        JTextField phone =
                new JTextField(s.phone);

        JTextField course =
                new JTextField(s.course);

        JTextField semester =
                new JTextField(s.semester);

        JTextField marks =
                new JTextField(
                        String.valueOf(s.marks)
                );

        JTextField attendance =
                new JTextField(
                        String.valueOf(
                                s.attendance
                        )
                );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                7,
                                2,
                                10,
                                10
                        )
                );

        panel.add(new JLabel("Name:"));
        panel.add(name);

        panel.add(new JLabel("Email:"));
        panel.add(email);

        panel.add(new JLabel("Phone:"));
        panel.add(phone);

        panel.add(new JLabel("Course:"));
        panel.add(course);

        panel.add(new JLabel("Semester:"));
        panel.add(semester);

        panel.add(new JLabel("Marks:"));
        panel.add(marks);

        panel.add(new JLabel("Attendance:"));
        panel.add(attendance);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Update Student",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (result != JOptionPane.OK_OPTION)
            return;

        try {

            s.name = name.getText();

            s.email = email.getText();

            s.phone = phone.getText();

            s.course = course.getText();

            s.semester = semester.getText();

            s.marks =
                    Double.parseDouble(
                            marks.getText()
                    );

            s.attendance =
                    Double.parseDouble(
                            attendance.getText()
                    );

            saveData();

            loadTable(students);

            updateDashboard();

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input."
            );
        }
    }

    // =========================
    // DELETE
    // =========================

    void deleteStudent() {

        if (table == null)
            return;

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student."
            );

            return;
        }

        int id =
                (int) tableModel.getValueAt(
                        row,
                        0
                );

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected student?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm ==
                JOptionPane.YES_OPTION
        ) {

            students.removeIf(
                    s -> s.id == id
            );

            saveData();

            loadTable(students);

            updateDashboard();

            JOptionPane.showMessageDialog(
                    this,
                    "Student deleted."
            );
        }
    }

    // =========================
    // SEARCH
    // =========================

    void searchStudents() {

        String keyword =
                searchField.getText()
                        .trim()
                        .toLowerCase();

        if (keyword.isEmpty()) {

            loadTable(students);

            return;
        }

        ArrayList<Student> result =
                new ArrayList<>();

        for (Student s : students) {

            if (
                    String.valueOf(s.id)
                            .contains(keyword)

                    || s.name
                            .toLowerCase()
                            .contains(keyword)

                    || s.email
                            .toLowerCase()
                            .contains(keyword)

                    || s.course
                            .toLowerCase()
                            .contains(keyword)
            ) {

                result.add(s);
            }
        }

        loadTable(result);
    }

    // =========================
    // ATTENDANCE
    // =========================

    void showAttendance() {

        contentPanel.removeAll();

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(background);

        JLabel title =
                new JLabel(
                        "Attendance Management"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        title.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        String[] columns = {
                "ID",
                "Student",
                "Attendance",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                );

        for (Student s : students) {

            String status =
                    s.attendance >= 75
                            ? "Eligible"
                            : "Short Attendance";

            model.addRow(
                    new Object[]{
                            s.id,
                            s.name,
                            String.format(
                                    "%.2f%%",
                                    s.attendance
                            ),
                            status
                    }
            );
        }

        JTable attendanceTable =
                new JTable(model);

        attendanceTable.setRowHeight(30);

        panel.add(
                new JScrollPane(
                        attendanceTable
                ),
                BorderLayout.CENTER
        );

        contentPanel.add(panel);

        refresh();
    }

    // =========================
    // MARKS
    // =========================

    void showMarks() {

        contentPanel.removeAll();

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(background);

        JLabel title =
                new JLabel(
                        "Marks & Grades"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        title.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        String[] columns = {
                "ID",
                "Student",
                "Marks",
                "Grade",
                "Performance"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                );

        for (Student s : students) {

            String grade =
                    calculateGrade(
                            s.marks
                    );

            String performance;

            if (s.marks >= 80)
                performance = "Excellent";

            else if (s.marks >= 60)
                performance = "Good";

            else if (s.marks >= 40)
                performance = "Pass";

            else
                performance = "Fail";

            model.addRow(
                    new Object[]{
                            s.id,
                            s.name,
                            s.marks,
                            grade,
                            performance
                    }
            );
        }

        JTable marksTable =
                new JTable(model);

        marksTable.setRowHeight(30);

        panel.add(
                new JScrollPane(
                        marksTable
                ),
                BorderLayout.CENTER
        );

        contentPanel.add(panel);

        refresh();
    }

    // =========================
    // GRADE
    // =========================

    String calculateGrade(
            double marks
    ) {

        if (marks >= 90)
            return "A+";

        if (marks >= 80)
            return "A";

        if (marks >= 70)
            return "B";

        if (marks >= 60)
            return "C";

        if (marks >= 50)
            return "D";

        if (marks >= 40)
            return "E";

        return "F";
    }

    // =========================
    // REPORT
    // =========================

    void showReports() {

        contentPanel.removeAll();

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(background);

        JLabel title =
                new JLabel(
                        "Student Reports"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        title.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JTextArea report =
                new JTextArea();

        report.setEditable(false);

        report.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        StringBuilder data =
                new StringBuilder();

        data.append(
                "===============================================\n"
        );

        data.append(
                "        STUDENT MANAGEMENT REPORT\n"
        );

        data.append(
                "===============================================\n\n"
        );

        data.append(
                "Total Students     : "
        );

        data.append(
                students.size()
        );

        data.append("\n");

        data.append(
                "Average Marks      : "
        );

        data.append(
                String.format(
                        "%.2f%%",
                        getAverageMarks()
                )
        );

        data.append("\n");

        data.append(
                "Average Attendance : "
        );

        data.append(
                String.format(
                        "%.2f%%",
                        getAverageAttendance()
                )
        );

        data.append(
                "\n\n"
        );

        data.append(
                "-----------------------------------------------\n"
        );

        for (Student s : students) {

            data.append(
                    "ID         : "
            );

            data.append(s.id);

            data.append("\n");

            data.append(
                    "Name       : "
            );

            data.append(s.name);

            data.append("\n");

            data.append(
                    "Course     : "
            );

            data.append(s.course);

            data.append("\n");

            data.append(
                    "Marks      : "
            );

            data.append(s.marks);

            data.append("\n");

            data.append(
                    "Attendance : "
            );

            data.append(s.attendance);

            data.append("%\n");

            data.append(
                    "Grade      : "
            );

            data.append(
                    calculateGrade(
                            s.marks
                    )
            );

            data.append(
                    "\n"
            );

            data.append(
                    "-----------------------------------------------\n"
            );
        }

        report.setText(
                data.toString()
        );

        panel.add(
                new JScrollPane(report),
                BorderLayout.CENTER
        );

        JButton save =
                new JButton(
                        "Save Report"
                );

        save.addActionListener(
                e -> saveReport(
                        data.toString()
                )
        );

        panel.add(
                save,
                BorderLayout.SOUTH
        );

        contentPanel.add(panel);

        refresh();
    }

    // =========================
    // SAVE REPORT
    // =========================

    void saveReport(
            String content
    ) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "student_report.txt"
                    );

            writer.write(content);

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Report saved successfully."
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving report."
            );
        }
    }

    // =========================
    // FIND STUDENT
    // =========================

    Student findStudent(
            int id
    ) {

        for (Student s : students) {

            if (s.id == id)
                return s;
        }

        return null;
    }

    // =========================
    // AVERAGE MARKS
    // =========================

    double getAverageMarks() {

        if (students.isEmpty())
            return 0;

        double total = 0;

        for (Student s : students)
            total += s.marks;

        return total / students.size();
    }

    // =========================
    // AVERAGE ATTENDANCE
    // =========================

    double getAverageAttendance() {

        if (students.isEmpty())
            return 0;

        double total = 0;

        for (Student s : students)
            total += s.attendance;

        return total / students.size();
    }

    // =========================
    // UPDATE DASHBOARD
    // =========================

    void updateDashboard() {

        if (totalStudents != null) {

            totalStudents.setText(
                    String.valueOf(
                            students.size()
                    )
            );
        }

        if (averageMarks != null) {

            averageMarks.setText(
                    String.format(
                            "%.2f%%",
                            getAverageMarks()
                    )
            );
        }

        if (averageAttendance != null) {

            averageAttendance.setText(
                    String.format(
                            "%.2f%%",
                            getAverageAttendance()
                    )
            );
        }
    }

    // =========================
    // SAVE DATA
    // =========================

    static void saveData() {

        try {

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    DATA_FILE
                            )
                    );

            output.writeObject(
                    students
            );

            output.writeInt(
                    nextId
            );

            output.close();

        } catch (IOException e) {

            System.out.println(
                    "Unable to save data."
            );
        }
    }

    // =========================
    // LOAD DATA
    // =========================

    @SuppressWarnings("unchecked")
    static void loadData() {

        File file =
                new File(DATA_FILE);

        if (!file.exists())
            return;

        try {

            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(
                                    file
                            )
                    );

            students =
                    (ArrayList<Student>)
                            input.readObject();

            nextId =
                    input.readInt();

            input.close();

        } catch (Exception e) {

            students =
                    new ArrayList<>();

            nextId = 1001;
        }
    }

    // =========================
    // REFRESH
    // =========================

    void refresh() {

        contentPanel.revalidate();

        contentPanel.repaint();
    }
}