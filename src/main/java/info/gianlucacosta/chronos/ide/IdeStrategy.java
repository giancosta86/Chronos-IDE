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

import info.gianlucacosta.chronos.ast.Program;
import info.gianlucacosta.chronos.interpreter.Input;
import info.gianlucacosta.chronos.interpreter.Interpreter;
import info.gianlucacosta.chronos.interpreter.Output;
import info.gianlucacosta.chronos.parser.BasicAstBuilder;
import info.gianlucacosta.chronos.parser.exceptions.ParsingException;
import info.gianlucacosta.omnieditor.AppStrategy;
import info.gianlucacosta.omnieditor.AtomicStringBuffer;
import info.gianlucacosta.omnieditor.StyledCodeEditor;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;


class IdeStrategy implements AppStrategy {
    private Optional<AboutBox> aboutBox = Optional.empty();

    @Override
    public String getAppTitle() {
        return IdeInfo.getInstance().name();
    }


    @Override
    public FileChooser createSourceFileChooser() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().setAll(
                new FileChooser.ExtensionFilter("Chronos source file", "*.chronos")
        );

        return fileChooser;
    }


    @Override
    public StyledCodeEditor createCodeEditor() {
        StyledCodeEditor codeEditor = new StyledCodeEditor();

        StylePatterns.styleCodeEditor(codeEditor);

        String exampleCode = getExampleCode();
        codeEditor.setText(exampleCode);

        codeEditor.getUndoManager().forgetHistory();
        codeEditor.enableSmartNewline();
        codeEditor.enableDynamicTabs(4);

        return codeEditor;
    }


    private String getExampleCode() {
        try (BufferedReader exampleReader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("example.chronos")
                )
        )) {
            StringBuilder exampleBuilder = new StringBuilder();

            while (true) {
                String line = exampleReader.readLine();
                if (line == null) {
                    break;
                }

                exampleBuilder.append(line);
                exampleBuilder.append("\n");
            }

            return exampleBuilder.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public File getSavedSourceFile(FileChooser sourceFileChooser, File selectedFile) {
        if (!selectedFile.getName().endsWith(".chronos")) {
            return new File(selectedFile.getAbsolutePath() + ".chronos");
        } else {
            return selectedFile;
        }
    }


    @Override
    public boolean isShowSettings() {
        return false;
    }

    @Override
    public void showSettings() {
        //Just do nothing
    }


    @Override
    public void run(String programCode, AtomicStringBuffer outputBuffer) throws InterruptedException {
        Input input = new IdeInput();
        Output output = new IdeOutput(outputBuffer);

        try {
            Program program = BasicAstBuilder.buildAST(programCode);
            Interpreter interpreter = new Interpreter(input, output);
            interpreter.run(program);
        } catch (InterruptedException ex) {
            throw ex;
        } catch (ParsingException ex) {
            output.printException(ex);
        } catch (Exception ex) {
            output.printException(ex);
            ex.printStackTrace(System.err);
        }
    }


    @Override
    public URL getSyntaxCss() {
        return getClass().getResource("chronos-syntax.css");
    }


    @Override
    public void showAboutWindow() {
        if (!aboutBox.isPresent()) {
            aboutBox = Optional.of(new AboutBox());
        }

        aboutBox.get().showAndWait();
    }
}