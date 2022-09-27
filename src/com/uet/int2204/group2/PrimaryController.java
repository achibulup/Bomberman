package com.uet.int2204.group2;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Bomberman.setRoot("secondary");
    }
}
