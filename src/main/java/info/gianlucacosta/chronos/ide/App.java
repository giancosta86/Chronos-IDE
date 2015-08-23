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

import info.gianlucacosta.omnieditor.OmniEditor;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().setAll(
                new Image(getClass().getResourceAsStream("icons/mainIcon16.png")),
                new Image(getClass().getResourceAsStream("icons/mainIcon32.png")),
                new Image(getClass().getResourceAsStream("icons/mainIcon64.png")),
                new Image(getClass().getResourceAsStream("icons/mainIcon128.png"))
        );

        OmniEditor omniEditor = new OmniEditor(new IdeStrategy());

        omniEditor.start(primaryStage);
    }
}