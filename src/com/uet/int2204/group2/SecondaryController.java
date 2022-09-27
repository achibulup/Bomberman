package com.uet.int2204.group2;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        Bomberman.setRoot("primary");
    }
}