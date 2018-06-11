# JavaPI

## Usage
```
./gradlew
gradle run
```

## To use module in your own program
1. Create the jar file
```
gradle jar      # generates jar file in build/libs directory
```
2. In your java program, include the following:
```
import org.alanzheng.JavaPI;
.
.
.
public static void main(String[] args) {
  System.out.println(JavaPI.getValueOfPi(2));
}

```
3. Compile and run your program
```
javac -d bin -cp '.:./lib/org.alanzheng.jar' <PATH_TO_MAIN_FILE>
java -classpath 'bin:./lib/org.alanzheng.jar' <MAIN_CLASS_NAME>
```

## Notes
- javac: if classpath and sourcepath is not set, defaults to current directory
- javac: extensions bundled as jar files
```
javac -d bin -classpath bin src/JavaPI.java
java -classpath bin JavaPI
```
