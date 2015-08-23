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

import info.gianlucacosta.chronos.Language;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AboutBoxController {
    @FXML
    private Label languageLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label versionLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private Label licenseLabel;

    @FXML
    private Label additionalInfoLabel;

    @FXML
    public void initialize() {
        languageLabel.setText(String.format("%s %s",
                Language.name().toUpperCase(),
                Language.version()
        ));

        titleLabel.setText(IdeInfo.getInstance().name());

        versionLabel.setText(String.format("Version %s", IdeInfo.getInstance().version()));

        copyrightLabel.setText(String.format("Copyright © %s Gianluca Costa.", IdeInfo.getInstance().copyrightYears()));

        licenseLabel.setText(String.format(
                        "This software is released under the following license:\n"
                        + "\n"
                        + "\t%s",
                        IdeInfo.getInstance().license())
        );

        additionalInfoLabel.setText(
            "For further information, please refer to the LICENSE and README files."
        );
    }


    public void showLanguageReference() {
        DesktopUtils.openBrowser(Language.website());
    }


    public void showFacebookPage() {
        DesktopUtils.openBrowser(IdeInfo.getInstance().facebookPage());
    }

    public void showOmniEditorPage() {
        DesktopUtils.openBrowser("https://github.com/giancosta86/OmniEditor");
    }
}
