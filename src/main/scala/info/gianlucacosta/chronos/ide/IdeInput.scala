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


import java.util.concurrent.atomic.{AtomicBoolean, AtomicReference}

import info.gianlucacosta.chronos.chronos_ide.{ArtifactInfo => IdeInfo}
import info.gianlucacosta.chronos.interpreter.atoms.{BooleanAtom, StringAtom}
import info.gianlucacosta.chronos.interpreter.exceptions.FailedReadException
import info.gianlucacosta.chronos.interpreter.io.BasicInput

import scalafx.application.Platform
import scalafx.scene.control.{Alert, ButtonBar, ButtonType, TextInputDialog}


class IdeInput extends BasicInput {

  override def readString(prompt: String): StringAtom = {
    val inputBuffer = new AtomicReference[Option[String]]()
    val simpleLock = new AtomicBoolean(false)


    Platform.runLater {
      val dialog = new TextInputDialog {
        title = IdeInfo.name
        headerText = prompt
      }

      val inputString = dialog.showAndWait()
      inputBuffer.set(inputString)

      simpleLock.synchronized {
        simpleLock.set(true)
        simpleLock.notifyAll()
      }
    }


    simpleLock.synchronized {
      while (!simpleLock.get()) {
        try {
          simpleLock.wait()
        } catch {
          case ex: InterruptedException => //Just do nothing
        }
      }
    }

    val inputString = inputBuffer.get()

    if (inputString.nonEmpty) {
      new StringAtom(inputString.get)
    } else {
      throw new FailedReadException("Interrupted input")
    }
  }


  override def readBoolean(prompt: String): BooleanAtom = {
    val inputBuffer = new AtomicReference[Option[Boolean]](None)
    val simpleLock = new AtomicBoolean(false)

    Platform.runLater {
      val alert = new Alert(Alert.AlertType.Confirmation) {
        title = IdeInfo.name
        headerText = prompt
      }

      val yesButton = new ButtonType("Yes")
      val noButton = new ButtonType("No")
      val cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CancelClose)

      alert.buttonTypes = Seq(
        yesButton,
        noButton,
        cancelButton
      )

      val result = alert.showAndWait()

      inputBuffer.set(
        result match {
          case Some(`yesButton`) => Some(true)
          case Some(`noButton`) => Some(false)
          case _ => None
        })

      simpleLock.synchronized {
        simpleLock.set(true)
        simpleLock.notifyAll()
      }
    }


    simpleLock.synchronized {
      while (!simpleLock.get()) {
        try {
          simpleLock.wait()
        } catch {
          case ex: InterruptedException => //Just do nothing
        }
      }
    }

    val inputBoolean = inputBuffer.get()

    if (inputBoolean.nonEmpty) {
      new BooleanAtom(inputBoolean.get)
    } else {
      throw new FailedReadException("Interrupted input")
    }
  }
}
