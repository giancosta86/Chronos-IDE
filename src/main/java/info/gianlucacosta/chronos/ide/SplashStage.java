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


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SplashStage extends Stage {
    private final VBox splashBox;
    private final ImageView splashImageView;
    private final ProgressIndicator splashIndicator;


    public SplashStage() {
        super(StageStyle.UNDECORATED);

        setTitle("Loading...");

        splashBox = new VBox();
        splashBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        splashBox.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, null, null)));

        splashBox.setSpacing(30);
        splashBox.setPadding(new Insets(30));
        splashBox.setAlignment(Pos.CENTER);

        Image splashImage = new Image(App.class.getResourceAsStream("icons/mainIcon512.png"));

        splashImageView = new ImageView(splashImage);
        splashBox.getChildren().add(splashImageView);

        splashIndicator = new ProgressIndicator();
        splashIndicator.setPrefSize(64, 64);
        splashBox.getChildren().add(splashIndicator);

        Scene splashScene = new Scene(splashBox);
        splashScene.setFill(null);

        getIcons().add(
                new Image(App.class.getResourceAsStream("icons/mainIcon32.png"))
        );
        setScene(splashScene);
        sizeToScene();

        centerOnScreen();
    }
}