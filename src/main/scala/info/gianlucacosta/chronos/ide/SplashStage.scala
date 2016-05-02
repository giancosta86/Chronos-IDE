/*§
  ===========================================================================
  Chronos IDE
  ===========================================================================
  Copyright (C) 2015-2016 Gianluca Costa
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

package info.gianlucacosta.chronos.ide

import scalafx.Includes._
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.ProgressIndicator
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.stage.{Stage, StageStyle}


class SplashStage extends Stage(StageStyle.UNDECORATED) {
  title = "Loading..."

  scene = new Scene {
    content = new VBox {
      background = new Background(Array(new BackgroundFill(Color.White, null, null)))

      spacing = 30
      padding = Insets(30)
      alignment = Pos.Center

      border = new javafx.scene.layout.Border(new BorderStroke(Color.DarkGrey, BorderStrokeStyle.Solid, null, null))

      children = Seq(
        new ImageView {
          image = new Image(App.getClass.getResourceAsStream("icons/mainIcon512.png"))
        },

        new ProgressIndicator() {
          prefWidth = 64
          prefHeight = 64
        }
      )
    }
  }

  icons.add(
    new Image(App.getClass.getResourceAsStream("icons/mainIcon32.png"))
  )

  sizeToScene()

  centerOnScreen()
}