package com.github.yaseen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

    @FXML private Label titleLabel;
    @FXML private TextField taskName;
    @FXML private DatePicker dueDate;
    @FXML private TextField additionalInfo;
    @FXML private TableView<Task> tableView;
    @FXML private TableColumn<Task, String> task;
    @FXML private TableColumn<Task, String> dateCreated;
    @FXML private TableColumn<Task, String> dateDue;
    @FXML private TableColumn<Task, String> addiInfo;


    private ToDo toDo = new ToDo();
    private ObservableList<Task> taskOL = FXCollections.observableArrayList();

    private static final String filePath = "TodoList.txt";


    public void addTask() {
        toDo.addTask(new Task(taskName.getText(), dueDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                additionalInfo.getText()));
        taskOL.setAll(toDo.getTasks());
        tableView.setItems(taskOL);
        saveFile();
        taskName.setText("");
        additionalInfo.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        task.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        dateDue.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        addiInfo.setCellValueFactory(new PropertyValueFactory<>("additionalInfo"));
        editableCols();

        toDo = loadFile();
        loadToDO();
        taskDone();
    }

    public void taskDone() {
        tableView.setRowFactory( tv -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) && event.isShiftDown() ) {
                    Task rowData = row.getItem();
                    toDo.addTaskComp(rowData);
                    toDo.removeTask(rowData);
                    taskOL.setAll(toDo.getTasks());
                    tableView.setItems(taskOL);
                    saveFile();
                    System.out.println("Deleted");
                }
            });
            return row ;
        });
    }

    private void editableCols() {
        task.setCellFactory(TextFieldTableCell.forTableColumn());
        task.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setTaskName(e.getNewValue()));

        dateDue.setCellFactory(TextFieldTableCell.forTableColumn());
        dateDue.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setDueDate(e.getNewValue()));

        addiInfo.setCellFactory(TextFieldTableCell.forTableColumn());
        addiInfo.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setAdditionalInfo(e.getNewValue()));
    }


    public void loadToDO() {
        titleLabel.setText("To-do");
        taskOL.setAll(toDo.getTasks());
        tableView.setItems(taskOL);
    }

    public void loadDone() {
        titleLabel.setText("Completed task");
        taskOL.setAll(toDo.getTasksComp());
        System.out.println(toDo.getTasksComp().get(0).getTaskName());
        tableView.setItems(taskOL);
    }

    public void saveFile() {
        saveFile(toDo);
    }

    public static void saveFile(ToDo toDo) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toDo);
            fos.close();
            oos.close();
            System.out.println("Saved");
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public static ToDo loadFile() {
        try(FileInputStream fis = new FileInputStream(filePath)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            ToDo toDo = (ToDo) ois.readObject();
            fis.close();
            ois.close();
            System.out.println("Save file used");
            return toDo;
        } catch (ClassNotFoundException | IOException ex) {
            saveFile(new ToDo());
            return new ToDo();
        }
    }

    public void homeBtnClicked() throws IOException {
        App.setRoot("start");
    }
}
