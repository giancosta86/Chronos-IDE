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

import info.gianlucacosta.omnieditor.StyledCodeEditor

/*
 *   Code editor with syntax highlighting for Chronos.
 *
 *   Inspired by a syntax highlighting example provided by RichTextFX.
 */
class ChronosCodeEditor extends StyledCodeEditor {
  private val commentPattern =
    raw"//[^\n]*" + "|" +
      raw"(?s)/\*.*?(\*/|\Z)"

  private val stringPattern = raw""""[^"\n]*"?"""

  private val numberPattern = raw"\b(\+|-)?(0|[1-9][0-9]*)(.[0-9]+)?\b|(\+|-)?inf|now|time\.v"
  private val parenthesesPattern = "[()]"
  private val bracesPattern = "[{}]"
  private val semicolonPattern = ";"

  private val keywords = Seq(
    "queue",
    "fifo",
    "lifo",
    "sorted",
    "by",
    "asc",
    "desc",
    "map",
    "event",
    "procedure",
    "disableHeapCheck",
    "create",
    "called",
    "destroy",
    "schedule",
    "at",
    "after",
    "cancel",
    "global",
    "insert",
    "into",
    "remove",
    "from",
    "call",
    "return",
    "exit",
    "if",
    "else",
    "while",
    "assert",
    "or",
    "and",
    "not",
    "get",
    "first",
    "from",
    "print",
    "println",
    "true",
    "false",
    "setRandomSeed"
  )


  private val functions = Seq(
    "isEmpty",
    "uniformRandom",
    "uniformIntRandom",
    "expRandom",
    "toDouble",
    "toInt",
    "toBool",
    "toString",
    "readDouble",
    "readInt",
    "readBool",
    "readString",
    "floor",
    "ceil"
  )


  addPattern("comment", commentPattern)
  addPattern("string", stringPattern)
  addPattern("number", numberPattern)
  addPattern("paren", parenthesesPattern)
  addPattern("brace", bracesPattern)
  addPattern("semicolon", semicolonPattern)
  addTokens("keyword", keywords: _*)
  addTokens("function", functions: _*)

  getUndoManager.forgetHistory()
  enableSmartNewline()
  enableDynamicTabs(4)
}
