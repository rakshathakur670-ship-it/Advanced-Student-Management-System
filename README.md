## How to Run

### Method 1: Run using Eclipse

1. Open **Eclipse IDE**.
2. Import or open the `studentmanagement` project.
3. In the Project Explorer, open:

```text
src
└── AdvancedStudentManagement
    └── AdvancedStudentManagement.java
```

4. Right-click `AdvancedStudentManagement.java`.
5. Select:

```text
Run As → Java Application
```

6. The login window will appear.

Use the demo credentials:

```text
Username: admin
Password: admin123
```

7. After successful login, the Student Management dashboard will open.

### Method 2: Run using Command Prompt

Open Command Prompt in the project directory.

Go to the source folder:

```bash
cd C:\Users\rakshathakur\git\repository\studentmanagement\src
```

Compile the program:

```bash
javac AdvancedStudentManagement\AdvancedStudentManagement.java
```

Run the program:

```bash
java AdvancedStudentManagement.AdvancedStudentManagement
```

### Method 3: Run directly from the GitHub project

Clone the repository:

```bash
git clone https://github.com/rakshathakur670-ship-it/Advanced-Student-Management-System.git
```

Open the downloaded project in Eclipse.

Then run:

```text
AdvancedStudentManagement.java
→ Right Click
→ Run As
→ Java Application
```

### Login

The application currently uses demo administrator credentials:

```text
Username: admin
Password: admin123
```

### Data Files

After running the application, the following files may be generated in the project working directory:

```text
students.dat
student_report.txt
```

`students.dat` stores student information locally using Java serialization.

`student_report.txt` contains generated student reports.

These runtime files are excluded from GitHub using `.gitignore`.

### Troubleshooting

If Eclipse shows:

```text
The serializable class ... does not declare a static final serialVersionUID
```

make sure the classes that implement `Serializable` contain a `serialVersionUID`, for example:

```java
private static final long serialVersionUID = 1L;
```

If you get:

```text
Could not find or load main class
```

make sure you are running the class:

```text
AdvancedStudentManagement
```

and that the package declaration matches:

```java
package AdvancedStudentManagement;
```

If the application starts but previous students are missing, check whether `students.dat` exists in the application's working directory.
