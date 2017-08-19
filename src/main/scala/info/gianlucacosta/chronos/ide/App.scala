/*§
  ===========================================================================
  Chronos IDE
  ===========================================================================
  Copyright (C) 2015-2017 Gianluca Costa
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

import javafx.stage.Stage

import info.gianlucacosta.chronos.chronos_ide.ArtifactInfo
import info.gianlucacosta.chronos.icons.MainIcon
import info.gianlucacosta.helios.apps.{AppInfo, AuroraAppInfo}
import info.gianlucacosta.helios.fx.apps.{AppBase, AppMain, SplashStage}
import info.gianlucacosta.omnieditor.OmniEditor


object App extends AppMain[App](classOf[App])

class App extends AppBase(AuroraAppInfo(ArtifactInfo, MainIcon)) {
  override def startup(appInfo: AppInfo, splashStage: SplashStage, primaryStage: Stage): Unit = {
    splashStage.statusText = "Creating OmniEditor..."
    val omniEditor = new OmniEditor(new IdeStrategy)

    splashStage.statusText = "Starting OmniEditor..."
    omniEditor.start(primaryStage)
  }
}