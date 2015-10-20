/*§
  ===========================================================================
  Chronos IDE
  ===========================================================================
  Copyright (C) 2015 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.chronos.ide;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

import java.io.IOException;

class AboutBox extends Alert {
    public AboutBox() {
        super(AlertType.NONE);

        setTitle(String.format("About %s...", IdeInfo.getInstance().name()));

        FXMLLoader loader = new FXMLLoader(AboutBox.class.getResource("AboutBox.fxml"));

        Pane contentPane;
        try {
            contentPane = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        getDialogPane().setContent(contentPane);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButton);
    }
}
