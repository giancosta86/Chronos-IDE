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

import info.gianlucacosta.chronos.interpreter.atoms.BooleanAtom;
import info.gianlucacosta.chronos.interpreter.atoms.StringAtom;
import info.gianlucacosta.chronos.interpreter.exceptions.FailedReadException;
import info.gianlucacosta.chronos.interpreter.io.BasicInput;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class IdeInput extends BasicInput {
    @Override
    public StringAtom readString(String prompt) {
        final AtomicReference<Optional<String>> inputBuffer = new AtomicReference<>();
        final AtomicBoolean simpleLock = new AtomicBoolean(false);

        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(IdeInfo.getInstance().name());
            dialog.setHeaderText(prompt);


            Optional<String> inputString = dialog.showAndWait();
            inputBuffer.set(inputString);


            synchronized (simpleLock) {
                simpleLock.set(true);
                simpleLock.notifyAll();
            }

        });

        synchronized (simpleLock) {
            while (!simpleLock.get()) {
                try {
                    simpleLock.wait();
                } catch (InterruptedException ex) {
                    //Just do nothing
                }
            }
        }

        Optional<String> inputString = inputBuffer.get();

        if (inputString.isPresent()) {
            return new StringAtom(inputString.get());
        } else {
            throw new FailedReadException("Interrupted input");
        }
    }


    @Override
    public BooleanAtom readBoolean(String prompt) {
        final AtomicReference<Optional<Boolean>> inputBuffer = new AtomicReference<>(Optional.empty());
        final AtomicBoolean simpleLock = new AtomicBoolean(false);

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(IdeInfo.getInstance().name());
            alert.setHeaderText(prompt);

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton) {
                inputBuffer.set(Optional.of(true));
            } else if (result.get() == noButton) {
                inputBuffer.set(Optional.of(false));
            }

            synchronized (simpleLock) {
                simpleLock.set(true);
                simpleLock.notifyAll();
            }
        });

        synchronized (simpleLock) {
            while (!simpleLock.get()) {
                try {
                    simpleLock.wait();
                } catch (InterruptedException ex) {
                    //Just do nothing
                }
            }
        }

        Optional<Boolean> inputBoolean = inputBuffer.get();

        if (inputBoolean.isPresent()) {
            return new BooleanAtom(inputBoolean.get());
        } else {
            throw new FailedReadException("Interrupted input");
        }
    }
}
