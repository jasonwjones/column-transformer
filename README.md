# Column Transformer

Column Transformer is a very simple command-line Java program that can be used to parse out a structured text file similar to how an Essbase load rule would process a data file for loading in to a multidimensional cube.

The inspiration for this program came from an off the cuff remark from a colleague saying that Oracle Essbase load rules are on their way out. And, well, they are. There are increasingly attractive options for getting around the need to use a load rule to parse data or update dimensions. 

For cases where you might want to Essbase load-rule-like processing on a data file, Column Transformer can accomodate almost all of the funtionality that a load rule has in terms of transforming a columnar input source. 

Column Transformer has a very terse micro-language (read: command-line switches) that generally have a type and column number that they operate on. For example, the first thing you do might be to split a line of text from a file based on commas (or some other delimiter). This transforms a single column of input into as many columns as there are commas. A subsequent tranformation might be to prefix the contents of column 0 (the first column) with some bit of text, similar to a transformation that would occur in a load rule.

Other rules are available, such as for suffixing the contents of a column, creating a new column with arbitrary text, joining columns, removing a column, splitting based on a delimiter, joing with a delimiter, replacing text, and so on.
 
In a way, CT can be thought of a poor mans AWK or similar ETL capability with a focus on being easy to replace the functionality available in Essbase load rules. Additionally, unlike other tools, the number of columns can possibly change from processing step to processing step. For example, the input to a certain step might start with 3 columns but then have one of those removed, meaning that subsequent steps that operate on a column then need to take into account the changing index number of the column. 

Column Transformer by default accepts input from STDIN and outputs to STDOUT. Error messages are output to STDERR. CT can be used on the command line to process a file by piping the contents of the source file in and redirecting it to a target output file, such as below:

java -jar column-transformer.jar -X,3 < input-file.txt > output-file.txt
 
# Future Features

* Drop leading/trailing spaces
* Convert spaces to underscore
* Lowercase
* Uppercase
* Scale
* Comma, Tab, All Spaces, Custom, column width
* CREATE with join
* Remove header?
* Conditional sign flip
* Global select reject
