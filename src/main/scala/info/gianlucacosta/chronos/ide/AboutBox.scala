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

package info.gianlucacosta.chronos.ide

import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane

import info.gianlucacosta.chronos.chronos_ide.{ArtifactInfo => IdeInfo}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonBar, ButtonType}


class AboutBox extends Alert(AlertType.None) {
  title = s"About ${IdeInfo.name}..."

  dialogPane().setContent({
    val loader = new FXMLLoader(App.getClass.getResource("AboutBox.fxml"))

    loader.load[Pane]
  })


  buttonTypes = Seq(
    new ButtonType("OK", ButtonBar.ButtonData.OKDone)
  )
}
