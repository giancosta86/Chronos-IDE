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

import info.gianlucacosta.omnieditor.StyledCodeEditor;

/*
 *   Inspired by a syntax highlighting example provided by RichTextFX
 */

class StylePatterns {
    private static final String commentPattern = "//[^\n]*" + "|" + "(?s)/\\*.*?(\\*/|\\Z)";
    private static final String stringPattern = "\"[^\"\\n]*\"?";

    private static final String numberPattern = "\\b(\\+|-)?(0|[1-9][0-9]*)(.[0-9]+)?\\b|(\\+|-)?inf|time\\.v";
    private static final String parenthesesPattern = "[()]";
    private static final String bracesPattern = "[{}]";
    private static final String semicolonPattern = ";";

    private static final String[] keywords = new String[]{
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
            "$disableHeapCheck",
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
            "readDouble",
            "readInt",
            "readBool",
            "readString",
            "true",
            "false",
            "setRandomSeed"
    };


    private static final String[] functions = new String[]{
            "isEmpty",
            "uniformRandom",
            "uniformIntRandom",
            "expRandom",
            "toDouble",
            "toInt",
            "toBool",
            "toString",
            "floor",
            "ceil"
    };


    public static void styleCodeEditor(StyledCodeEditor codeEditor) {
        codeEditor.addPattern("comment", commentPattern);
        codeEditor.addPattern("string", stringPattern);
        codeEditor.addPattern("number", numberPattern);
        codeEditor.addPattern("paren", parenthesesPattern);
        codeEditor.addPattern("brace", bracesPattern);
        codeEditor.addPattern("semicolon", semicolonPattern);
        codeEditor.addTokens("keyword", keywords);
        codeEditor.addTokens("function", functions);
    }

    private StylePatterns() {
    }
}
