# cljstyle

This formatter extension is a wrapper around cljstyle.

## Configuration

cljstyle.indentation - true if cljstyle should correct the indentation of your code. Defaults to true.

cljstyle.insertMissingWhitespace - true if cljstyle should insert whitespace missing from between elements. This will convert (foo(bar)) to (foo (bar)). Defaults to true.

cljstyle.removeSurroundingWhitespace - true if cljstyle should remove whitespace surrounding inner forms. This will convert ( foo ) to (foo). Defaults to true.

cljstyle.removeTrailingWhitespace - true if cljstyle should remove trailing whitespace in lines. This will convert (foo) \n to (foo)\n. Defaults to true.

## Acknowledgement

Big thank you to [pedrorgirardi](https://github.com/pedrorgirardi). This project is a _fork_ of [vscode-cljfmt](https://github.com/pedrorgirardi/vscode-cljfmt) but modified to use `cljstyle` instead of `cljfmt`.
