import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngineFactory;
// imports for GraalJS bindings
import javax.script.Bindings;
import javax.script.ScriptContext;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NashornTest {
  public static void main(String[] args) {
    System.out.println("VM Details:");
    // System.out.println(org.graalvm.home.Version.getCurrent());
    System.out.println(System.getProperty("java.vm.name"));
    System.out.println(System.getProperty("java.vm.version"));
    System.out.println(System.getProperty("java.vm.info"));
    ScriptEngine engine = (new ScriptEngineManager()).getEngineByName("Nashorn");
    if (engine == null) {
      System.out.println("Nashorn engine not found.");
    } else {
      System.out.println("Nashorn engine found!");
    }
    List<ScriptEngineFactory> engines = (new ScriptEngineManager()).getEngineFactories();
    for (ScriptEngineFactory f: engines) {
      System.out.println(f.getLanguageName()+" "+f.getEngineName()+" "+f.getNames().toString());
      //this.getLogger().severe(f.getLanguageName()+" "+f.getEngineName()+" "+f.getNames().toString());
    }
  }
}