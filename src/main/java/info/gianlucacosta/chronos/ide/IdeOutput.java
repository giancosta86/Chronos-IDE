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

import info.gianlucacosta.chronos.interpreter.Output;
import info.gianlucacosta.chronos.interpreter.atoms.Atom;
import info.gianlucacosta.omnieditor.AtomicStringBuffer;

class IdeOutput implements Output {
    private final AtomicStringBuffer outputBuffer;

    public IdeOutput(AtomicStringBuffer outputBuffer) {
        this.outputBuffer = outputBuffer;
    }

    @Override
    public void print(Atom atom) {
        outputBuffer.print(atom.toString());
    }

    @Override
    public void println(Atom atom) {
        outputBuffer.println(atom.toString());
    }

    @Override
    public void printException(Exception exception) {
        outputBuffer.println("** ERROR **");
        outputBuffer.println(exception.getMessage());
    }
}
