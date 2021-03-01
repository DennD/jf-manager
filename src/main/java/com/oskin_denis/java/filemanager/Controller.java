package com.oskin_denis.java.filemanager;


import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller {

    @FXML
    VBox rightPanel, leftPanel;

    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void copyBtnAction(ActionEvent actionEvent){

        PanelController leftPC = (PanelController) leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController) rightPanel.getProperties().get("ctrl");

        if(leftPC.getSelectedFilename() == null && rightPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ни один файл не выбран", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Path srcPath = null;
        Path dstPath = null;

        PanelController srcPC = null, dstPC = null;
        if (leftPC.getSelectedFilename() != null){
            srcPC = leftPC;
            dstPC = rightPC;
        }

        if(rightPC.getSelectedFilename() != null){
            srcPC = rightPC;
            dstPC = leftPC;
        }

            srcPath = Paths.get(srcPC.getCurrentPath(),srcPC.getSelectedFilename());
            dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());


        try {
            Files.copy(srcPath,dstPath);
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось скопировать указанный файл", ButtonType.OK);
            alert.showAndWait();
        }


    }

    public void deleteBtnAction(ActionEvent actionEvent) {

        PanelController leftPC = (PanelController) leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController) rightPanel.getProperties().get("ctrl");

        if(leftPC.getSelectedFilename() == null && rightPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ни один файл не выбран", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Path srcPath = null;

        PanelController srcPC = null;
        PanelController dstPC = null;

        if (leftPC.getSelectedFilename() != null){
            srcPC = leftPC;
            dstPC = rightPC;
        }

        if(rightPC.getSelectedFilename() != null){
            srcPC = rightPC;
            dstPC = leftPC;
        }

        srcPath = Paths.get(srcPC.getCurrentPath(),srcPC.getSelectedFilename());



        try {
            Files.delete(srcPath);
            srcPC.updateList(Paths.get(srcPC.getCurrentPath()));
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось удалить указанный файл", ButtonType.OK);
            alert.showAndWait();
        }


    }

    public void moveBtnAction(ActionEvent actionEvent) {

        PanelController leftPC = (PanelController) leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController) rightPanel.getProperties().get("ctrl");

        if(leftPC.getSelectedFilename() == null && rightPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ни один файл не выбран", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Path srcPath = null;
        Path dstPath = null;

        PanelController srcPC = null, dstPC = null;
        if (leftPC.getSelectedFilename() != null){
            srcPC = leftPC;
            dstPC = rightPC;
        }

        if(rightPC.getSelectedFilename() != null){
            srcPC = rightPC;
            dstPC = leftPC;
        }

        srcPath = Paths.get(srcPC.getCurrentPath(),srcPC.getSelectedFilename());
        dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());


        try {
            Files.move(srcPath,dstPath);
            srcPC.updateList(Paths.get(srcPC.getCurrentPath()));
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось переместить указанный файл", ButtonType.OK);
            alert.showAndWait();
        }

    }
}
